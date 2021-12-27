package by.intexsoft.vihrova.votingsystem.vote.repository;

import by.intexsoft.vihrova.votingsystem.vote.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Integer> {
}
