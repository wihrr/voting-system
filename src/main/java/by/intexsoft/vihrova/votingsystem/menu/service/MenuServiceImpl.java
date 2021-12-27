package by.intexsoft.vihrova.votingsystem.menu.service;

import by.intexsoft.vihrova.votingsystem.menu.Menu;
import by.intexsoft.vihrova.votingsystem.menu.MenuNotFoundException;
import by.intexsoft.vihrova.votingsystem.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService{
    @Autowired
    private final MenuRepository menuRepository;

    @Override
    public Menu getById(int id) {
        Optional<Menu> menu = menuRepository.findById(id);
        if(menu.isPresent()){
            return menu.get();
        } else {
            throw new MenuNotFoundException("There is no menu with id - " + id);
        }
    }

    @Override
    public List<Menu> getAll() {
        return (List<Menu>) menuRepository.findAll();
    }

    @Override
    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public void delete(int id) {
        menuRepository.deleteById(id);
    }
}
