package hu.bme.aut.mobsoft.tourapp.interactor.login.events;

import hu.bme.aut.mobsoft.tourapp.interactor.login.LoginStatus;
import hu.bme.aut.mobsoft.tourapp.model.User;

/**
 * Created by vcsontos on 2017. 05. 12..
 */

public class LoginEvent {

    private LoginStatus status;
    private User user;
    private Throwable throwable;

    public LoginEvent() {
    }

    public LoginStatus getStatus() {
        return status;
    }

    public void setStatus(LoginStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

}
