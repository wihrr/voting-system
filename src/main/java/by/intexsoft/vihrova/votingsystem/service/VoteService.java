package by.intexsoft.vihrova.votingsystem.service;

import by.intexsoft.vihrova.votingsystem.model.Vote;

import java.util.List;

public interface VoteService {

    Vote getById(int id);

    List<Vote> getAll();

    Vote save(Vote vote);

    void delete(int id);
}
