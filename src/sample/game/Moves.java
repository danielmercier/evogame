package sample.game;

import java.util.ArrayList;

public class Moves extends ArrayList<Position>{
    private Integer north;
    public Moves(Integer north){
        this.north = north;
    }

    public void add(Integer row, Integer col){
        Position pos = new Position(row, col);
        Square sq = Board.getInstance().getGrid().get(pos);
        if(row >= 0 && row < Grid.height && col >= 0 && col < Grid.width &&
                (sq.isEmpty() || ((AbstractTile)sq).getNorth() != this.north)){
            add(pos);
        }
    }
}
