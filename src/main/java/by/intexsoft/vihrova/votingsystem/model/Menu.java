package by.intexsoft.vihrova.votingsystem.model;

;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menus")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "menu_dishes",
            joinColumns = {@JoinColumn(name = "menu_id")},
            inverseJoinColumns = {@JoinColumn(name = "dish_id")}
    )
    private Set<Dish> dishes = new HashSet<>();

    @ManyToMany(mappedBy = "menus", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Restaurant> restaurants = new HashSet<>();

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
//    @JsonManagedReference
//    private Set<Vote> votes;
}
