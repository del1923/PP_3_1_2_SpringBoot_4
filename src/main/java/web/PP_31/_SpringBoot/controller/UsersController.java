package web.PP_31._SpringBoot.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.PP_31._SpringBoot.model.User;
import web.PP_31._SpringBoot.service.UserServices;
import web.PP_31._SpringBoot.util.UserValidator;


@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserValidator userValidator;

    private final UserServices userServices;

    @Autowired
    public UsersController(UserValidator userValidator, UserServices userServices) {
        this.userValidator = userValidator;
        this.userServices = userServices;
    }


    @GetMapping
    public String usersList(Model model) {
        model.addAttribute("usersList", userServices.getUserList());
        return "users/usersList";
    }

    @GetMapping("/newUser")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/newUser";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/users/newUser";
        }
        userServices.addUser(user);
        return "redirect:users";
    }

    @GetMapping("/{id}/editUser")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userServices.show(id));
        return "users/editUser";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("id") int id) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/users/editUser";
        }
        userServices.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userServices.delete(id);
        return "redirect:/users";
    }

}
