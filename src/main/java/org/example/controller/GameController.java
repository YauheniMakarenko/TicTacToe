package org.example.controller;

import org.example.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/game")
    public String connectToGame(Model model, HttpServletRequest request) {
        String s = request.getParameter("search");
        model.addAttribute("game", gameService.findGame(s));
        return "game";
    }


    @GetMapping("/allgames")
    public String gameList(Model model) {
        model.addAttribute("allGames", gameService.allGames());
        return "allgames";
    }

    @PostMapping("/allgames")
    public String connectGame(HttpServletRequest request,
                              @RequestParam(required = true, defaultValue = "") String action,
                              Model model) {
        String s = request.getParameter("search");
        if (action.equals("search")){
            model.addAttribute("gameId", gameService.findGame(s));
            model.addAttribute("allGames", gameService.allGames());
            return "allgames";
        }
        return "redirect:/game";
    }
}
