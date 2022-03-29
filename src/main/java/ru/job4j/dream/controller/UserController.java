package ru.job4j.dream.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.dream.model.User;
import ru.job4j.dream.service.UserService;

import java.util.Optional;

@ThreadSafe
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/formRegistration")
    public String formRegistration(Model model) {
        return "registration";
    }

    @GetMapping("/fail")
    public String formFail(Model model) {
        return "fail";
    }

    @GetMapping("/success")
    public String success(Model model) {
        return "success";
    }

    @PostMapping("/failRedirect")
    public String failRedirect(Model model) {
        return "redirect:/formRegistration";
    }

    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute User user) {
        Optional<User> regUser = Optional.ofNullable(userService.add(user));
        if (regUser.isEmpty()) {
            model.addAttribute("message", "Пользователь с такой почтой уже существует");
            return "redirect:/fail";
        }
        return "redirect:/success";
    }
}
