package co.uptc.services;

import co.uptc.constants.DefaultShipPositions;
import co.uptc.constants.GameStatus;
import co.uptc.constants.PlayerStatus;
import co.uptc.models.GameRoom;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class GameTimerService {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
    private final ConcurrentHashMap<String, ScheduledFuture<?>> timers = new ConcurrentHashMap<>();

    // Iniciar temporizador para un juego
    public void startPlacementTimer(GameRoom gameRoom) {
        cancelTimer(gameRoom.getGameId());

        ScheduledFuture<?> future = scheduler.schedule(() -> {
            System.out.println("Tiempo agotado para el juego con ID: " + gameRoom.getGameId());
            gameRoom.getListPlayers().forEach(player -> {
                if (player.getPlayerStatus() != PlayerStatus.PLAYING) {
                    player.getBoard().setShips(DefaultShipPositions.getRandomDefaultPositions());
                    player.setPlayerStatus(PlayerStatus.PLAYING);
                }
            });
            gameRoom.setGameStatus(GameStatus.IN_PROGRESS);
        }, 30, TimeUnit.SECONDS);

        timers.put(gameRoom.getGameId(), future);
    }

    // Cancelar el temporizador (por ejemplo, si los jugadores terminaron antes)
    public void cancelTimer(String gameId) {
        ScheduledFuture<?> future = timers.remove(gameId);
        if (future != null && !future.isDone()) {
            future.cancel(true);
            System.out.println("Temporizador cancelado para: " + gameId);
        }
    }
}
