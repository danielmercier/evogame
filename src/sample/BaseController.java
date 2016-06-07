package sample;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.online.Client;
import sample.online.Server;

import java.io.Closeable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class BaseController {
    @FXML private RadioButton local;
    @FXML private RadioButton server;
    @FXML private TextField port;
    @FXML private TextField ip;
    private Stage stage;
    private Closeable toClose;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    public void handleOk(){
        if(!local.isSelected()){
            if(server.isSelected()) {
                Server s = new Server(Integer.parseInt(port.getText()));
                System.out.println("DONE");
                Controller.setOnlineController(s.getSocketduserveur(), -1);
                toClose = s;
            } else {
                Client c = null;
                try {
                    c = new Client(InetAddress.getByName(ip.getText()), Integer.parseInt(port.getText()));
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                System.out.println("DONE");
                Controller.setOnlineController(c.getSocket(), 1);
                toClose = c;
            }
        } else {
            Controller.setLocalController();
        }

        this.stage.setScene(new Scene(Controller.getInstance().getRoot(), 500, 800));
        this.stage.show();
    }

    public void setToClose(Closeable toClose) {
        this.toClose = toClose;
    }
}
