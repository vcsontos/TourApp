package hu.bme.aut.mobsoft.tourapp;

import android.content.Context;

import de.greenrobot.event.EventBus;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.mobsoft.tourapp.ui.details.DetailsPresenter;
import hu.bme.aut.mobsoft.tourapp.ui.login.LoginPresenter;
import hu.bme.aut.mobsoft.tourapp.ui.main.MainPresenter;
import hu.bme.aut.mobsoft.tourapp.ui.my_tours.MyToursPresenter;
import hu.bme.aut.mobsoft.tourapp.ui.new_tour.NewTourPresenter;
import hu.bme.aut.mobsoft.tourapp.utils.UiExecutor;
import hu.bme.aut.mobsoft.tourapp.ui.UIModule;

/**
 * Created by valentin on 2017. 05. 15..
 */

@Module
public class TestModule {

    private final UIModule UIModule;

    public TestModule(Context context) {
        this.UIModule = new UIModule(context);
    }

    @Provides
    public Context provideContext() {
        return UIModule.provideContext();
    }


    @Provides
    public MainPresenter provideMainPresenter() {
        return UIModule.provideMainPresenter();
    }

    @Provides
    public DetailsPresenter provideDetailsPresenter() {
        return UIModule.provideDetailsPresenter();
    }

    @Provides
    public MyToursPresenter provideMyToursPresenter() {
        return UIModule.provideMyToursPresenter();
    }

    @Provides
    public NewTourPresenter provideNewTourPresenter() {
        return UIModule.provideNewTourPresenter();
    }

    @Provides
    public LoginPresenter provideLoginPresenter() {
        return UIModule.provideLoginPresenter();
    }

    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    public Executor provideNetworkExecutor() {
        return new UiExecutor();
    }
}
