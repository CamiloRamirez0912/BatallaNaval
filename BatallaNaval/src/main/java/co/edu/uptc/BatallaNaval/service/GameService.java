package co.edu.uptc.BatallaNaval.service;

import co.edu.uptc.BatallaNaval.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    
    private List<Player> players = new ArrayList<>();
    
    public Player registerPlayer(Player player) {
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
}