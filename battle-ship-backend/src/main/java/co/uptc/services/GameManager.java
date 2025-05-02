package co.uptc.services;

import co.uptc.constants.GameStatus;
import co.uptc.models.GameRoom;
import co.uptc.models.Session;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class GameManager {
    private final GameTimerService gameTimerService;
    private final ConcurrentLinkedQueue<GameRoom> waitingGames = new ConcurrentLinkedQueue<>();
    private final ConcurrentHashMap<String, GameRoom> activeGames = new ConcurrentHashMap<>();

    public GameManager(GameTimerService gameTimerService) {
        this.gameTimerService = gameTimerService;
    }

    public synchronized Session findOrCreateGame(String username) {
        GameRoom gameRoom = waitingGames.peek();
        String playerId;
        String gameId;

        if (gameRoom == null) {
            gameId = UUID.randomUUID().toString();
            gameRoom = new GameRoom(gameId);
            playerId = gameRoom.addPlayer(username);
            waitingGames.offer(gameRoom);
        } else {
            gameId = gameRoom.getGameId();
            playerId = gameRoom.addPlayer(username);
            gameRoom.setGameStatus(GameStatus.POSIOTIONING);
            activeGames.put(gameRoom.getGameId(), gameRoom);
            waitingGames.poll();
            gameTimerService.startPlacementTimer(gameRoom);
        }
        return new Session(gameId, playerId);
    }

    public synchronized GameRoom findActiveGame(String gameId) {
        return activeGames.get(gameId);
    }

    public synchronized void cancelTimerGame(String gameId) {
        gameTimerService.cancelTimer(gameId);
    }
}
