package com.example.BGRent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class GamesController
{
    @Autowired
    private GamesRepository gamesRepository;

    // http://localhost:8080/api/games
    //
    @GetMapping(
            value = "/api/games",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional(readOnly = true)
    public ResponseEntity<List<GameEntity>> getGames() {
        return  ResponseEntity.ok(this.gamesRepository.findAll());
    }

    // http://localhost:8080/api/games/1
    //
    @GetMapping(
            value = "/api/games/{gameId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional(readOnly = true)
    public ResponseEntity<GameEntity> getGame(@PathVariable("gameId") Long gameId) {
        return ResponseEntity.of(this.gamesRepository.findById(gameId));
    }

    // POST http://localhost:8080/api/games/create
    // echo '{"gameName":"Azul","category":"1","gameDescription":"-","gamePrice":"20"}' | curl -X POST -H "Content-Type: application/json" -d @- http://localhost:8080/api/game/create
    //
    @PostMapping(
            value = "/api/games/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<GameEntity> createGame(@RequestBody GameEntity gameEntity) {
        return ResponseEntity.ok(this.gamesRepository.save(gameEntity));
    }

    // POST http://localhost:8080/api/games/1/update
    // echo '{"gameName":"Azul","category":"Abstract","gameDescription":"-","gamePrice":"25"}' | curl -X POST -H "Content-Type: application/json" -d @- http://localhost:8080/api/games/1/update
    //
    @PostMapping(
            value = "/api/games/{gameId}/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<GameEntity> updateGame(@PathVariable("gameId") Long gameId, @RequestBody GameEntity newGameEntity) {
        Optional<GameEntity> foundGameOptional = this.gamesRepository.findById(gameId);
        if (foundGameOptional.isPresent()) {
            GameEntity foundGameEntity = foundGameOptional.get();
            foundGameEntity.setName(newGameEntity.getName());
            foundGameEntity.setDescription(newGameEntity.getDescription());
            //TODO CATEGORY
            foundGameEntity.setPrice(newGameEntity.getPrice());
            this.gamesRepository.save(foundGameEntity);
        }
        return ResponseEntity.of(foundGameOptional);
    }

    // http://localhost:8080/api/games/1/remove
    //
    @GetMapping(
            value = "/api/games/{gameId}/remove",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<GameEntity> removeGame(@PathVariable("gameId") Long gameId) {
        Optional<GameEntity> foundGameOptional = this.gamesRepository.findById(gameId);
        if (foundGameOptional.isPresent()) {
            this.gamesRepository.deleteById(gameId);
        }
        return ResponseEntity.of(foundGameOptional);
    }
}
