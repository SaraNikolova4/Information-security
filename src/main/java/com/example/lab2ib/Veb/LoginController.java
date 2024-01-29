package com.example.lab2ib.Veb;

import com.example.lab2ib.Model.Role;
import com.example.lab2ib.Model.Users;
import com.example.lab2ib.Repository.UserRepository;
import com.example.lab2ib.Service.PlayerService;
import com.example.lab2ib.Service.UserService;
import com.example.lab2ib.encrypt;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
//@SessionAttributes("account")
public class LoginController {
    private final PlayerService playerService;

    private final UserService userService;
    private final UserRepository userRepository;

    public LoginController(PlayerService playerService, UserService userService, UserRepository userRepository) {
        this.playerService = playerService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login() {

        return "Login";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        Users users = (Users) session.getAttribute("account");
        session.removeAttribute("account");
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String log(@RequestParam String email,
                      @RequestParam String password, Model model, HttpSession httpSession) {
        if (userService.najava(email, password)) {
            String novpass = encrypt.generatespassword(password, userRepository.findByEmail(email).get().getRandom());
            Users user = userRepository.findByEmailAndPassword(email, novpass);
            httpSession.setAttribute("account", user);
            System.out.println(user.getName());
        }
        return "redirect:/players";
    }
}
