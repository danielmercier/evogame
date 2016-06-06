package sample.ui;

import javafx.scene.layout.GridPane;
import sample.game.*;

import java.util.ArrayList;
import java.util.Iterator;

public class Board extends GridPane{
    private sample.game.Board board;
    public Board(sample.game.Board board){
        this.board = board;
    }
    public Board(){
        this.board = board;
    }

    public void setBoard(sample.game.Board board){
        this.board = board;
    }

    public void update(){
        for(int i = 0 ; i < Grid.height ; i++){
            for(int j = 0 ; j < Grid.width ; j++){
                Square sq = this.board.getGrid().get(new Position(i, j));
                if(sq.isEmpty()){
                    this.add(new Empty(), i, j);
                } else if(sq instanceof sample.game.Castle) {
                    this.add(new Castle(), i, j);
                } else {
                    sample.game.Tile t = (sample.game.Tile)sq;
                    this.add(new Tile(t), i, j);
                }
            }
        }
    }
}
