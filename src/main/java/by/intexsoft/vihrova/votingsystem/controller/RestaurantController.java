package by.intexsoft.vihrova.votingsystem.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import by.intexsoft.vihrova.votingsystem.exception.EntityNotFoundException;
import by.intexsoft.vihrova.votingsystem.model.*;
import by.intexsoft.vihrova.votingsystem.service.impl.DishServiceImpl;
import by.intexsoft.vihrova.votingsystem.service.impl.MenuServiceImpl;
import by.intexsoft.vihrova.votingsystem.service.impl.RestaurantServiceImpl;
import by.intexsoft.vihrova.votingsystem.service.impl.VoteServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
@Slf4j
public class RestaurantController {
    private final RestaurantServiceImpl restaurantService;
    private final MenuServiceImpl menuService;
    private final VoteServiceImpl voteService;
    private final DishServiceImpl dishService;

    @GetMapping()
    public List<Restaurant> getAll() {
        return restaurantService.getAll();
    }

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody Restaurant restaurant) {
        Restaurant creatingRestaurant = restaurantService.save(restaurant);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creatingRestaurant.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public EntityModel<Restaurant> getById(@PathVariable int id) {
        Restaurant restaurant = restaurantService.getById(id);

        EntityModel<Restaurant> model = EntityModel.of(restaurant);
        WebMvcLinkBuilder linkToRestaurants = linkTo(methodOn(this.getClass()).getAll());
        model.add(linkToRestaurants.withRel("all-restaurants"));
        return model;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        restaurantService.delete(id);
    }

    @PostMapping("/{restaurantId}/menus/")
    public ResponseEntity<Object> createMenu(@PathVariable int restaurantId, @RequestBody Menu menu) {
        Restaurant restaurant = restaurantService.getById(restaurantId);
        menu.setRestaurant(restaurant);
        menuService.save(menu, restaurantId);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(menu.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{restaurantId}/menus/")
    public Set<Menu> getWithMenus(@PathVariable int restaurantId) {
        Restaurant restaurant = restaurantService.getById(restaurantId);
        return restaurant.getMenus();
    }

    @GetMapping("/{restaurantId}/menus/{menuId}")
    public EntityModel<Menu> getMenu(@PathVariable int restaurantId, @PathVariable int menuId) {
        Restaurant restaurant = restaurantService.getById(restaurantId);
        Menu currentMenu = restaurant.getMenus().stream()
                .filter(menu -> menu.getId().equals(menuId))
                .findFirst().orElseThrow(() -> new EntityNotFoundException("There is no menu with ID - " + menuId));

        EntityModel<Menu> model = EntityModel.of(currentMenu);
        WebMvcLinkBuilder linkToMenus = linkTo(methodOn(this.getClass()).getWithMenus(restaurantId));
        model.add(linkToMenus.withRel("all-menus"));
        return model;
    }

    @DeleteMapping("/{restaurantId}/menus/{menuId}")
    public void deleteMenu(@PathVariable int restaurantId, @PathVariable int menuId) {
        menuService.delete(menuId, restaurantId);
    }

    @PostMapping("/{restaurantId}/menus/{menuId}/votes")
    public ResponseEntity<Object> createVote(@PathVariable int restaurantId, @PathVariable int menuId, @RequestBody Vote vote, User user) {
        vote.setUser(user);
        Menu menu = menuService.getById(menuId, restaurantId);
        vote.setMenu(menu);
        Vote creatingVote = voteService.save(vote, user.getId(), menuId);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creatingVote.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{restaurantId}/menus/{menuId}/votes")
    public List<Vote> getVotes(@PathVariable int restaurantId, @PathVariable int menuId) {
        if (!(getMenu(restaurantId, menuId) == null)) {
            return voteService.getAllVotesOfSameMenu(menuId);
        } else {
            throw new EntityNotFoundException("There is no such menu");
        }
    }

    @GetMapping("/{restaurantId}/menus/{menuId}/votes/{voteId}")
    public EntityModel<Vote> getVoteById(@PathVariable int restaurantId, @PathVariable int menuId, @PathVariable int voteId, User user) {
        if (user.getVotes().stream()
                .noneMatch(vote -> vote.getId().equals(voteId))) {
            throw new EntityNotFoundException("There is no such vote by user with ID - " + user.getId());
        } else {
            Vote vote = voteService.getById(voteId, user.getId(), menuId);

            EntityModel<Vote> model = EntityModel.of(vote);
            WebMvcLinkBuilder linkToMenus = linkTo(methodOn(this.getClass()).getVotes(restaurantId, menuId));
            model.add(linkToMenus.withRel("all-votes"));
            return model;
        }
    }

    @DeleteMapping("/{restaurantId}/menus/{menuId}/votes/{voteId}")
    public void deleteVote(@PathVariable int restaurantId, @PathVariable int menuId, @PathVariable int voteId, User user) {
        if (user.getVotes().stream()
                .noneMatch(vote -> vote.getId().equals(voteId)) &&
                menuService.getById(menuId, restaurantId).getVotes().stream()
                        .noneMatch(vote -> vote.getId().equals(voteId))) {
            throw new EntityNotFoundException("There is no such vote by user with ID - " + user.getId() + " in menu with ID - " + menuId);
        } else {
            voteService.delete(voteId, user.getId(), menuId);
        }
    }

    @PostMapping("/{restaurantId}/menus/{menuId}/dishes")
    public ResponseEntity<Object> createDish(@PathVariable int restaurantId, @PathVariable int menuId, @RequestBody Dish dish) {
        Menu menu = menuService.getById(menuId, restaurantId);
        menu.getDishes().add(dish);
        Dish creatingDish = dishService.save(dish, menuId);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creatingDish.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{restaurantId}/menus/{menuId}/dishes")
    public Set<Dish> getDishes(@PathVariable int restaurantId, @PathVariable int menuId) {
        Menu menu = menuService.getById(menuId, restaurantId);
        return menu.getDishes();
    }

    @GetMapping("/{restaurantId}/menus/{menuId}/dishes/{dishId}")
    public EntityModel<Dish> getDishById(@PathVariable int restaurantId, @PathVariable int menuId, @PathVariable int dishId) {
        Menu menu = menuService.getById(menuId, restaurantId);
        Dish currentDish = menu.getDishes().stream()
                .filter(dish -> dish.getId().equals(dishId))
                .findFirst().orElseThrow(() -> new EntityNotFoundException("There is no dish with ID - " + dishId));

        EntityModel<Dish> model = EntityModel.of(currentDish);
        WebMvcLinkBuilder linkToMenus = linkTo(methodOn(this.getClass()).getDishes(restaurantId, menuId));
        model.add(linkToMenus.withRel("all-dishes"));
        return model;
    }

    @DeleteMapping("/{restaurantId}/menus/{menuId}/dishes/{dishId}")
    public void deleteDish(@PathVariable int restaurantId, @PathVariable int menuId, @PathVariable int dishId) {
        List<Dish> dishes = new ArrayList<>(menuService.getById(menuId, restaurantId).getDishes());
        if (menuService.getById(menuId, restaurantId).getDishes().stream()
                .anyMatch(dish -> dish.getId().equals(dishId))) {
            dishes.remove(dishId);
        }
    }
}
