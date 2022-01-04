package by.intexsoft.vihrova.votingsystem.controller;

import by.intexsoft.vihrova.votingsystem.dto.UserTo;
import by.intexsoft.vihrova.votingsystem.dtoutils.UserToUtils;
import by.intexsoft.vihrova.votingsystem.model.User;
import by.intexsoft.vihrova.votingsystem.model.Vote;
import by.intexsoft.vihrova.votingsystem.service.impl.UserServiceImpl;
import by.intexsoft.vihrova.votingsystem.service.impl.VoteServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserServiceImpl userService;
    private final VoteServiceImpl voteService;

    @GetMapping()
    public List<UserTo> users() {
        return UserToUtils.getTos(userService.getAll());
    }

    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody User user) {
        User newUser = userService.create(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public UserTo findById(@PathVariable int id) {
        return UserToUtils.createTo(userService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        userService.delete(id);
    }

    @GetMapping("/{id}/votes-history")
    public List<Vote> getWithVotes(@PathVariable int id) {
        return voteService.getAll(id);
    }
}
