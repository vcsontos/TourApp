package hu.bme.aut.mobsoft.tourapp.interactor.tours;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.tourapp.TourApplication;
import hu.bme.aut.mobsoft.tourapp.repository.Repository;

/**
 * Created by mobsoft on 2017. 04. 24..
 */

public class ToursInteractor {

    @Inject
    Repository repository;

    public ToursInteractor() {
        TourApplication.injector.inject(this);
    }
}
