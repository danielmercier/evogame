package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.game.Game;
import sample.game.Play;
import sample.game.Player;
import sample.ui.Board;

import java.util.concurrent.Callable;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Board board = new Board();
        Player p = new Player() {
            @Override
            public Play play() {
                return null;
            }
        };
        Callable<Void> played = () -> { board.update(); return null; };
        Game game = new Game(p, p, played);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(board, 800, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
