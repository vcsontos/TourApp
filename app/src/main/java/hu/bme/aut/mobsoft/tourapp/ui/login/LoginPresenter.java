package hu.bme.aut.mobsoft.tourapp.ui.login;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.tourapp.interactor.login.LoginInteractor;
import hu.bme.aut.mobsoft.tourapp.ui.Presenter;

/**
 * Created by valentin on 2017. 03. 27..
 */

public class LoginPresenter extends Presenter<LoginScreen> {

    @Inject
    LoginInteractor loginInteractor;

    public LoginPresenter() {
    }

    @Override
    public void attachScreen(LoginScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

}
