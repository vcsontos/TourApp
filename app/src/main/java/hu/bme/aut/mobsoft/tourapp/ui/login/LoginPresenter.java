package hu.bme.aut.mobsoft.tourapp.ui.login;

import android.util.Log;

import de.greenrobot.event.EventBus;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.tourapp.R;
import hu.bme.aut.mobsoft.tourapp.interactor.login.LoginInteractor;
import hu.bme.aut.mobsoft.tourapp.interactor.login.events.LoginEvent;
import hu.bme.aut.mobsoft.tourapp.ui.Presenter;
import hu.bme.aut.mobsoft.tourapp.utils.Constants;
import hu.bme.aut.mobsoft.tourapp.utils.Utils;

import static hu.bme.aut.mobsoft.tourapp.TourApplication.injector;

/**
 * Created by valentin on 2017. 03. 27..
 */

public class LoginPresenter extends Presenter<LoginScreen> {

    private static final String TAG = Constants.LOG_PREFIX + LoginPresenter.class.getSimpleName();

    @Inject
    LoginInteractor loginInteractor;

    @Inject
    Executor executor;

    @Inject
    EventBus bus;

    @Override
    public void attachScreen(LoginScreen screen) {
        super.attachScreen(screen);
        injector.inject(this);
        bus.register(this);
    }

    @Override
    public void detachScreen() {
        bus.unregister(this);
        super.detachScreen();
    }

    public void login(final String username, final String password) {
        if (screen != null) {
            screen.showProgressBar();
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    loginInteractor.login(username, password);
                }
            });
        }
    }

    public void onEventMainThread(LoginEvent event) {
        Log.d(TAG, "LoginEvent start...");
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.hideProgressBar();
                screen.showMessage(R.string.login_error);
            }
            Log.e(TAG, "Error during login", event.getThrowable());
        } else {
            if (screen != null) {
                screen.hideProgressBar();
                Utils.setLoggedInUser(event.getUser());
                screen.navigateToHome();
            }
        }
    }

}
