package sample.game;

import java.io.Serializable;

public abstract class Square {
    protected Position currentPos;
    public Square(Integer row, Integer col){
        currentPos = new Position(row, col);
    }
    public Square(Position pos){
        this.currentPos = pos;
    }
    public abstract boolean isEmpty();
    public abstract boolean isPlayable();
    public Position getCurrentPos() {
        return currentPos;
    }
}
