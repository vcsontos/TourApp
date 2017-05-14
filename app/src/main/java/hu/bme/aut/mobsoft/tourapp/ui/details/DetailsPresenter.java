package hu.bme.aut.mobsoft.tourapp.ui.details;

import android.util.Log;

import de.greenrobot.event.EventBus;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.tourapp.R;
import hu.bme.aut.mobsoft.tourapp.interactor.tours.ToursInteractor;
import hu.bme.aut.mobsoft.tourapp.interactor.tours.events.Status;
import hu.bme.aut.mobsoft.tourapp.interactor.tours.events.TourConnectionEvent;
import hu.bme.aut.mobsoft.tourapp.interactor.tours.events.TourEvent;
import hu.bme.aut.mobsoft.tourapp.model.Tour;
import hu.bme.aut.mobsoft.tourapp.ui.Presenter;
import hu.bme.aut.mobsoft.tourapp.utils.Constants;

import static hu.bme.aut.mobsoft.tourapp.TourApplication.injector;

/**
 * Created by valentin on 2017. 03. 27..
 */

public class DetailsPresenter extends Presenter<DetailsScreen> {

    private static final String TAG = Constants.LOG_PREFIX + DetailsPresenter.class.getSimpleName();

    @Inject
    ToursInteractor toursInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    public DetailsPresenter() {
    }

    @Override
    public void attachScreen(DetailsScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    public void getTour(final String tourId) {
        if (screen != null) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    toursInteractor.getTour(tourId);
                }
            });
        }
    }

    public void connectTour(final Tour tour) {
        if (screen != null) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    toursInteractor.connectTour(tour);
                }
            });
        }
    }

    public void disconnectTour(final Tour tour) {
        if (screen != null) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    toursInteractor.disconnectTour(tour);
                }
            });
        }
    }

    public void onEventMainThread(TourEvent event) {
        Log.d(TAG, "LoginEvent start...");
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showMessage(R.string.get_tour_details_error);
            }
            Log.e(TAG, "Error during get tour details", event.getThrowable());
        } else {
            if (screen != null) {
                screen.loadTourDetails(event.getTour());
            }
        }
    }

    public void onEventMainThread(TourConnectionEvent event) {
        Log.d(TAG, "LoginEvent start...");
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                if (event.getStatus() == Status.CONNECT) {
                    screen.showMessage(R.string.get_tour_connection_error);
                } else {
                    screen.showMessage(R.string.get_tour_disconnection_error);
                }
            }
            Log.e(TAG, "Error during get tours", event.getThrowable());
        } else {
            if (screen != null) {
                screen.refreshTourMembers(event.getTourMembersNumber());
                screen.refreshBtnLabel(event.getStatus());
            }
        }
    }


}
