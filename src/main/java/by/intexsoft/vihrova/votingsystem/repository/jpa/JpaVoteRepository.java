package by.intexsoft.vihrova.votingsystem.repository.jpa;

import by.intexsoft.vihrova.votingsystem.model.Vote;
import by.intexsoft.vihrova.votingsystem.repository.VoteRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public abstract class JpaVoteRepository implements VoteRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote save(Vote vote) {
        if(vote.getId()==null) {
            em.persist(vote);
            return vote;
        } else {
            return em.merge(vote);
        }
    }

    @Override
    @Transactional
    public void deleteById(int id){
        Query query = em.createQuery("DELETE FROM Vote v WHERE v.id=:id");
        query.setParameter("id", id);
    }

    @Override
    public Optional<Vote> findById(int id){
        Vote vote = em.find(Vote.class, id);
        return Optional.of(vote);
    }

    @Override
    public List<Vote> findAll(){
        Query query = em.createQuery("SELECT v FROM Vote v");
        return query.getResultList();
    }
}
