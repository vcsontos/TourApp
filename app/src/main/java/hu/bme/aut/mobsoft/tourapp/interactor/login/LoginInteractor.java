package hu.bme.aut.mobsoft.tourapp.interactor.login;

import de.greenrobot.event.EventBus;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.tourapp.TourApplication;
import hu.bme.aut.mobsoft.tourapp.interactor.login.events.LoginEvent;
import hu.bme.aut.mobsoft.tourapp.model.User;
import hu.bme.aut.mobsoft.tourapp.repository.Repository;
import hu.bme.aut.mobsoft.tourapp.utils.Constants;
import hu.bme.aut.mobsoft.tourapp.utils.Validator;

/**
 * Created by mobsoft on 2017. 04. 24..
 */

public class LoginInteractor {

    @Inject
    Repository repository;

    @Inject
    EventBus bus;

    public LoginInteractor() {
        TourApplication.injector.inject(this);
    }

    public void login(String username, String password) {
        LoginEvent event = new LoginEvent();
        try {
            if (Validator.validLoginUsername(username) && Validator.validLoginPassword(password)) {
                User user = repository.getUser(username, password);
                if (user == null) {
                    event.setStatus(LoginStatus.USER_NOT_FOUND);
                } else {
                    event.setStatus(LoginStatus.USER_FOUND);
                    event.setUser(user);
                }
            } else {
                event.setStatus(LoginStatus.INVALID_CREDENTIALS);
            }
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
