package sample.ui;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import sample.Controller;
import sample.game.*;

import java.util.ArrayList;
import java.util.Iterator;

public class Board extends GridPane{
    public static final int squareSize = 50;
    private sample.game.Board board;
    private StackPane[][] panes;
    public Board(sample.game.Board board){
        this();
        this.board = board;
    }
    public Board(){
        this.setGridLinesVisible(true);
        this.setOnMouseClicked(mouseEvent -> Controller.getInstance().handleMouseClick(mouseEvent));

        panes = new StackPane[Grid.height][Grid.width];
        for(int i = 0 ; i < Grid.height ; i++) {
            for (int j = 0; j < Grid.width; j++) {
                panes[i][j] = new StackPane();
                this.add(panes[i][j], j, i);
            }
        }
    }

    public void setBoard(sample.game.Board board){
        this.board = board;
    }

    public void update(){
        for(int i = 0 ; i < Grid.height ; i++){
            for(int j = 0 ; j < Grid.width ; j++){
                panes[i][j].getChildren().clear();
                sample.game.Square sq = this.board.getGrid().get(new Position(i, j));
                if(sq.isEmpty()){
                    panes[i][j].getChildren().add(new Empty(sq));
                } else if(sq instanceof sample.game.Castle) {
                    panes[i][j].getChildren().add(new Castle(sq));
                } else {
                    sample.game.Tile t = (sample.game.Tile)sq;
                    panes[i][j].getChildren().add(new Tile(t));
                }
            }
        }
    }

    public StackPane getStackPane(int row, int col){
        return panes[row][col];
    }
    public StackPane getStackPane(Position pos){
        return panes[pos.getKey()][pos.getValue()];
    }
}
