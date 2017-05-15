package hu.bme.aut.mobsoft.tourapp;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.tourapp.repository.Repository;
import hu.bme.aut.mobsoft.tourapp.ui.UIModule;
import io.fabric.sdk.android.Fabric;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public class TourApplication extends Application {

    @Inject
    Repository repository;

    private Tracker mTracker;
    public static TourApplicationComponent injector;

    public void setInjector(TourApplicationComponent appComponent) {
        injector = appComponent;
        injector.inject(this);
        repository.open(getApplicationContext());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        injector = DaggerTourApplicationComponent.builder().
                uIModule(
                        new UIModule(this)
                ).build();

        injector.inject(this);
        repository.open(getApplicationContext());

    }

    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     *
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }
}
