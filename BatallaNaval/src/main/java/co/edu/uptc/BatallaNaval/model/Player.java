package co.edu.uptc.BatallaNaval.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private int id;
    private String username;
    private boolean inGame;
    
    public Player(String username) {
        this.username = username;
        this.inGame = false;
    }
}