package org.fungover.webapi.controllers;


import org.fungover.webapi.MyUserDetails;
import org.fungover.webapi.MyUserDetailsService;
import org.fungover.webapi.entities.Game;
import org.fungover.webapi.entities.User;
import org.fungover.webapi.repositories.GameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class GameController {

    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @PostMapping("/game")
    public ResponseEntity<Void> createGame(@RequestBody Game gameEntity) {

        //TODO Validate inRequest
        gameRepository.save(gameEntity);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/game/{id}")
    public ResponseEntity<Void> updateGame(Game gameEntity, @PathVariable Long id) {

        //TODO Validate inRequest
        gameRepository.save(gameEntity);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/game")
    public List<Game> getMyGames(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      var userDetails =  (MyUserDetails) auth.getPrincipal();
        return gameRepository.findAllByPlayerOneOrPlayerTwo(userDetails.getUsername(),userDetails.getUsername());

    }






}
