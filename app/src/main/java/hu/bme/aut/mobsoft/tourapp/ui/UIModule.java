package hu.bme.aut.mobsoft.tourapp.ui;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.mobsoft.tourapp.ui.details.DetailsPresenter;
import hu.bme.aut.mobsoft.tourapp.ui.login.LoginPresenter;
import hu.bme.aut.mobsoft.tourapp.ui.main.MainPresenter;
import hu.bme.aut.mobsoft.tourapp.ui.my_tours.MyToursPresenter;
import hu.bme.aut.mobsoft.tourapp.ui.new_tour.NewTourPresenter;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

@Module
public class UIModule {

    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Provides
    @Singleton
    public LoginPresenter provideLoginPresenter() {
        return new LoginPresenter();
    }

    @Provides
    @Singleton
    public DetailsPresenter provideDetailsPresenter() {
        return new DetailsPresenter();
    }

    @Provides
    @Singleton
    public NewTourPresenter provideNewTourPresenter() {
        return new NewTourPresenter();
    }

    @Provides
    @Singleton
    public MyToursPresenter provideMyToursPresenter() {
        return new MyToursPresenter();
    }

}
