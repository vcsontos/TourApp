package hu.bme.aut.mobsoft.tourapp.interactor.tours;

import de.greenrobot.event.EventBus;

import java.util.List;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.tourapp.TourApplication;
import hu.bme.aut.mobsoft.tourapp.interactor.tours.events.NewTourEvent;
import hu.bme.aut.mobsoft.tourapp.interactor.tours.events.Status;
import hu.bme.aut.mobsoft.tourapp.interactor.tours.events.TourConnectionEvent;
import hu.bme.aut.mobsoft.tourapp.interactor.tours.events.TourEvent;
import hu.bme.aut.mobsoft.tourapp.interactor.tours.events.ToursEvent;
import hu.bme.aut.mobsoft.tourapp.model.Tour;
import hu.bme.aut.mobsoft.tourapp.repository.Repository;

/**
 * Created by mobsoft on 2017. 04. 24..
 */

public class ToursInteractor {

    @Inject
    Repository repository;

    @Inject
    EventBus bus;

    public ToursInteractor() {
        TourApplication.injector.inject(this);
    }

    public void getTours() {
        ToursEvent event = new ToursEvent();
        try {
            List<Tour> tours = repository.getTours();
            event.setTours(tours);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void getTour(String tourId) {
        TourEvent event = new TourEvent();
        try {
            Tour tour = repository.getTour(tourId);
            event.setTour(tour);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void connectTour(Tour tour) {
        TourConnectionEvent event = new TourConnectionEvent();
        try {
            int updatedMemberNumb = repository.connectTour(tour);
            event.setStatus(Status.CONNECT);
            event.setTourMembersNumber(updatedMemberNumb);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void disconnectTour(Tour tour) {
        TourConnectionEvent event = new TourConnectionEvent();
        try {
            int updatedMemberNumb = repository.disconnectTour(tour);
            event.setStatus(Status.DISCONNECT);
            event.setTourMembersNumber(updatedMemberNumb);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }

    public void createTour(Tour tour) {
        NewTourEvent event = new NewTourEvent();
        try {
            repository.saveTour(tour);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
