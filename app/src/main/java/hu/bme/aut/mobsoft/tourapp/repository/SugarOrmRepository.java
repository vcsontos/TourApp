package hu.bme.aut.mobsoft.tourapp.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import hu.bme.aut.mobsoft.tourapp.model.Experience;
import hu.bme.aut.mobsoft.tourapp.model.Gender;
import hu.bme.aut.mobsoft.tourapp.model.Tour;
import hu.bme.aut.mobsoft.tourapp.model.User;
import hu.bme.aut.mobsoft.tourapp.utils.GsonHelper;
import hu.bme.aut.mobsoft.tourapp.utils.Utils;

import static hu.bme.aut.mobsoft.tourapp.utils.Utils.addDays;

/**
 * Created by mobsoft on 2017. 04. 10..
 */

public class SugarOrmRepository implements Repository {

    @Override
    public void open(Context context) {
        SugarContext.init(context);

        SugarRecord.deleteAll(User.class);

        User user = new User("2", "user");
        user.setAge(20);
        user.setAuthToken("1234567");
        user.setExpiredDate(addDays(new Date(), 10));
        user.setExperience(Experience.ADVANCED);
        user.setGender(Gender.MALE);

        SugarRecord.save(user);
    }

    @Override
    public void close() {
        SugarContext.terminate();
    }

    @Override
    public User getUser(String username, String password) {

        long count = SugarRecord.count(User.class);
        if (count > 0) {
            List<User> results = SugarRecord.find(User.class, "person_name=?", username);
            if (results != null && !results.isEmpty()) {
                return results.get(0);
            }
        }
        return null;
    }

    @Override
    public List<Tour> getTours() {
        return SugarRecord.listAll(Tour.class);
    }

    @Override
    public Tour getTour(String tourId) {
        List<Tour> results = SugarRecord.find(Tour.class, "tour_id=?", tourId);
        if (results != null && !results.isEmpty()) {
            Type type = new TypeToken<List<User>>() {
            }.getType();
            List<User> members = GsonHelper.getGson().fromJson(results.get(0).getMembersStr(), type);
            results.get(0).setMembers(members);
            return results.get(0);
        }
        return null;
    }

    @Override
    public List<Tour> getMyTours() {
        // TODO implement
        return null;
    }

    @Override
    public void saveTour(Tour tour) {
        Gson gson = new Gson();
        tour.setMembersStr(gson.toJson(tour.getMembers()));
        SugarRecord.save(tour);
    }

    @Override
    public void removeTour(Tour tour) {
        SugarRecord.delete(tour);
    }

    @Override
    public int connectTour(Tour tour) {
        List<Tour> results = SugarRecord.find(Tour.class, "tour_id=?", tour.getTourId());
        int number = 0;
        if (results != null && !results.isEmpty()) {
            List<User> newMembers = results.get(0).getMembers();
            newMembers.add(Utils.getLoggedInUser());
            Gson gson = new Gson();
            results.get(0).setMembersStr(gson.toJson(newMembers));
            SugarRecord.update(results.get(0));
            number = results.get(0).getMembers().size();
        }

        return number;
    }

    @Override
    public int disconnectTour(Tour tour) {
        List<Tour> results = SugarRecord.find(Tour.class, "tour_id=?", tour.getTourId());
        int number = 0;
        if (results != null && !results.isEmpty()) {
            List<User> newMembers = results.get(0).getMembers();
            newMembers.remove(Utils.getLoggedInUser());
            Gson gson = new Gson();
            results.get(0).setMembersStr(gson.toJson(newMembers));
            SugarRecord.save(results.get(0));
            number = results.get(0).getMembers().size();
        }

        return number;
    }

    @Override
    public boolean isInDB(Tour tour) {
        return SugarRecord.findById(Tour.class, Long.valueOf(tour.getTourId())) != null;
    }
}
