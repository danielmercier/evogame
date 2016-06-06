package sample.game;

public class Board {
    private Grid grid;

    private Board(){
        grid = new Grid();
    }

    public void move(Tile t, Position newPos){
        this.grid.set(t.getCurrentPos(), new Empty());
        this.grid.set(newPos, t);
        t.setNewPosition(newPos);
    }

    public void move(Play play){
        this.move(play.getKey(), play.getValue());
    }

    public Grid getGrid(){
        return grid;
    }

    private static Board instance = null;
    public static Board getInstance(){
        if(instance == null){
            instance = new Board();
        }
        return instance;
    }
}
