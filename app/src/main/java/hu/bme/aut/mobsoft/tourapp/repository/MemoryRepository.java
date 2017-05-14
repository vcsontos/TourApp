package hu.bme.aut.mobsoft.tourapp.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import hu.bme.aut.mobsoft.tourapp.model.Category;
import hu.bme.aut.mobsoft.tourapp.model.Difficulty;
import hu.bme.aut.mobsoft.tourapp.model.Experience;
import hu.bme.aut.mobsoft.tourapp.model.Gender;
import hu.bme.aut.mobsoft.tourapp.model.Tour;
import hu.bme.aut.mobsoft.tourapp.model.User;
import hu.bme.aut.mobsoft.tourapp.utils.Utils;

import static hu.bme.aut.mobsoft.tourapp.utils.Utils.addDays;

/**
 * Created by mobsoft on 2017. 04. 10..
 */

public class MemoryRepository implements Repository {

    private static List<Tour> tours;
    private static List<Tour> myTours;

    @Override
    public void open(Context context) {

        User tourLeader = new User("1L", "Gipsz Jakab");
        tourLeader.setAge(40);

        Tour tour1 = createTour("1L", "Nagy-Kevely", Category.WALKING, Difficulty.EASY,
                addDays(new Date(), 1), 5.0, "", "nagy_kevely", "", tourLeader, null);

        Tour tour2 = createTour("2L", "Cserna rafting tour", Category.WATER, Difficulty.HARD,
                addDays(new Date(), 10), 50.0, "", "", "", tourLeader, null);

        Tour tour3 = createTour("3L", "Balaton-round", Category.CYCLING, Difficulty.MEDIUM,
                addDays(new Date(), 15), 200.0, "", "balaton_round", "", tourLeader, null);

        Tour tour4 = createTour("3L", "Bukk tour", Category.MOUNTAIN, Difficulty.MEDIUM,
                addDays(new Date(), 15), 20.0, "", "", "", tourLeader, null);

        tours = new ArrayList<>();
        tours.add(tour1);
        tours.add(tour2);
        tours.add(tour3);
        tours.add(tour4);

        myTours = new ArrayList<>();
        myTours.add(tour1);
        myTours.add(tour3);
    }

    @Override
    public void close() {

    }

    @Override
    public User getUser(String username, String password) {

        User user = new User(UUID.randomUUID().toString(), username);
        user.setAge(20);
        user.setAuthToken("1234567");
        user.setExpiredDate(Utils.addDays(new Date(), 10));
        user.setExperience(Experience.ADVANCED);
        user.setGender(Gender.MALE);
        user.setMyTours(myTours);

        return user;
    }

    @Override
    public List<Tour> getTours() {
        return tours;
    }

    @Override
    public Tour getTour(String tourId) {
        for (Tour tour : tours) {
            if (tour.getTourId().equals(tourId)) {
                return tour;
            }
        }
        return null;
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
    public int connectTour(Tour tour) {
        if (tour.getMembers() == null) {
            tour.setMembers(new ArrayList<User>());
        }
        tour.getMembers().add(Utils.getLoggedInUser());
        return tour.getMembers().size();
    }

    @Override
    public int disconnectTour(Tour tour) {
        tour.getMembers().remove(Utils.getLoggedInUser());
        return tour.getMembers().size();
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
}
