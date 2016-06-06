package sample.game;

import java.util.ArrayList;
import java.util.Iterator;

public class Grid extends ArrayList<ArrayList<Square>>{
    public static final int height = 12;
    public static final int width = 8;

    public Square get(Position position){
        return this.get(position.getKey()).get(position.getValue());
    }

    public Iterator<Square> squareIterator(){
        return new Iterator<Square>() {
            private int row = 0;
            private int col = 0;
            @Override
            public boolean hasNext() {
                return col < Grid.width;
            }

            @Override
            public Square next() {
                if(row >= Grid.height){
                    row = 0;
                    col++;
                }

                return get(new Position(row++, col));
            }
        };
    }

    public void set(Position position, Square square){
        this.set(position.getKey(), position.getValue(), square);
    }

    public void set(Integer row, Integer col, Square square){
        this.get(row).set(col, square);
    }
}
