package sample;

import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import sample.game.Tile;
import sample.ui.Square;

import java.net.Socket;

public abstract class Controller {
    private static Controller instance = null;
    public static Controller getInstance(){
        return instance;
    }
    public static void setLocalController(){
        if(instance == null){
            instance = new LocalController();
        }
    }
    public static void setOnlineController(Socket socket, Integer north){
        if(instance == null){
            instance = new OnlineController(socket, north);
        }
    }
    public abstract Parent getRoot();
    public abstract boolean handleSelectShape(Tile.Shape shape);
    public abstract void handleSquareClicked(MouseEvent mouseEvent, Square square);
    public abstract void handleMouseClick(MouseEvent mouseEvent);
}
