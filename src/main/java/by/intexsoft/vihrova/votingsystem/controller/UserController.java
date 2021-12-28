package by.intexsoft.vihrova.votingsystem.controller;

import by.intexsoft.vihrova.votingsystem.model.User;
import by.intexsoft.vihrova.votingsystem.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserServiceImpl userService;

//    @GetMapping
//    public List<User> users() {
//       return userService.getAll();
//    }

    @PostMapping(value = "/{userName}/test", consumes = "application/json", produces = "application/json")
    public User test(@PathVariable String userName, @RequestParam(value = "age", required = false) Byte userAge, @RequestBody User user) {
        log.info("This is user from drequest: {}", user);
        user.setName(userName);
        return user;
    }
}
