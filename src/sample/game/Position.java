package sample.game;

import javafx.beans.NamedArg;
import javafx.util.Pair;

public class Position extends Pair<Integer, Integer> {
    public Position(@NamedArg("key") Integer key, @NamedArg("value") Integer value) {
        super(key, value);
    }
}
