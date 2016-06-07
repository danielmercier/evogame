package sample.game;

public class Castle extends PlayerTile {
    public Castle(Integer north, Integer row, Integer col) {
        super(north, row, col);
    }

    public Castle(Integer north, Position position) {
        super(north, position);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isPlayable() {
        return false;
    }
}
