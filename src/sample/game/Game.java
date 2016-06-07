package sample.game;

import javafx.util.Callback;

import java.util.concurrent.Callable;

public class Game {
    private Player[] players;
    private Board board;
    private Integer current;
    private void initBoard(){
        this.board = Board.getInstance();
        Grid grid = this.board.getGrid();

        for(int i = 0 ; i < Grid.height ; i++){
            for(int j = 0 ; j < Grid.width ; j++){
                grid.set(i, j, new Empty(i, j));
            }
        }

        grid.set(0, 0, new Castle(1, 0, 0));
        grid.set(1, 0, new Tile(1, new Position(1, 0)));
        for(int i = 1 ; i <= 3 ; i++){
            grid.set(0, i, new Tile(1, new Position(0, i)));
            grid.set(1, i, new Tile(1, new Position(1, i)));
        }
        grid.set(0, 4, new Tile(1, new Position(0, 4)));

        grid.set(Grid.height - 1, Grid.width - 1, new Castle(-1, Grid.height - 1, Grid.width - 1));
        grid.set(Grid.height - 2, Grid.width - 1, new Tile(-1, new Position(Grid.height - 2, Grid.width - 1)));
        for(int i = 1 ; i <= 3 ; i++){
            grid.set(Grid.height - 1, Grid.width - i - 1, new Tile(-1, new Position(Grid.height - 1, Grid.width - i - 1)));
            grid.set(Grid.height - 2, Grid.width - i - 1, new Tile(-1, new Position(Grid.height - 2, Grid.width - i - 1)));
        }
        grid.set(Grid.height - 1, Grid.width - 5, new Tile(-1, new Position(Grid.height - 1, Grid.width - 5)));

        //((Tile)grid.get(1, 0)).setShape(Tile.Shape.Blade);
        //((Tile)grid.get(1, 1)).setShape(Tile.Shape.Catapult);
        //((Tile)grid.get(1, 2)).setShape(Tile.Shape.Shield);
        //((Tile)grid.get(Grid.height - 1, Grid.width - 5)).setShape(Tile.Shape.Blade);
        //((Tile)grid.get(Grid.height - 1, Grid.width - 4)).setShape(Tile.Shape.Catapult);
        //((Tile)grid.get(Grid.height - 1, Grid.width - 3)).setShape(Tile.Shape.Shield);
    }

    public Game(Player player1, Player player2){
        players = new Player[2];

        current = 0;
        players[0] = player1;
        players[1] = player2;

        initBoard();
    }

    public void play(Play play){
        board.move(play.getTile(), play.getNewPosition());
        play.getTile().setShape(play.getNewShape());
        current = 1 - current;
    }

    public Board getBoard(){
        return board;
    }

    public Player currentPlayer(){
        return players[current];
    }
}
