package hu.bme.aut.mobsoft.tourapp.ui.main;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.tourapp.interactor.tours.ToursInteractor;
import hu.bme.aut.mobsoft.tourapp.ui.Presenter;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public class MainPresenter extends Presenter<MainScreen> {

    @Inject
    ToursInteractor toursInteractor;

    public MainPresenter() {
    }

    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }


}
