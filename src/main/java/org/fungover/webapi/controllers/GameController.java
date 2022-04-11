package org.fungover.webapi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.core.io.Resource;

import org.fungover.webapi.MyUserDetails;

import org.fungover.webapi.entities.Game;

import org.fungover.webapi.repositories.GameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
public class GameController {


    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @PostMapping("/game")
    public ResponseEntity<Game> createGame(@RequestBody Game gameEntity) {

        //TODO Validate inRequest
        gameRepository.save(gameEntity);


      //  List<Game> listGame = List.of(gameEntity);

        return ResponseEntity.ok((gameEntity));
    }



    @PutMapping("/game/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable("id") Long gameId,@RequestBody Game gameEntity
       ) {

      Game game = gameRepository.findById(gameId)
              .orElseThrow(() -> new InvalidConfigurationPropertyValueException("da","id","gameId"));

        //TODO Validate inRequest
        game.setGameId(gameEntity.getGameId());
        game.setMessage(gameEntity.getMessage());
        game.setPlayerOne(gameEntity.getPlayerOne());
        game.setPlayerTwo(gameEntity.getPlayerTwo());
        game.setResult(gameEntity.getResult());
        game.setChoiceOne(gameEntity.getChoiceOne());
        game.setChoiceTwo(gameEntity.getChoiceTwo());
        game.setState(gameEntity.getState());

        final Game updateGame = gameRepository.save(game);


        return ResponseEntity.ok(updateGame);
    }

    @GetMapping("/game")
    public List<Game> getMyGames(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      var userDetails =  (MyUserDetails) auth.getPrincipal();
        return gameRepository.findAllByPlayerOneOrPlayerTwo(userDetails.getUsername(),userDetails.getUsername());

    }

    @GetMapping("/all")
    public ResponseEntity<List<Game>> getAllEmployees () {
        List<Game> employees = getMyGames();
        return new ResponseEntity<>(employees, HttpStatus.OK);

}

}
