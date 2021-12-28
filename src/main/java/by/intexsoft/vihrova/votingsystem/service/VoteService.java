package by.intexsoft.vihrova.votingsystem.service;

import by.intexsoft.vihrova.votingsystem.model.Vote;

import java.util.List;

public interface VoteService {

    Vote getById(int id, int userId, int restaurantId);

    List<Vote> getAll(int userId);

    Vote save(Vote vote, Integer userId, Integer menuId);

    void delete(int id, int userId, int menuId);

    void update(Vote vote, Integer userId, Integer menuId);
}
