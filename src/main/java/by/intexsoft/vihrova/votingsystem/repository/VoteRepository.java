package by.intexsoft.vihrova.votingsystem.repository;

import by.intexsoft.vihrova.votingsystem.model.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Integer> {

    List<Vote> findAll();

    void deleteById(int id);

    Optional<Vote> findById(int id);

    Vote save(Vote vote);
}
