package by.intexsoft.vihrova.votingsystem.service.impl;

import by.intexsoft.vihrova.votingsystem.exception.EntityNotFoundException;
import by.intexsoft.vihrova.votingsystem.service.VoteService;
import by.intexsoft.vihrova.votingsystem.model.Vote;
import by.intexsoft.vihrova.votingsystem.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {
    private final VoteRepository voteRepository;

    @Override
    public Vote getById(int id, int userId, int menuId) {
        Vote vote = voteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Can't found vote with ID - " + id));
        if (vote.getUser().getId() == userId && vote.getMenu().getId() == menuId) {
            return vote;
        } else {
            throw new EntityNotFoundException("There is no vote with userID - " + userId + " and menu ID - " + menuId);
        }
    }

    @Override
    public List<Vote> getAll(int userId) {
        return voteRepository.findAll().stream()
                .filter(vote -> vote.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    public List<Vote> getAllVotesOfSameMenu(int menu) {
        return voteRepository.findAll().stream()
                .filter(vote -> vote.getMenu().getId().equals(menu))
                .collect(Collectors.toList());
    }

    @Override
    public Vote save(Vote vote, Integer userId, Integer menuId) {
        if (!(userId == null && menuId == null)) {
            Vote savingVote = voteRepository.save(vote);
            savingVote.getUser().setId(userId);
            savingVote.getMenu().setId(menuId);
            return vote;
        } else {
            throw new EntityNotFoundException("There is no vote with user ID - " + userId + " and menu ID - " + menuId);
        }
    }

    @Override
    public void update(Vote vote, Integer userId, Integer menuId) {
        save(vote, userId, menuId);
        if (vote == null) {
            throw new EntityNotFoundException("Can't update entity with user ID" + userId + " and menu ID - " + menuId);
        }
    }

    @Override
    public void delete(int id, int userId, int menuId) {
        Vote vote = getById(id, userId, menuId);
        voteRepository.deleteById(vote.getId());
    }
}
