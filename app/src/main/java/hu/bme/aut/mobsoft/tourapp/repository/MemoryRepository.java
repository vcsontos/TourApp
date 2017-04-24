package hu.bme.aut.mobsoft.tourapp.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import hu.bme.aut.mobsoft.tourapp.model.Category;
import hu.bme.aut.mobsoft.tourapp.model.Difficulty;
import hu.bme.aut.mobsoft.tourapp.model.Experience;
import hu.bme.aut.mobsoft.tourapp.model.Gender;
import hu.bme.aut.mobsoft.tourapp.model.Tour;
import hu.bme.aut.mobsoft.tourapp.model.User;

/**
 * Created by mobsoft on 2017. 04. 10..
 */

public class MemoryRepository implements Repository {

    public static List<Tour> tours;
    public static List<Tour> myTours;
    public static User loggedInPerson;

    @Override
    public void open(Context context) {

        User tourLeader = new User("1L", "Gipsz Jakab");
        tourLeader.setAge(40);

        Tour tour1 = createTour("1L", "Nagy-Kevely", Category.WALKING, Difficulty.EASY,
                addDays(new Date(), 1), 5.0, "", "", "", tourLeader, null);

        Tour tour2 = createTour("2L", "Cserna rafting tour", Category.WATER, Difficulty.HARD,
                addDays(new Date(), 10), 50.0, "", "", "", tourLeader, null);

        Tour tour3 = createTour("3L", "Balaton-round", Category.CYCLING, Difficulty.MEDIUM,
                addDays(new Date(), 15), 200.0, "", "", "", tourLeader, null);

        tours = new ArrayList<>();
        tours.add(tour1);
        tours.add(tour2);
        tours.add(tour3);

        myTours = new ArrayList<>();
        myTours.add(tour1);
        myTours.add(tour3);

        loggedInPerson = new User("2L", "Teszt Elek");
        loggedInPerson.setAge(20);
        loggedInPerson.setAuthToken("1234567");
        loggedInPerson.setExpiredDate(addDays(new Date(), 10));
        loggedInPerson.setExperience(Experience.ADVANCED);
        loggedInPerson.setGender(Gender.MALE);
        loggedInPerson.setMyTours(myTours);
    }

    @Override
    public void close() {

    }

    @Override
    public List<Tour> getTours() {
        return tours;
    }

    @Override
    public List<Tour> getMyTours() {
        return myTours;
    }

    @Override
    public void saveTour(Tour tour) {
        tours.add(tour);
    }

    @Override
    public void removeTour(Tour tour) {
        tours.remove(tour);
    }

    @Override
    public void connectTour(Tour tour) {
        tour.getMembers().add(loggedInPerson);
    }

    @Override
    public void disconnectTour(Tour tour) {
        tour.getMembers().remove(loggedInPerson);
    }

    @Override
    public boolean isInDB(Tour tour) {
        return tours.contains(tour);
    }

    private Tour createTour(String id, String name, Category category, Difficulty difficulty,
                            Date startDate, double distance, String desc, String imageUrl,
                            String location, User tourLeader, List<User> members) {
        Tour tour = new Tour(id, name);
        tour.setCategory(category);
        tour.setDifficulty(difficulty);
        tour.setStartDate(startDate);
        tour.setDistance(distance);
        tour.setDescription(desc);
        tour.setImageUrl(imageUrl);
        tour.setTourLocation(location);
        tour.setTourLeader(tourLeader);
        tour.setMembers(members);
        return tour;
    }

    private static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
}
