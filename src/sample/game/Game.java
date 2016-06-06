package sample.game;

import javafx.util.Callback;

import java.util.concurrent.Callable;

public class Game implements Runnable {
    private Player[] players;
    private Callable<Void> played;
    private Board board;
    private void initBoard(){
        this.board = Board.getInstance();
        Grid grid = this.board.getGrid();

        for(int i = 0 ; i < Grid.height ; i++){
            for(int j = 0 ; j < Grid.width ; j++){
                grid.set(i, j, new Empty());
            }
        }

        grid.set(0, 0, new Castle());
        grid.set(1, 0, new Tile(1, new Position(1, 0)));
        for(int i = 1 ; i <= 3 ; i++){
            grid.set(0, i, new Tile(1, new Position(0, i)));
            grid.set(1, i, new Tile(1, new Position(1, i)));
        }
        grid.set(0, 4, new Tile(1, new Position(0, 4)));

        grid.set(Grid.height - 1, Grid.width - 1, new Castle());
        grid.set(Grid.height - 2, Grid.width - 1, new Tile(-1, new Position(Grid.height - 2, Grid.width - 1)));
        for(int i = 1 ; i <= 3 ; i++){
            grid.set(Grid.height - 1, Grid.width - i - 1, new Tile(-1, new Position(Grid.height - 1, Grid.width - i - 1)));
            grid.set(Grid.height - 2, Grid.width - i - 1, new Tile(-1, new Position(Grid.height - 2, Grid.width - i - 1)));
        }
        grid.set(Grid.height - 1, Grid.width - 5, new Tile(-1, new Position(Grid.height - 1, Grid.width - 5)));
    }

    public Game(Player player1, Player player2, Callable<Void> played){
        players = new Player[2];

        players[0] = player1;
        players[1] = player2;

        this.played = played;

        initBoard();
    }

    @Override
    public void run() {
        Board board = Board.getInstance();
        int current = 9;
        while(true){
            Play play = players[current].play();
            board.move(play);
            current = 1 - current;
            try {
                played.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Board getBoard(){
        return board;
    }
}
