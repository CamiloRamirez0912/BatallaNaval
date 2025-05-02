package co.uptc.models;

import lombok.Getter;

@Getter
public class Ship {
    protected Long idShip;
    protected int size;
    protected Position position;
    protected boolean isRotated;
    protected boolean isSunk;

    public Ship(Long idShip, int size, Position position, boolean isRotated, boolean isSunk) {
        this.idShip = idShip;
        this.size = size;
        this.position = position;
        this.isRotated = isRotated;
        this.isSunk = isSunk;
    }
}
