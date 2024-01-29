package com.example.lab2ib.Veb;

import com.example.lab2ib.Model.Role;
import com.example.lab2ib.Model.Users;
import com.example.lab2ib.Service.PlayerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("account")
public class PlayersController {
    private final PlayerService playerService;

    public PlayersController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping({"/", "/players"})
    public String showPlayers(Model model) {
        Users user= (Users) model.getAttribute("account");
        if(user.getRole().equals(Role.SUPER_ADMIN)) {
            model.addAttribute("playerlist", playerService.listAllPlayers());
            return "Home_SuperAdmin";
        }
        if(user.getRole().equals(Role.ADMIN)) {
            model.addAttribute("playerlist", playerService.listAllPlayers());
            return "Home_Admin";
        }
        if(user.getRole().equals(Role.USER)) {
            model.addAttribute("playerlist", playerService.listAllPlayers());
            return "Home_User";
        }
        return "Home_User";
    }


    @GetMapping("/players/add")
    public String showAdd(Model model)
    {
        Users user =(Users) model.getAttribute("account");
        if(user.getRole().equals(Role.SUPER_ADMIN)) {
            return "Form";
        }
        else
        {
            return null;
        }
    }

    @GetMapping("/players/{id}/edit")
    public String showEdit(@PathVariable(required = false) Long id, Model model) {

        model.addAttribute("player",this.playerService.findById(id));
        Users user =(Users) model.getAttribute("account");
        if(user.getRole().equals(Role.SUPER_ADMIN)) {
            return "Form";
        }
        else
        {
            return null;
        }
    }

    @PostMapping("/players")
    public String create(@RequestParam (required = false) String name,
                         @RequestParam (required = false) String bio,
                         @RequestParam (required = false) Double pointsPerGame) {
        this.playerService.create(name, bio, pointsPerGame);
        return "redirect:/players";
    }
    @PostMapping("/players/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam (required = false) String name,
                         @RequestParam (required = false) String bio,
                         @RequestParam (required = false) Double pointsPerGame, Model model) {


        model.addAttribute("player",this.playerService.findById(id));
        Users user =(Users) model.getAttribute("account");
        if(user.getRole().equals(Role.SUPER_ADMIN)) {
            this.playerService.update(id, name, bio, pointsPerGame);
            return "redirect:/players";
        }
        else
        {
            return null;
        }
    }

    @PostMapping("/players/{id}/delete")
    public String delete(@PathVariable (required = false) Long id, Model model) {
        model.addAttribute("player",this.playerService.findById(id));
        Users user =(Users) model.getAttribute("account");
        if(user.getRole().equals(Role.SUPER_ADMIN) || user.getRole().equals(Role.ADMIN)) {
            this.playerService.delete(id);
            return "redirect:/players";
        }
        else
        {
            return  null;
        }
    }

    @PostMapping("/players/{id}/vote")
    public String vote(@PathVariable (required = false) Long id) {
        this.playerService.vote(id);
        return "redirect:/players";
    }
}
