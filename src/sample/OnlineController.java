package sample;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sample.game.*;
import sample.game.Tile;
import sample.ui.*;
import sample.ui.Board;
import sample.ui.Empty;
import sample.ui.Player;
import sample.ui.Square;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class OnlineController extends Controller {
    private List<StackPane> previous;
    private Tile selected;
    private Position selectedPosition;
    private Board board;
    private VBox vbox;
    private Canvas overLap;
    private Game game;
    private Label whoPlays;
    private Integer me;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Task<Void> task;

    public OnlineController(Socket socket, Integer north){
        this.socket = socket;
        try {
            out = new ObjectOutputStream(this.socket.getOutputStream());
            in = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        me = north;
        task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true){
                    System.out.println("Trying to get Object.");
                    Play p = null;
                    try {
                        p = (Play) in.readObject();
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                    System.out.println("...");
                    if(p != null){
                        Play finalP = p;
                        Platform.runLater(() -> {
                            game.play(finalP);
                            update();
                        });
                    }
                }
            }
        };
        previous = new ArrayList<>();
        selected = null;
        game = new Game(new Player(-1), new Player(1));

        this.board = new Board();
        this.board.setBoard(game.getBoard());
        this.board.update();

        overLap = new Canvas(800, 800);
        overLap.setMouseTransparent(true);

        whoPlays = new Label();
        Font f = new Font(20);
        whoPlays.setFont(f);
        vbox = new VBox(whoPlays, board);
        vbox.setAlignment(Pos.CENTER);
        this.board.setAlignment(Pos.CENTER);
        updateLabel();

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Parent getRoot(){
        return vbox;
    }

    private void updateLabel(){
        if(game.currentPlayer().getNorth() == -1){
            whoPlays.setTextFill(Color.BLUE);
            whoPlays.setText("BLUE, it's you're turn");
        } else {
            whoPlays.setTextFill(Color.RED);
            whoPlays.setText("RED, it's you're turn");
        }
    }

    private void clearAll(){
        clearPrevious();
        clearSelect();
    }
    private void clearPrevious(){
        for(StackPane sp : previous){
            sp.getChildren().remove(1);
        }
        previous.clear();
    }

    private void clearSelect(){
        selected = null;
        selectedPosition = null;
        if(vbox.getChildren().size() > 2) {
            vbox.getChildren().remove(2);
        }
    }

    private void select(Square uiSquare, Tile tile){
        selected = tile;
        double x = uiSquare.getWidth() * tile.getCurrentPos().getValue();
        double y = uiSquare.getHeight() * tile.getCurrentPos().getKey();
        HBox shapes = new HBox();

        shapes.setAlignment(Pos.CENTER);
        shapes.setSpacing(40);
        for(Tile.Shape shape : Tile.Shape.values()){
            if(shape != tile.getCurrentShape()){
                System.out.println(shape.toString());
                shapes.getChildren().add(new ShapeButton(shape));
            }
        }
        vbox.getChildren().add(shapes);
    }

    public void handleMouseClick(MouseEvent mouseEvent){
        if(mouseEvent.getButton() == MouseButton.SECONDARY){
            clearAll();
        }
    }

    public void handleSquareClicked(MouseEvent mouseEvent, Square uiSquare){
        if(game.currentPlayer().getNorth() == me) {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                if (selectedPosition != null && uiSquare.getSquare() == selected) {
                    handleSelectShape(null);
                } else {
                    sample.game.Square square = uiSquare.getSquare();
                    if (square.isPlayable() && ((Tile) square).getNorth() == game.currentPlayer().getNorth()) {
                        clearAll();
                        Tile tile = (Tile) square;
                        select(uiSquare, tile);

                        for (Position pos : tile.possibleMoves()) {
                            StackPane sp = board.getStackPane(pos);

                            Canvas c = new Canvas(Board.squareSize, Board.squareSize);
                            c.setMouseTransparent(true);
                            GraphicsContext gc = c.getGraphicsContext2D();
                            gc.setFill(Color.BROWN);
                            gc.setGlobalAlpha(0.2);
                            gc.fillRect(0, 0, c.getWidth(), c.getHeight());

                            sp.getChildren().add(c);

                            previous.add(sp);
                        }
                    } else if (selected != null) {
                        Position pos = square.getCurrentPos();

                        if (selected.possibleMoves().contains(pos)) {
                            selectedPosition = pos;
                            clearPrevious();
                            StackPane spTile = board.getStackPane(selected.getCurrentPos());
                            StackPane spTo = board.getStackPane(selectedPosition);
                            previous.add(spTile);
                            previous.add(spTo);

                            Empty empty = new Empty(selected);
                            spTile.getChildren().add(empty);

                            sample.ui.Tile tile = new sample.ui.Tile(selected);
                            spTo.getChildren().add(tile);
                        }
                    }
                }
            }
        }
    }

    public boolean handleSelectShape(Tile.Shape shape) {
        Play play = null;
        if(shape == null && selectedPosition != null){
            play = new Play(selected, selectedPosition);
        } else if(selectedPosition == null && shape != null){
            play = new Play(selected, shape);
        } else if(selectedPosition != null) {
            play = new Play(selected, selectedPosition, shape);
        }

        if(play != null){
            try {
                System.out.println("Writing object");
                out.writeObject(play);
            } catch (IOException e) {
                e.printStackTrace();
            }
            game.play(play);
            this.update();
            return true;
        } else {
            return false;
        }
    }

    private void update() {
        clearAll();
        this.board.update();
        this.updateLabel();
    }
}
