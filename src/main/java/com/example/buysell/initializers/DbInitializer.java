package com.example.buysell.initializers;

import com.example.buysell.models.User;
import com.example.buysell.models.enums.Role;
import com.example.buysell.services.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
public class DbInitializer {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    @Transactional
    public void initDb() {
        String email = "danul78969@gmail.com";

        User mainAdmin = userService.findByEmail(email);

        if(mainAdmin == null){
            mainAdmin = new User();

            mainAdmin.getRoles().add(Role.ROLE_USER);
            mainAdmin.getRoles().add(Role.ROLE_ADMIN);
            mainAdmin.getRoles().add(Role.ROLE_MAINADMIN);

            mainAdmin.setActive(true);

            mainAdmin.setEmail(email);

            mainAdmin.setPassword(passwordEncoder.encode("gzznybwf1331"));

            mainAdmin.setName("Данил");

            userService.save(mainAdmin);
        }
    }
}