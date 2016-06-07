package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.game.Game;
import sample.game.Play;
import sample.game.Player;
import sample.online.Client;
import sample.online.Server;
import sample.ui.Board;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.concurrent.Callable;

public class Main extends Application {
    public Closeable toClose = null;
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream("sample.fxml"));
        primaryStage.setOnCloseRequest(event -> {
            if(toClose != null) {
                try {
                    toClose.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        BaseController controller = loader.getController();
        controller.setStage(primaryStage);
        controller.setToClose(toClose);
        primaryStage.setTitle("Evo");
        primaryStage.setScene(new Scene(root, 250, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
