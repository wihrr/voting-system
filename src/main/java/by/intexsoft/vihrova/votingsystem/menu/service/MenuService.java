package by.intexsoft.vihrova.votingsystem.menu.service;

import by.intexsoft.vihrova.votingsystem.menu.Menu;

import java.util.List;

public interface MenuService {

    Menu getById(int id);

    List<Menu> getAll();

    Menu save(Menu menu);

    void delete(int id);
}
