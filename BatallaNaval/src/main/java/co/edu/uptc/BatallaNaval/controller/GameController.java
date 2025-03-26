package co.edu.uptc.BatallaNaval.controller;

import co.edu.uptc.BatallaNaval.model.Player;
import co.edu.uptc.BatallaNaval.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/register-player")
    public ResponseEntity<Player> registerPlayer(@RequestBody Player player) {
        Player registeredPlayer = gameService.registerPlayer(player);
        return ResponseEntity.ok(registeredPlayer);
    }
}