package sample.game;

import javafx.geometry.Pos;

public class Tile extends Square {
    public void setNewPosition(Position newPosition) {
        this.currentPos = newPosition;
    }

    public
    enum Shape{
        Warrior((north, position) -> {
            Moves m = new Moves(north);
            m.add(position.getKey() + north, position.getValue());
            m.add(position.getKey(), position.getValue() + north);
            m.add(position.getKey(), position.getValue() - north);
            m.add(position.getKey() + north, position.getValue() + north);
            m.add(position.getKey() + north, position.getValue() - north);
            return m;
        }),
        Blade((north, position) -> {
            Moves m = new Moves(north);
            m.add(position.getKey() + north, position.getValue());
            m.add(position.getKey() + 2 * north, position.getValue());
            return m;
        }),
        Catapult((north, position) -> {
            Moves m = new Moves(north);
            m.add(position.getKey() + north, position.getValue() + north);
            m.add(position.getKey() + 2 * north, position.getValue() + 2 * north);
            m.add(position.getKey() + 3 * north, position.getValue() + 3 * north);
            return m;
        }),
        Shield((north, position) -> {
            Moves m = new Moves(north);
            m.add(position.getKey() - north, position.getValue());
            m.add(position.getKey() - north, position.getValue() + north);
            m.add(position.getKey() - north, position.getValue() - north);
            m.add(position.getKey() - 2 * north, position.getValue());
            return m;
        });

        private Callback2<Integer, Position, Moves> callback;

        Shape(Callback2<Integer, Position, Moves> callback){
            this.callback = callback;
        }

        public Moves possibleMoves(Integer north, Position position){
            return callback.call(north, position);
        }
    }
    private Shape currentShape;
    private Position currentPos;
    private Integer north;

    public Tile(Integer north, Position position){
        this.currentShape = Shape.Warrior;
        this.north = north;
        this.currentPos = position;
    }

    public Moves possibleMoves(){
        return this.currentShape.possibleMoves(north, this.currentPos);
    }

    public void setShape(Shape shape){
        this.currentShape = shape;
    }

    @Override
    public boolean isEmpty(){
        return false;
    }

    public Integer getNorth(){
        return north;
    }

    public Position getCurrentPos(){
        return currentPos;
    }

    public Shape getCurrentShape(){
        return currentShape;
    }
}
