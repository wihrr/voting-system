package by.intexsoft.vihrova.votingsystem.repository.jpa;

import by.intexsoft.vihrova.votingsystem.model.Menu;
import by.intexsoft.vihrova.votingsystem.repository.MenuRepository;
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
public class JpaMenuRepository implements MenuRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    @Transactional
    public Menu save(Menu menu) {
        if (menu.getId() == null) {
            em.persist(menu);
            return menu;
        } else {
            return em.merge(menu);
        }
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        int result = em.createQuery("DELETE FROM Menu m WHERE m.id=:id")
                .setParameter("id", id)
                .executeUpdate();

        if (result != 1) {
            throw new RuntimeException("There was an error in delete for menu with id: " + id);
        }
    }

    @Override
    public Optional<Menu> findById(int id) {
        Menu menu = em.find(Menu.class, id);
        return Optional.of(menu);
    }

    @Override
    public Menu getById(int id) {
        return em.getReference(Menu.class, id);
    }

    @Override
    public List<Menu> findAll() {
        Query query = em.createQuery("SELECT m FROM Menu m LEFT JOIN FETCH m.dishes ORDER BY m.id");
        return query.getResultList();
    }
}
