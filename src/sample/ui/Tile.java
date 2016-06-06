package sample.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Tile extends Canvas {
    public Tile(sample.game.Tile tile){
        GraphicsContext gc = this.getGraphicsContext2D();
        String img = "";
        switch (tile.getCurrentShape()){
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
        gc.drawImage(new Image(getClass().getResource(img).toString()), 0, 0, this.getWidth(), this.getHeight());
    }
}
