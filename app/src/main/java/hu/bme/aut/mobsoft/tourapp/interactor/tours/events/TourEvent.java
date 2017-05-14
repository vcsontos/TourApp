package hu.bme.aut.mobsoft.tourapp.interactor.tours.events;

import hu.bme.aut.mobsoft.tourapp.model.Tour;

/**
 * Created by valentin on 2017. 05. 14..
 */

public class TourEvent {

    private int code;
    private Tour tour;
    private Throwable throwable;

    public TourEvent() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
