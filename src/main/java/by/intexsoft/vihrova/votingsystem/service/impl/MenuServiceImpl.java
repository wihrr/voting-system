package by.intexsoft.vihrova.votingsystem.service.impl;

import by.intexsoft.vihrova.votingsystem.exception.EntityNotFoundException;
import by.intexsoft.vihrova.votingsystem.model.Menu;
import by.intexsoft.vihrova.votingsystem.service.MenuService;
import by.intexsoft.vihrova.votingsystem.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;

    @Override
    public Menu getById(int id) {
        return menuRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Can't find menu with ID - " + id));
    }

    @Override
    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    @Override
    public Set<Menu> getMenusOfOneRestaurant(int restaurantId) {
        return menuRepository.findAll().stream()
                .filter(menu -> menu.getRestaurants().stream().anyMatch(restaurant -> restaurant.getId().equals(restaurantId)))
                .collect(Collectors.toSet());
    }

    @Override
    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public void delete(int id) {
        Menu menu = getById(id);
        menuRepository.deleteById(menu.getId());
    }
}
