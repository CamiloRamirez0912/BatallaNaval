package co.edu.uptc.BatallaNaval.controller;

import co.edu.uptc.BatallaNaval.enums.TimerType;
import co.edu.uptc.BatallaNaval.model.Player;
import co.edu.uptc.BatallaNaval.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/register-player")
    public ResponseEntity<?> registerPlayer(@RequestBody Player player) {
        Player registeredPlayer = gameService.registerPlayer(player);
        if (registeredPlayer == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "No se pueden registrar más jugadores");
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(registeredPlayer);
    }

    @PostMapping("/timer/start/{playerId}")
    public ResponseEntity<?> startTimer(@PathVariable int playerId, @RequestParam TimerType timerType) {
        Player player = gameService.getPlayerById(playerId);
        if (player == null) {
            return ResponseEntity.notFound().build();
        }

        gameService.startTimer(playerId, timerType, () -> {
            player.setInGame(false);
        });

        return ResponseEntity.ok().build();
    }

    @GetMapping("/timer/{playerId}")
    public ResponseEntity<?> getRemainingTime(@PathVariable int playerId) {
        Integer remainingTime = gameService.getRemainingTime(playerId);
        if (remainingTime == null) {
            return ResponseEntity.notFound().build();
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("remainingTime", remainingTime);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/timer/stop/{playerId}")
    public ResponseEntity<?> stopTimer(@PathVariable int playerId) {
        gameService.stopTimer(playerId);
        return ResponseEntity.ok().build();
    }
}