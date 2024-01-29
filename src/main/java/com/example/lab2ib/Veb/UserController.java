package com.example.lab2ib.Veb;

import com.example.lab2ib.Model.Role;
import com.example.lab2ib.Repository.UserRepository;
import com.example.lab2ib.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    private  final UserRepository userRepository;


    @GetMapping("/users")
    public String showUsers(Model model)
    {
        model.addAttribute("userlist", userService.getAllUsers());
        return "All_Users";
    }
    @PostMapping("/users/{id}/edit")
    public String editRole(Model model, @PathVariable Long id)
    {
        model.addAttribute("user", userRepository.findById(id));
        model.addAttribute("role", Role.values());
        return "Edit_User";
    }
    @PostMapping("/edit/{id}/save")
    public  String saveEdit(Model model, @PathVariable Long id,
                            @RequestParam (required = false) String name,
                            @RequestParam (required = false) String email,
                            @RequestParam (required = false) Role role)
    {
        userService.EditUser(id,role);
        return "redirect:/users";
    }

}
