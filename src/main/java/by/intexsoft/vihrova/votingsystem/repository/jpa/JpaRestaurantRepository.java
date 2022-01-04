package by.intexsoft.vihrova.votingsystem.repository.jpa;

import by.intexsoft.vihrova.votingsystem.model.Restaurant;
import by.intexsoft.vihrova.votingsystem.repository.RestaurantRepository;
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
public class JpaRestaurantRepository implements RestaurantRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.getId() == null) {
            em.persist(restaurant);
            return restaurant;
        } else {
            return em.merge(restaurant);
        }
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        int result = em.createQuery("DELETE FROM Restaurant r WHERE r.id=:id")
                .setParameter("id", id)
                .executeUpdate();
        if (result != 1) {
            throw new RuntimeException("There was an error in delete for restaurant with id: " + id);
        }
    }

    @Override
    public Optional<Restaurant> findById(int id) {
        Restaurant restaurant = em.find(Restaurant.class, id);
        return Optional.of(restaurant);
    }

    @Override
    public List<Restaurant> findAll() {
        Query query = em.createQuery("SELECT r FROM Restaurant r");
        return query.getResultList();
    }
}
