package hu.bme.aut.mobsoft.tourapp.ui.main;

import android.util.Log;

import de.greenrobot.event.EventBus;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.tourapp.R;
import hu.bme.aut.mobsoft.tourapp.interactor.tours.ToursInteractor;
import hu.bme.aut.mobsoft.tourapp.interactor.tours.events.ToursEvent;
import hu.bme.aut.mobsoft.tourapp.ui.Presenter;
import hu.bme.aut.mobsoft.tourapp.utils.Constants;

import static hu.bme.aut.mobsoft.tourapp.TourApplication.injector;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public class MainPresenter extends Presenter<MainScreen> {

    private static final String TAG = Constants.LOG_PREFIX + MainPresenter.class.getSimpleName();

    @Inject
    ToursInteractor toursInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    public MainPresenter() {
    }

    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    public void getTours(final String toursSearchTerm) {
        if (screen != null) {
            screen.showProgressBar();
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    toursInteractor.getTours(toursSearchTerm);
                }
            });
        }
    }

    public void onEventMainThread(ToursEvent event) {
        Log.d(TAG, "LoginEvent start...");
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.hideProgressBar();
                screen.showMessage(R.string.get_tours_error);
            }
            Log.e(TAG, "Error during get tours", event.getThrowable());
        } else {
            if (screen != null) {
                screen.hideProgressBar();
                screen.showTours(event.getTours());
            }
        }
    }


}
