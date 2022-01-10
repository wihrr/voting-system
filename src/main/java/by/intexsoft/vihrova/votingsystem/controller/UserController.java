package by.intexsoft.vihrova.votingsystem.controller;

import by.intexsoft.vihrova.votingsystem.dto.UserTo;
import by.intexsoft.vihrova.votingsystem.dtoutils.UserToUtils;
import by.intexsoft.vihrova.votingsystem.model.Vote;
import by.intexsoft.vihrova.votingsystem.service.impl.UserServiceImpl;
import by.intexsoft.vihrova.votingsystem.service.impl.VoteServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserServiceImpl userService;
    private final VoteServiceImpl voteService;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserTo register(@RequestBody UserTo userTo) {
        return userService.register(userTo);
    }

    @GetMapping("/test")
    public String test(){
        return "Hello";
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserTo> users() {
        return UserToUtils.getTos(userService.getAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserTo create(@RequestBody UserTo userTo) {
        return userService.create(userTo);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserTo findById(@PathVariable int id) {
        return UserToUtils.createTo(userService.getById(id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteById(@PathVariable int id) {
        userService.delete(id);
    }

    @GetMapping(value = "/{id}/votes-history", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getWithVotes(@PathVariable int id) {
        return voteService.getAll(id);
    }
}
