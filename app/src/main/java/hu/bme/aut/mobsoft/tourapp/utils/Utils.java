package hu.bme.aut.mobsoft.tourapp.utils;

import com.orhanobut.hawk.Hawk;

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
}
