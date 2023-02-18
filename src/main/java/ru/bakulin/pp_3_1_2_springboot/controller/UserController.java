package ru.bakulin.pp_3_1_2_springboot.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bakulin.pp_3_1_2_springboot.model.User;
import ru.bakulin.pp_3_1_2_springboot.service.UserService;


@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUsers(Model model) {
        //Получим всех людей из DAO и передадим на отображение в представлении
        model.addAttribute("usersList", userService.readUsers());
        return "users";
    }

    @GetMapping("/{id}")
    public String showIdUser(@PathVariable("id") long id, Model model) {
        //Получим одного человека по id из DAO и передадим на отображение в представление
        model.addAttribute("user", userService.readUserId(id));
        return "show_user";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("usernew", new User());
        return "new_user";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("usernew") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new_user";
        }
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.readUserId(id));
        return "user_edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "user_edit";
        }
        userService.updateUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
