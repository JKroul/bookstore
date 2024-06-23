package jkroul.bookstore.controllers;

import jkroul.bookstore.entities.Cart;
import jkroul.bookstore.entities.User;
import jkroul.bookstore.repositories.CartRepository;
import jkroul.bookstore.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class CheckoutController {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public CheckoutController(CartRepository cartRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/checkout")
    public String listCart(Model model) {
        model.addAttribute("booksInCart", cartRepository.findById(1L));

        Optional<Cart> cartOptional =  cartRepository.findById(1L);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            model.addAttribute("totalPrice", cart.updateCartCost());
        }

        return "checkout";
    }

    @PostMapping("/purchase")
    @ResponseBody
    public String purchaseBooks() {
        Optional<Cart> cartOptional =  cartRepository.findById(1L);
        Optional<User> user = userRepository.findById(1L);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.purchaseBooks();
            cartRepository.save(cart);
            user.get().subtractBalance((long) cart.updateCartCost());
            user.get().addPoints(cart.updateCartCost()/10);
        }
        return "Books purchased";
    }
}