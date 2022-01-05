package by.intexsoft.vihrova.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 5, max = 50)
    private String name;

    @NotNull
    private Double price;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "menu_dishes",
            joinColumns = {@JoinColumn(name = "dish_id")},
            inverseJoinColumns = {@JoinColumn(name = "menu_id")}
    )
    private Set<Menu> menus;

    @JsonIgnore
    public String getInfoToCompare() {
        return this.getName() + this.getPrice();
    }
}
