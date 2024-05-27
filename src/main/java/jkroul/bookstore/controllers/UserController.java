package jkroul.bookstore.controllers;

import jkroul.bookstore.entities.User;
import jkroul.bookstore.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/userpage")
    public String userPage(Model model) {
        Optional<User> userOptional = userRepository.findById(Long.valueOf(1));
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);
            model.addAttribute("userName", user.getUsername());
            model.addAttribute("balance", user.getBalance());
            model.addAttribute("points", user.getPoints());
        }
        return "userpage";
    }
}