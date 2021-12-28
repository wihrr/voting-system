package by.intexsoft.vihrova.votingsystem.service.impl;

import by.intexsoft.vihrova.votingsystem.exception.EntityNotFoundException;
import by.intexsoft.vihrova.votingsystem.model.Menu;
import by.intexsoft.vihrova.votingsystem.service.MenuService;
import by.intexsoft.vihrova.votingsystem.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    @Autowired
    private final MenuRepository menuRepository;

    @Override
    public Menu getById(int id, int restaurantId) {
        Menu menu = menuRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Can't found menu with ID - " + id));
            if(menu.getRestaurant().getId() == restaurantId) {
                return menu;
        } else {
            throw new EntityNotFoundException("There is no menu with restaurant ID - " + restaurantId);
        }
    }

    @Override
    public List<Menu> getAll(int restaurantId) {
        return menuRepository.findAll().stream().filter(menu -> menu.getRestaurant().getId().equals(restaurantId)).collect(Collectors.toList());
    }

    @Override
    public Menu save(Menu menu, Integer restaurantId) {
        if(!(restaurantId==null)){
            Menu savingMenu = menuRepository.save(menu);
            savingMenu.getRestaurant().setId(restaurantId);
            return menu;
        } else {
            throw new EntityNotFoundException("There is no restaurant with ID - " + restaurantId);
        }
    }

    @Override
    public void delete(int id, int restaurantId) {
        Menu menu = getById(id, restaurantId);
        menuRepository.deleteById(menu.getId());
    }
}
