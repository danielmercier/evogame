package sample.ui;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import sample.Controller;
import sample.game.*;
import sample.game.Tile;


public class ShapeButton extends Button {
    private Tile.Shape shape;
    public ShapeButton(Tile.Shape shape){
        this.shape = shape;
        this.setOnAction(action -> Controller.getInstance().handleSelectShape(shape));
        ImageView img = new ImageView(sample.ui.Tile.getShapeImage(shape));
        img.setFitWidth(Board.squareSize);
        img.setFitHeight(Board.squareSize);
        this.setGraphic(img);
    }
}
