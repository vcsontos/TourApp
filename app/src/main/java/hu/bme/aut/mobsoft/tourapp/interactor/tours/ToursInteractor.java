package hu.bme.aut.mobsoft.tourapp.interactor.tours;

import de.greenrobot.event.EventBus;

import java.util.List;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.tourapp.TourApplication;
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

    public void getTours(String toursSearchTerm) {
        ToursEvent event = new ToursEvent();
        try {
            List<Tour> tours = repository.getTours(toursSearchTerm);
            event.setTours(tours);
            bus.post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            bus.post(event);
        }
    }
}
