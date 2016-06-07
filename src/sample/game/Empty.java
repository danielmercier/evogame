package sample.game;

public class Empty extends Square{
    public Empty(Integer row, Integer col) {
        super(row, col);
    }

    public Empty(Position pos) {
        super(pos);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isPlayable() {
        return false;
    }
}
