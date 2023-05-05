package org.launchcode.spaday.controllers;

import org.launchcode.spaday.data.UserData;
import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {
    @GetMapping
    public String displayUsers(Model model){
        model.addAttribute("users", UserData.getAll());
        return "user/index";
    }

    @GetMapping("add")
    public String displayAddUserForm(Model model){
        model.addAttribute("users", UserData.getAll());
        return "user/add";
    }
    @PostMapping("add")
    public String processAddUserForm(Model model, @ModelAttribute User newUser, @RequestParam String verify) {
        boolean noMatch = false;
        model.addAttribute("user", newUser);
        model.addAttribute("username", newUser.getUsername());
        model.addAttribute("email", newUser.getEmail());
        if (verify.equals(newUser.getPassword())) {
            model.addAttribute("noMatch", false);
            UserData.add(newUser);
            model.addAttribute("users", UserData.getAll());
            return "user/index";
        } else {
            model.addAttribute("noMatch", true);
            return "user/add";
        }
    }
}
