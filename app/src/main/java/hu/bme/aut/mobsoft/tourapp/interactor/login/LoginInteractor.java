package hu.bme.aut.mobsoft.tourapp.interactor.login;

import de.greenrobot.event.EventBus;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.tourapp.TourApplication;
import hu.bme.aut.mobsoft.tourapp.interactor.login.events.LoginEvent;
import hu.bme.aut.mobsoft.tourapp.model.User;
import hu.bme.aut.mobsoft.tourapp.repository.Repository;

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
            User user = repository.getUser(username, password);
            if (user == null) {
                event.setCode(404);
            } else {
                event.setCode(200);
                event.setUser(user);
            }
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
