package sample.ui;

import sample.game.Play;

public class Player extends sample.game.Player {
    private Play toPlay;

    public Player(Integer north) {
        super(north);
        toPlay = null;
    }

    @Override
    public synchronized Play play() {
        toPlay = null;
        while(toPlay == null){
            try {
                wait(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return toPlay;
    }

    public synchronized void setToPlay(Play toPlay){
        this.toPlay = toPlay;
    }
}
