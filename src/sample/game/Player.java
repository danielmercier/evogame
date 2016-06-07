package sample.game;

public abstract class Player {
    protected Integer north;
    public Player(Integer north){
        this.north = north;
    }

    public Integer getNorth() {
        return north;
    }

    public abstract Play play();
}
