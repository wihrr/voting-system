package by.intexsoft.vihrova.votingsystem.service.impl;

import by.intexsoft.vihrova.votingsystem.exception.EntityNotFoundException;
import by.intexsoft.vihrova.votingsystem.model.Menu;
import by.intexsoft.vihrova.votingsystem.service.MenuService;
import by.intexsoft.vihrova.votingsystem.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    @Autowired
    private final MenuRepository menuRepository;

    @Override
    public Menu getById(int id) {
        Optional<Menu> menu = menuRepository.findById(id);
        if(menu.isPresent()){
            return menu.get();
        } else {
            throw new EntityNotFoundException("There is no menu with id - " + id);
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
