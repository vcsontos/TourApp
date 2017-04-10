package hu.bme.aut.mobsoft.tourapp.repository;

import android.content.Context;

import java.util.List;

import hu.bme.aut.mobsoft.tourapp.model.Tour;
import hu.bme.aut.mobsoft.tourapp.model.TourHeader;

/**
 * Created by mobsoft on 2017. 04. 10..
 */

public interface Repository {

    void open(Context context);

    void close();

    List<TourHeader> getTours();

    List<TourHeader> getMyTours();

    Tour getTour(TourHeader tourHeader);

    void saveTour(Tour tour);

    void removeTour(Tour tour);

    void connectTour(Tour tour);

    void disconnectTour(Tour tour);

    boolean isInDB(Tour tour);
}
