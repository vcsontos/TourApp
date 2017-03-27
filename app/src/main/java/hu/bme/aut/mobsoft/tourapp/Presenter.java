package hu.bme.aut.mobsoft.tourapp;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public abstract class Presenter<S> {
    protected S screen;

    public void attachScreen(S screen) {
        this.screen = screen;
    }

    public void detachScreen() {
        this.screen = null;
    }
}
