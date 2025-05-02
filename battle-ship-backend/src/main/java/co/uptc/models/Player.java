package co.uptc.models;

import co.uptc.constants.PlayerStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private final String playerId;
    private final String name;
    private Board board;
    private final String gameId;
    private PlayerStatus playerStatus;

    public Player(String playerId, String name, String gameId) {
        this.playerId = playerId;
        this.name = name;
        this.gameId = gameId;
        this.playerStatus = PlayerStatus.POSITIONING;
        board = new Board();
    }

    public boolean atack(Position position, Board board) {
        return false;
    }
}
