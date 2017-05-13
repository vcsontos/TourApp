package hu.bme.aut.mobsoft.tourapp.interactor.tours.events;

import java.util.List;

import hu.bme.aut.mobsoft.tourapp.model.Tour;

/**
 * Created by valentin on 2017. 05. 13..
 */

public class ToursEvent {

    private int code;
    private List<Tour> tours;
    private Throwable throwable;

    public ToursEvent() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
