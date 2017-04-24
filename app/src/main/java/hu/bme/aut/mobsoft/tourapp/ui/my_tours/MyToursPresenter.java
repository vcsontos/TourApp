package hu.bme.aut.mobsoft.tourapp.ui.my_tours;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.tourapp.interactor.tours.ToursInteractor;
import hu.bme.aut.mobsoft.tourapp.ui.Presenter;

/**
 * Created by valentin on 2017. 03. 27..
 */

public class MyToursPresenter extends Presenter<MyToursScreen> {

    @Inject
    ToursInteractor toursInteractor;

    public MyToursPresenter() {
    }

    @Override
    public void attachScreen(MyToursScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

}
