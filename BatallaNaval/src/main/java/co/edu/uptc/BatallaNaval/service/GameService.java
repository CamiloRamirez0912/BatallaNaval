package co.edu.uptc.BatallaNaval.service;

import co.edu.uptc.BatallaNaval.enums.TimerType;
import co.edu.uptc.BatallaNaval.model.Player;
import co.edu.uptc.BatallaNaval.model.Timer;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameService {
    
    private List<Player> players = new ArrayList<>();
    private Map<Integer, Timer> playerTimers = new HashMap<>();
    
    public Player registerPlayer(Player player) {
        System.out.println("Jugador creado con el usuario: " + player.getUsername());

        if (players.isEmpty()) {
            player.setId(1);
        } else if (players.size() == 1) {
            player.setId(2);
        } else {
            return null;
        }
        players.add(player);
        return player;
    }
    
    public List<Player> getAllPlayers() {
        return players;
    }
    
    public Player getPlayerById(int id) {
        for (Player player : players) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }

    public void startTimer(int playerId, TimerType timerType, Runnable onTimeUp) {
        Timer existingTimer = playerTimers.get(playerId);
        if (existingTimer != null) {
            existingTimer.stopTimer();
        }

        Timer timer = new Timer(timerType, onTimeUp);
        playerTimers.put(playerId, timer);
        timer.start();
    }

    public Integer getRemainingTime(int playerId) {
        Timer timer = playerTimers.get(playerId);
        return timer != null ? timer.getRemainingTime() : null;
    }

    public void stopTimer(int playerId) {
        Timer timer = playerTimers.get(playerId);
        if (timer != null) {
            timer.stopTimer();
            playerTimers.remove(playerId);
        }
    }
}