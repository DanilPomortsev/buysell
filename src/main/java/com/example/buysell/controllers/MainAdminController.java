package com.example.buysell.controllers;

import com.example.buysell.models.User;
import com.example.buysell.models.enums.Role;
import com.example.buysell.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainAdminController {
    private final MainAdminService mainAdminService;

    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        mainAdminService.banUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Arrays.stream(Role.values()).filter((element) ->
                element.equals(Role.valueOf("ROLE_ADMIN"))||element.equals(Role.valueOf("ROLE_MAINADMIN"))
        ).collect(Collectors.toList()));
        return "user-edit";
    }

    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") User user, @RequestParam Map<String, String> form) {
        mainAdminService.changeUserRoles(user, form);
        return "redirect:/admin";
    }
}
