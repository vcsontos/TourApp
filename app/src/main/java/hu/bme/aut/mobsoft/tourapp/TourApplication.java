package hu.bme.aut.mobsoft.tourapp;

import android.app.Application;

import hu.bme.aut.mobsoft.tourapp.ui.UIModule;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public class TourApplication extends Application {

    public static TourApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector = DaggerTourApplicationComponent.builder().
                        uIModule(
                                new UIModule(this)
                        ).build();
    }
}
