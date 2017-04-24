package hu.bme.aut.mobsoft.tourapp.interactor.login;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.tourapp.TourApplication;
import hu.bme.aut.mobsoft.tourapp.repository.Repository;

/**
 * Created by mobsoft on 2017. 04. 24..
 */

public class LoginInteractor {

    @Inject
    Repository repository;

    public LoginInteractor() {
        TourApplication.injector.inject(this);
    }
}
