package hu.bme.aut.mobsoft.tourapp.repository;

import android.content.Context;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.List;

import hu.bme.aut.mobsoft.tourapp.model.Tour;
import hu.bme.aut.mobsoft.tourapp.model.User;

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
    public User getUser(String username, String password) {
        return null;
    }

    @Override
    public List<Tour> getTours() {
        return SugarRecord.listAll(Tour.class);
    }

    @Override
    public List<Tour> getMyTours() {
        // TODO implement
        return null;
    }

    @Override
    public void saveTour(Tour tour) {
        SugarRecord.saveInTx(tour);
    }

    @Override
    public void removeTour(Tour tour) {
        SugarRecord.deleteInTx(tour);
    }

    @Override
    public int connectTour(Tour tour) {
        Tour result = SugarRecord.findById(Tour.class, Long.valueOf(tour.getTourId()));
        if (result != null) {
            result.getMembers().add(null);  // TODO get loggedInPerson
            SugarRecord.saveInTx(result);
        }
        return result.getMembers().size();
    }

    @Override
    public int disconnectTour(Tour tour) {
        Tour result = SugarRecord.findById(Tour.class, Long.valueOf(tour.getTourId()));
        if (result != null) {
            result.getMembers().remove(null); // TODO get loggedInPerson
            SugarRecord.saveInTx(result);
        }
        return result.getMembers().size();
    }

    @Override
    public boolean isInDB(Tour tour) {
        return SugarRecord.findById(Tour.class, Long.valueOf(tour.getTourId())) != null;
    }
}
