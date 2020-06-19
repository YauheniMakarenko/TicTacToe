package org.example.controller;

import javax.validation.Valid;

import org.example.entity.Game;
import org.example.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CreateGameController {
    @Autowired
    private GameService gameService;

    @GetMapping("/creategame")
    public String createGame(Model model) {
        model.addAttribute("gameForm", new Game());
        return "creategame";
    }

    @PostMapping("/creategame")
    public String addGame(@ModelAttribute("gameForm") @Valid Game gameForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "creategame";
        }

        if (!gameService.saveGame(gameForm)) {
            model.addAttribute("gamenameError", "A game with the same name already exists");
            return "creategame";
        }
        return "redirect:/game";
    }
}
