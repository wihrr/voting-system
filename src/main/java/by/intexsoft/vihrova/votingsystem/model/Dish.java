package by.intexsoft.vihrova.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToMany(mappedBy = "dishes", cascade = {CascadeType.ALL})
    private Set<Menu> menus = new HashSet<>();

    @JsonIgnore
    public String getInfoToCompare() {
        return this.getName() + this.getPrice();
    }
}
