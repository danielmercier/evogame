package sample.ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Empty extends Square {
    public Empty(sample.game.Square square) {
        super(square);
    }

    @Override
    protected void toDraw(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
