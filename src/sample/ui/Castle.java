package sample.ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Castle extends Square {
    public Castle(sample.game.Square square) {
        super(square);
    }

    @Override
    protected void toDraw(GraphicsContext gc) {
        gc.setFill((((sample.game.Castle) this.square).getNorth() == -1) ? Color.BLUE : Color.RED);
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
