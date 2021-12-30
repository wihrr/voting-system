package by.intexsoft.vihrova.votingsystem.repository.datajpa;

import by.intexsoft.vihrova.votingsystem.model.Vote;
import by.intexsoft.vihrova.votingsystem.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("datajpa")
public abstract class DataJpaVoteRepository implements VoteRepository {
    @Autowired
    private VoteRepository voteRepository;

    List<Vote> getAll() {
        return voteRepository.findAll();
    }

    void delete(int id) {
        voteRepository.deleteById(id);
    }

    Optional<Vote> get(int id) {
        return voteRepository.findById(id);
    }

    Vote create(Vote vote) {
        return voteRepository.save(vote);
    }
}
