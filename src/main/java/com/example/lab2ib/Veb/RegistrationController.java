package com.example.lab2ib.Veb;


import com.example.lab2ib.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    private  UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration()
    {
        return "Registration";
    }
    @PostMapping("/registration")
    public String registration(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String cpassword,
                               @RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam String phone)
    {

           userService.registration(username,email,password,cpassword,name,surname,phone);
           return "Login";
    }
}
