package sample.ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Tile extends Square {
    public static Image getShapeImage(sample.game.Tile.Shape shape){
        String img = "";
        switch (shape){
            case Warrior:
                img = "warrior.png";
                break;
            case Blade:
                img = "blade.png";
                break;
            case Catapult:
                img = "catapult.png";
                break;
            case Shield:
                img = "shield.png";
                break;
        }
        return new Image(Tile.class.getResource(img).toString());
    }
    public Tile(sample.game.Tile tile){
        super(tile);
    }

    @Override
    protected void toDraw(GraphicsContext gc) {
        sample.game.Tile tile = (sample.game.Tile)square;
        Image img = getShapeImage(tile.getCurrentShape());
        if(tile.getNorth() == 1){
            gc.translate(this.getWidth() / 2, this.getHeight() / 2);
            gc.rotate(180);
            gc.translate(-this.getWidth() / 2, -this.getHeight() / 2);
        }
        gc.drawImage(img, 0, 0, this.getWidth(), this.getHeight());
        gc.setGlobalAlpha(0.2);
        gc.setFill((((sample.game.Tile) this.square).getNorth() == -1) ? Color.BLUE : Color.RED);
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
