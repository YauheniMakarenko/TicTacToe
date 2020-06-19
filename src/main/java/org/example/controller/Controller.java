package org.example.controller;

import org.example.TicRacToe;
import org.example.entity.Message;
import org.example.entity.PlayerMove;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class Controller {

    private List<PlayerMove> playerMoves = new ArrayList<>();
    private List<String> sides = new ArrayList<>(Arrays.asList("X", "O"));
    private TicRacToe ticTacToe = new TicRacToe();

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public PlayerMove sendMessage(@Payload PlayerMove playerMove) {
        Collections.reverse(sides);
        playerMove.setSide(sides.get(0));
        playerMoves.add(playerMove);


        if (playerMove.getSide().equals("O")) {
            ticTacToe.move(Integer.parseInt(playerMove.getX()), Integer.parseInt(playerMove.getY()), 2);
            ticTacToe.determineWinner(ticTacToe.getTicTacToeField(), 2);
        } else {
            ticTacToe.move(Integer.parseInt(playerMove.getX()), Integer.parseInt(playerMove.getY()), 1);
            ticTacToe.determineWinner(ticTacToe.getTicTacToeField(), 1);
        }

        if (Arrays.stream(ticTacToe.getTicTacToeField()).flatMapToInt(x -> Arrays.stream(x))
                .filter(num -> num == 0).count() == 0) {
            playerMove.setWinner(false);
        }

        if ((ticTacToe.determineWinner(ticTacToe.getTicTacToeField(), 1)
                || ticTacToe.determineWinner(ticTacToe.getTicTacToeField(), 2))) {
            playerMoves.removeAll(playerMoves);
            ticTacToe.refreshTicTacToeField();
            Collections.reverse(sides);
            playerMove.setWinner(true);
        }
        return playerMove;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Message addUser(@Payload Message message,
                           SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        return message;
    }

}
