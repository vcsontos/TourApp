package hu.bme.aut.mobsoft.tourapp.interactor.tours.events;

/**
 * Created by valentin on 2017. 05. 14..
 */

public class NewTourEvent {

    private int code;
    private Throwable throwable;

    public NewTourEvent() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
