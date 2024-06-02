package jkroul.bookstore.controllers;

import jkroul.bookstore.entities.User;
import jkroul.bookstore.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @PostMapping("/add-balance")
    @ResponseBody
    public String addBalance(@RequestParam("money") Double money) {
        Optional<User> userOptional = userRepository.findById(Long.valueOf(1));
        Double newBalance;
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.addBalance(money);
            userRepository.save(user);
            newBalance = (double) user.getBalance();
        } else {
            return "User not found";
        }
        return "Balance updated. New balance: " + newBalance;
    }

    @PostMapping("/use-points")
    @ResponseBody
    public String usePoints(@RequestParam("points") Double points) {
        Optional<User> userOptional = userRepository.findById(Long.valueOf(1));
        Double newPoints;
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPoints() <= points) {
                user.addBalance(points);
                user.subtractPoints(points);
            } else {
                return "Not enough points";
            }
            userRepository.save(user);
            newPoints = (double) user.getPoints();
        } else {
            return "User not found";
        }
        return "Points used. New points: " + newPoints;
    }
}