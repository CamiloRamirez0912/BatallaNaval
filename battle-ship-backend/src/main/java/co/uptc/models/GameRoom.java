package co.uptc.models;

import co.uptc.constants.GameStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class GameRoom {

    protected String gameId;
    protected List<Player> listPlayers;
    protected Player currentTurn;
    protected Player winner;
    private GameStatus gameStatus;
    private Board board;

    public GameRoom(String idGame) {
        this.gameId = idGame;
        this.gameStatus = GameStatus.WAITING_PLAYERS;
        listPlayers = new ArrayList<>();

    }

    public void setShipsPlayer(String playerId, List<Ship> ships) {
        listPlayers.stream().filter(player -> player.getPlayerId().equals(playerId)).findFirst().ifPresent(player -> player.getBoard().setShips(ships));
    }

    public void endGame(){

    }

    public void switchTurn(){

    }

    public String addPlayer(String username){
        String playerId = UUID.randomUUID().toString();
        Player newPlayer = new Player(playerId, username, gameId);
        listPlayers.add(newPlayer);
        return playerId;
    }

    public String getInfoPlayers(){
        String infoPlayers = "";
        for (Player player : listPlayers) {
            infoPlayers +=  player.getPlayerId() + "-" + player.getName() + "/";
        }
        return infoPlayers;
    }
}
