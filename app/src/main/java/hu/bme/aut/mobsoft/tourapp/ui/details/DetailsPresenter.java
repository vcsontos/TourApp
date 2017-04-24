package hu.bme.aut.mobsoft.tourapp.ui.details;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.tourapp.interactor.tours.ToursInteractor;
import hu.bme.aut.mobsoft.tourapp.ui.Presenter;

/**
 * Created by valentin on 2017. 03. 27..
 */

public class DetailsPresenter extends Presenter<DetailsScreen> {

    @Inject
    ToursInteractor toursInteractor;

    public DetailsPresenter() {
    }

    @Override
    public void attachScreen(DetailsScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

}
