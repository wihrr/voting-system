package by.intexsoft.vihrova.votingsystem.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Builder
public class UserTo {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Date registered;
    private Set<Integer> rolesIds;
}