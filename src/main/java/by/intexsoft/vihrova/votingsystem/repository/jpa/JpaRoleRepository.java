package by.intexsoft.vihrova.votingsystem.repository.jpa;

import by.intexsoft.vihrova.votingsystem.model.Role;
import by.intexsoft.vihrova.votingsystem.repository.RoleRepository;
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
public abstract class JpaRoleRepository implements RoleRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Role save(Role role) {
        if (role.getId() == null) {
            em.persist(role);
            return role;
        } else {
            return em.merge(role);
        }
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Query query = em.createQuery("DELETE FROM Role r WHERE r.id=:id");
        query.setParameter("id", id);
    }

    @Override
    public Optional<Role> findById(int id) {
        Role role = em.find(Role.class, id);
        return Optional.of(role);
    }

    @Override
    public List<Role> findAll() {
        Query query = em.createQuery("SELECT r FROM Role r LEFT JOIN FETCH r.users ORDER BY r.id");
        return query.getResultList();
    }
}
