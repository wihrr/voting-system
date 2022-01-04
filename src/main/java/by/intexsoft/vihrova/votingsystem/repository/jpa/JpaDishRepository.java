package by.intexsoft.vihrova.votingsystem.repository.jpa;

import by.intexsoft.vihrova.votingsystem.model.Dish;
import by.intexsoft.vihrova.votingsystem.repository.DishRepository;
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
public class JpaDishRepository implements DishRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    @Transactional
    public Dish save(Dish dish) {
        if (dish.getId() == null) {
            em.persist(dish);
            return dish;
        } else {
            return em.merge(dish);
        }
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        int result = em.createQuery("DELETE FROM Dish d WHERE d.id=:id")
                .setParameter("id", id)
                .executeUpdate();

        if (result != 1) {
            throw new RuntimeException("There was an error in delete for dish with id: " + id);
        }
    }

    @Override
    public Optional<Dish> findById(int id) {
        Dish dish = em.find(Dish.class, id);
        return Optional.of(dish);
    }

    @Override
    public List<Dish> findAll() {
        Query query = em.createQuery("SELECT d FROM Dish d LEFT JOIN FETCH d.menus ORDER BY d.id");
        return query.getResultList();
    }
}
