package by.intexsoft.vihrova.votingsystem.repository.jpa;

import by.intexsoft.vihrova.votingsystem.model.Vote;
import by.intexsoft.vihrova.votingsystem.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
@Profile("jpa")
@RequiredArgsConstructor
public class JpaVoteRepository implements VoteRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    @Transactional
    public Vote save(Vote vote) {
        if (vote.getId() == null) {
            em.persist(vote);
            return vote;
        } else {
            return em.merge(vote);
        }
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        int result = em.createQuery("DELETE FROM Vote v WHERE v.id=:id")
                .setParameter("id", id)
                .executeUpdate();
        if (result != 1) {
            throw new RuntimeException("There was an error in delete for vote with id: " + id);
        }
    }

    @Override
    public Optional<Vote> findById(int id) {
        Vote vote = em.find(Vote.class, id);
        return Optional.of(vote);
    }

    @Override
    public List<Vote> findAll() {
        Query query = em.createQuery("SELECT v FROM Vote v");
        return query.getResultList();
    }
}
