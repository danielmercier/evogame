package sample.game;

public abstract class PlayerTile extends Square{
    protected Integer north;

    public PlayerTile(Integer north, Integer row, Integer col){
        super(row, col);
        this.north = north;
    }
    public PlayerTile(Integer north, Position position){
        this(north, position.getKey(), position.getValue());
    }
    public Integer getNorth(){
        return north;
    }
}
