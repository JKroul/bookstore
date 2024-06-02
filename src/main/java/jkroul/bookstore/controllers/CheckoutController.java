package jkroul.bookstore.controllers;

import jkroul.bookstore.entities.Cart;
import jkroul.bookstore.entities.User;
import jkroul.bookstore.repositories.CartRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class CheckoutController {
    private final CartRepository cartRepository;
    private final User username = new User("username");

    public CheckoutController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @GetMapping("/checkout")
    public String listCart(Model model) {
        model.addAttribute("booksInCart", cartRepository.findById(username.getId()));

        Optional<Cart> cartOptional = cartRepository.findById(username.getId());
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            model.addAttribute("totalPrice", cart.updateCartCost());
        }

        return "checkout";
    }

    @PostMapping("/purchase")
    @ResponseBody
    public String purchaseBooks() {
        Optional<Cart> cartOptional = cartRepository.findById(username.getId());
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            cart.purchaseBooks();
            cartRepository.save(cart);
            username.addBalance(-cart.updateCartCost());
            username.addPoints(cart.updateCartCost()/10);
        }
        return "Books purchased";
    }
}