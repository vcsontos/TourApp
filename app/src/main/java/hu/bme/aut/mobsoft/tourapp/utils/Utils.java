package hu.bme.aut.mobsoft.tourapp.utils;

import com.orhanobut.hawk.Hawk;

import java.util.Calendar;
import java.util.Date;

import hu.bme.aut.mobsoft.tourapp.model.Tour;
import hu.bme.aut.mobsoft.tourapp.model.User;

/**
 * Created by valentin on 2017. 05. 14..
 */

public class Utils {

    public static User getLoggedInUser() {
        return Hawk.get(Constants.LOGGED_IN_USER);
    }

    public static void setLoggedInUser(User user) {
        Hawk.put(Constants.LOGGED_IN_USER, user);
    }

    public static boolean isUserLoggedIn() {
        return getLoggedInUser() != null;
    }

    public static void deleteSessionData() {
        Hawk.delete(Constants.LOGGED_IN_USER);
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static boolean isMemberUser(Tour tour) {
        if (tour.getMembers() == null) {
            return false;
        } else {
            for (User member : tour.getMembers()) {
                if (Utils.getLoggedInUser().getPersonId().equals(member.getPersonId())) {
                    return true;
                }
            }
        }
        return false;
    }
}
