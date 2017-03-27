package hu.bme.aut.mobsoft.tourapp.ui;

/**
 * Created by valentin on 2017. 03. 27..
 */

public interface Screen {

    void showProgressBar();

    void hideProgressBar();

    void showMessage(int messageId);
}
