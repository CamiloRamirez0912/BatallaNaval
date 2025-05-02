package co.uptc.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Board {
    protected List<Ship> ships;
    protected List<Shot> shots;

    public void showInfoShips(){
        ships.forEach(ship -> {
            System.out.println("BArco: " + ship.getIdShip() + " Posicion: " + ship.getPosition().posittionToString());
        });
    }
}
