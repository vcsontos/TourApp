package hu.bme.aut.mobsoft.tourapp.utils;

/**
 * Created by vcsontos on 2017. 05. 18..
 */

public class Validator {

    public static boolean validLoginUsername(String username) {
        return (username != null && !username.isEmpty() && username.length() >= 3);
    }

    public static boolean validLoginPassword(String password) {
        return (password != null && !password.isEmpty() && password.length() >= 3);
    }
}
