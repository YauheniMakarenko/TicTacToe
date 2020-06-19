package org.example.service;

import org.example.entity.Game;
import org.example.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;


    public List<Game> allGames() {
        return gameRepository.findAll();
    }

    public boolean saveGame(Game game) {
        Game gameFromDB = gameRepository.findGameByGamename(game.getGamename());

        if (gameFromDB != null) {
            return false;
        }
        gameRepository.save(game);
        return true;
    }

    public boolean deleteGame(Long gameId) {
        if (gameRepository.findById(gameId).isPresent()) {
            gameRepository.deleteById(gameId);
            return true;
        }
        return false;
    }

    public Game findGame(String s){
        return gameRepository.findGameByGamename(s);
    }

}
