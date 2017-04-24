package hu.bme.aut.mobsoft.tourapp.ui.new_tour;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.tourapp.interactor.tours.ToursInteractor;
import hu.bme.aut.mobsoft.tourapp.ui.Presenter;

/**
 * Created by valentin on 2017. 03. 27..
 */

public class NewTourPresenter extends Presenter<NewTourScreen> {

    @Inject
    ToursInteractor toursInteractor;

    public NewTourPresenter() {
    }

    @Override
    public void attachScreen(NewTourScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

}
