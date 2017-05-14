package hu.bme.aut.mobsoft.tourapp.interactor.tours.events;

/**
 * Created by valentin on 2017. 05. 14..
 */

public class TourConnectionEvent {

    private int code;
    private int tourMembersNumber;
    private Status status;
    private Throwable throwable;

    public TourConnectionEvent() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getTourMembersNumber() {
        return tourMembersNumber;
    }

    public void setTourMembersNumber(int tourMembersNumber) {
        this.tourMembersNumber = tourMembersNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
