package by.intexsoft.vihrova.votingsystem.dtoutils;

import by.intexsoft.vihrova.votingsystem.dto.UserTo;
import by.intexsoft.vihrova.votingsystem.model.Role;
import by.intexsoft.vihrova.votingsystem.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserToUtils {

    public static UserTo createTo(User user) {
        return UserTo.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .registered(user.getRegistered())
                .rolesIds(user.getRoles().stream()
                        .map(Role::getId)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static List<UserTo> getTos(Collection<User> users) {
        return users.stream()
                .map(UserToUtils::createTo)
                .collect(Collectors.toList());
    }
}
