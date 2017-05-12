package hu.bme.aut.mobsoft.tourapp;

import android.app.Application;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.tourapp.repository.Repository;
import hu.bme.aut.mobsoft.tourapp.ui.UIModule;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public class TourApplication extends Application {

    @Inject
    Repository repository;

    public static TourApplicationComponent injector;

    public void setInjector(TourApplicationComponent appComponent) {
        injector = appComponent;
        injector.inject(this);
        repository.open(getApplicationContext());
    }

    @Override
    public void onCreate() {
        super.onCreate();

        injector = DaggerTourApplicationComponent.builder().
                        uIModule(
                                new UIModule(this)
                        ).build();

        injector.inject(this);
        repository.open(getApplicationContext());
    }
}
