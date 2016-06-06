package sample.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Castle extends Canvas {
    public Castle(){
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
