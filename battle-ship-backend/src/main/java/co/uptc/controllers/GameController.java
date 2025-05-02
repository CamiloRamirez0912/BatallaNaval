package co.uptc.controllers;

import co.uptc.constants.PlayerStatus;
import co.uptc.dtos.FinishPosition;
import co.uptc.services.GameManager;
import co.uptc.constants.GameStatus;
import co.uptc.dtos.PlayerDtoGame;
import co.uptc.models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GameController {
    private final GameManager gameManager;

    public GameController(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @GetMapping("/gameId/{username}")
    public ResponseEntity<Session> getGameId(@PathVariable String username) {
        Session session = gameManager.findOrCreateGame(username);
        return ResponseEntity.ok(session);
    }

    @MessageMapping("/play")
    @SendToUser("/queue/position-updates")
    public InfoMessage play(PlayerDtoGame player) {
        String gameId = player.getGameId();

        GameRoom gameRoom = gameManager.findActiveGame(gameId);
        while(gameRoom == null){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            gameRoom = gameManager.findActiveGame(gameId);
        }

        assert gameRoom != null;
        if(gameRoom.getGameStatus() == GameStatus.WAITING_PLAYERS){
            return new InfoMessage(GameStatus.WAITING_PLAYERS.toString());
        } else if (gameRoom.getGameStatus() == GameStatus.POSIOTIONING) {
            return new InfoMessage(GameStatus.POSIOTIONING.toString());
        }else{
            return new InfoMessage(GameStatus.IN_PROGRESS.toString());
        }
    }

    @MessageMapping("/finish-positioning")
    @SendToUser("/queue/position-updates")
    public InfoMessage changeStateGame(FinishPosition finishPosition) {
        finishPosition.showInfoShips();
        GameRoom gameRoom = gameManager.findActiveGame(finishPosition.getGameId());
        gameRoom.getListPlayers().forEach(player -> {
            if(player.getPlayerId().equals(finishPosition.getPlayerId()) && gameRoom.getGameStatus() == GameStatus.POSIOTIONING){
                player.setPlayerStatus(PlayerStatus.PLAYING);
                gameRoom.setShipsPlayer(player.getPlayerId(), finishPosition.getShips());
                gameManager.cancelTimerGame(gameRoom.getGameId());
            }
        });

        boolean isAllPlayersReady = gameRoom.getListPlayers().stream().allMatch(player -> player.getPlayerStatus() == PlayerStatus.PLAYING);
        if (isAllPlayersReady) {
            gameRoom.setGameStatus(GameStatus.IN_PROGRESS);
            return new InfoMessage(GameStatus.IN_PROGRESS.toString());
        } else {
            return new InfoMessage(GameStatus.POSIOTIONING.toString());
        }
    }

    @GetMapping("/name-opponent/{username}/{gameId}")
    public ResponseEntity<String> nameOpponent(@PathVariable String username, @PathVariable String gameId) {
        GameRoom gameRoom = gameManager.findActiveGame(gameId);
        String nameOpponent = gameRoom.getListPlayers().stream()
                .map(Player::getName)
                .filter(name -> !name.equals(username))
                .findFirst()
                .orElse(null);
        return ResponseEntity.ok(nameOpponent);
    }
}