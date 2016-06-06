package sample.game;

import javafx.beans.NamedArg;
import javafx.util.Pair;

public class Play extends Pair<Tile, Position> {
    public Play(@NamedArg("key") Tile key, @NamedArg("value") Position value) {
        super(key, value);
    }
}
