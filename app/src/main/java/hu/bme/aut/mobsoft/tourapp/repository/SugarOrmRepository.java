package hu.bme.aut.mobsoft.tourapp.repository;

import android.content.Context;

import com.orm.SugarContext;

import java.util.List;

import hu.bme.aut.mobsoft.tourapp.model.Tour;
import hu.bme.aut.mobsoft.tourapp.model.TourHeader;

/**
 * Created by mobsoft on 2017. 04. 10..
 */

public class SugarOrmRepository implements Repository {

    @Override
    public void open(Context context) {
        SugarContext.init(context);
    }

    @Override
    public void close() {
        SugarContext.terminate();
    }

    @Override
    public List<TourHeader> getTours() {
        return null;
    }

    @Override
    public List<TourHeader> getMyTours() {
        return null;
    }

    @Override
    public Tour getTour(TourHeader tourHeader) {
        return null;
    }

    @Override
    public void saveTour(Tour tour) {

    }

    @Override
    public void removeTour(Tour tour) {

    }

    @Override
    public void connectTour(Tour tour) {

    }

    @Override
    public void disconnectTour(Tour tour) {

    }

    @Override
    public boolean isInDB(Tour tour) {
        return false;
    }
}
