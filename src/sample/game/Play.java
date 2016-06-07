package sample.game;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Play  implements Externalizable{
    private Tile tile;
    private Position newPosition;
    private Tile.Shape newShape;

    public Tile getTile() {
        return tile;
    }

    public Position getNewPosition() {
        return newPosition;
    }

    public Tile.Shape getNewShape() {
        return newShape;
    }

    public Play(Tile tile, Position newPosition, Tile.Shape newShape) {
        this.tile = tile;

        this.newPosition = newPosition;
        this.newShape = newShape;
    }
    public Play(Tile tile, Position newPosition) {
        this(tile, newPosition, tile.getCurrentShape());
    }
    public Play(Tile tile, Tile.Shape newShape) {
        this(tile, tile.getCurrentPos(), newShape);
    }
    public Play(){
    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(tile.getCurrentShape());
        out.writeObject(tile.getCurrentPos());
        out.writeObject(tile.getNorth());
        out.writeObject(newPosition);
        out.writeObject(newShape);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        Tile.Shape shape = (Tile.Shape)in.readObject();
        Position pos = (Position)in.readObject();
        Integer north = (Integer)in.readObject();
        tile = new Tile(north, pos);
        tile.setShape(shape);
        newPosition = (Position) in.readObject();
        newShape = (Tile.Shape) in.readObject();
    }
}
