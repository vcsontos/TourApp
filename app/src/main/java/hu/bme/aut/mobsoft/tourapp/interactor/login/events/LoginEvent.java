package hu.bme.aut.mobsoft.tourapp.interactor.login.events;

import hu.bme.aut.mobsoft.tourapp.model.User;

/**
 * Created by vcsontos on 2017. 05. 12..
 */

public class LoginEvent {

    private int code;
    private User user;
    private Throwable throwable;

    public LoginEvent() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
