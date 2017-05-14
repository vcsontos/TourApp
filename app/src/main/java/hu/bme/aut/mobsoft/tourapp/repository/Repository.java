package hu.bme.aut.mobsoft.tourapp.repository;

import android.content.Context;

import java.util.List;

import hu.bme.aut.mobsoft.tourapp.model.Tour;
import hu.bme.aut.mobsoft.tourapp.model.User;

/**
 * Created by mobsoft on 2017. 04. 10..
 */

public interface Repository {

    void open(Context context);

    void close();

    User getUser(String username, String password);

    List<Tour> getTours();

    List<Tour> getMyTours();

    void saveTour(Tour tour);

    void removeTour(Tour tour);

    int connectTour(Tour tour);

    int disconnectTour(Tour tour);

    boolean isInDB(Tour tour);
}
