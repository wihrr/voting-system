package by.intexsoft.vihrova.votingsystem.vote.service;

import by.intexsoft.vihrova.votingsystem.vote.Vote;
import by.intexsoft.vihrova.votingsystem.vote.VoteNotFoundException;
import by.intexsoft.vihrova.votingsystem.vote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService{
    @Autowired
    private final VoteRepository voteRepository;

    @Override
    public Vote getById(int id) {
        Optional<Vote> vote = voteRepository.findById(id);
        if (vote.isPresent()){
            return vote.get();
        } else {
            throw new VoteNotFoundException("There is no vote with id - " + id);
        }
    }

    @Override
    public List<Vote> getAll() {
        return (List<Vote>) voteRepository.findAll();
    }

    @Override
    public Vote save(Vote vote) {
        return voteRepository.save(vote);
    }

    @Override
    public void delete(int id) {
        voteRepository.deleteById(id);
    }
}
