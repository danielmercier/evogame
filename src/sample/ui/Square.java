package sample.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.Controller;

public abstract class Square extends Canvas{
    protected sample.game.Square square;
    private double alpha;

    public Square(sample.game.Square square, boolean init){
        super(Board.squareSize, Board.squareSize);
        this.square = square;
        this.alpha = 1;
        if(init){
            init();
        }
    }

    public Square(sample.game.Square square){
        this(square, true);
    }

    protected void init(){
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.save();
        gc.setGlobalAlpha(alpha);
        toDraw(gc);
        gc.restore();
        gc.setStroke(Color.BLACK);
        gc.strokeRect(0, 0, this.getWidth(), this.getHeight());

        this.setOnMouseClicked(mouseEvent -> Controller.getInstance().handleSquareClicked(mouseEvent, this));
    }

    protected abstract void toDraw(GraphicsContext gc);

    public void setAlpha(double alpha){
        this.alpha = alpha;
    }

    public sample.game.Square getSquare() {
        return square;
    }
}
