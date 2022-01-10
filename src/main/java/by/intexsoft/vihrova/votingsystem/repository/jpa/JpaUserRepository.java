package by.intexsoft.vihrova.votingsystem.repository.jpa;

import by.intexsoft.vihrova.votingsystem.exception.JpaException;
import by.intexsoft.vihrova.votingsystem.model.User;
import by.intexsoft.vihrova.votingsystem.repository.UserRepository;
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
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    @Transactional
    public User save(User user) {
        if (user.getId() == null) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Override
    public Optional<User> findByName(String userName) {
        User user = em.find(User.class, userName);
        return Optional.of(user);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        int result = em.createQuery("DELETE FROM User u WHERE u.id=:id")
                .setParameter("id", id)
                .executeUpdate();
        if (result != 1) {
            throw new JpaException ("There was an error in delete for user with id: " + id);
        }
    }

    @Override
    public Optional<User> findById(int id) {
        User user = em.find(User.class, id);
        return Optional.of(user);
    }

    @Override
    public List<User> findAll() {
        Query query = em.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }
}
