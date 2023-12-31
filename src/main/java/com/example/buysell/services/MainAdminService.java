package com.example.buysell.services;

import com.example.buysell.models.User;
import com.example.buysell.models.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainAdminService {
    private final UserService userService;
    private final AdminService adminService;

    public void banUser(Long id) {
        User user = userService.findById(id);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                if(user.isSeller()){
                    user.getProducts().forEach((product) -> adminService.unsuccessfulModerate(product,"Вы были забанены"));
                }
                log.info("Ban user with id = {}; email: {}", user.getId(), user.getEmail());
            } else {
                user.setActive(true);
                log.info("Unban user with id = {}; email: {}", user.getId(), user.getEmail());
            }
        }
        userService.save(user);
    }

    public void changeUserRoles(User user, Map<String, String> form) {
        Set<String> roles =
                Arrays.stream(Role.values())
                        .map(Role::name)
                        .collect(Collectors.toSet());
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                if(!user.getRoles().contains(Role.valueOf(key)))
                    user.getRoles().add(Role.valueOf(key));
            }
        }
        userService.save(user);
    }
}
