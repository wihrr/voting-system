package by.intexsoft.vihrova.votingsystem.repository.datajpa;

import by.intexsoft.vihrova.votingsystem.model.Vote;
import by.intexsoft.vihrova.votingsystem.repository.VoteRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("datajpa")
public interface DataJpaVoteRepository extends VoteRepository, JpaRepository<Vote, Integer> {
}
