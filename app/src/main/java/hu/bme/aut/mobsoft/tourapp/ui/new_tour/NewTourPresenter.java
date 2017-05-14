package hu.bme.aut.mobsoft.tourapp.ui.new_tour;

import android.util.Log;

import de.greenrobot.event.EventBus;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.tourapp.R;
import hu.bme.aut.mobsoft.tourapp.interactor.tours.ToursInteractor;
import hu.bme.aut.mobsoft.tourapp.interactor.tours.events.NewTourEvent;
import hu.bme.aut.mobsoft.tourapp.model.Tour;
import hu.bme.aut.mobsoft.tourapp.ui.Presenter;
import hu.bme.aut.mobsoft.tourapp.utils.Constants;

import static hu.bme.aut.mobsoft.tourapp.TourApplication.injector;

/**
 * Created by valentin on 2017. 03. 27..
 */

public class NewTourPresenter extends Presenter<NewTourScreen> {

    private static final String TAG = Constants.LOG_PREFIX + NewTourPresenter.class.getSimpleName();

    @Inject
    ToursInteractor toursInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    public NewTourPresenter() {
    }

    @Override
    public void attachScreen(NewTourScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    public void createTour(final Tour tour) {
        if (screen != null) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    toursInteractor.createTour(tour);
                }
            });
        }
    }

    public void onEventMainThread(NewTourEvent event) {
        Log.d(TAG, "LoginEvent start...");
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage(R.string.create_tour_error);
            }
            Log.e(TAG, "Error during get tours", event.getThrowable());
        } else {
            if (screen != null) {
                screen.showMessage(R.string.successful_create_tour);
                screen.navigateToHome();
            }
        }
    }

}
