package hu.bme.aut.mobsoft.tourapp.interactor;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.mobsoft.tourapp.interactor.login.LoginInteractor;
import hu.bme.aut.mobsoft.tourapp.interactor.tours.ToursInteractor;

/**
 * Created by mobsoft on 2017. 04. 24..
 */

@Module
public class InteractorModule {

    @Provides
    public LoginInteractor provideLoginInteractor() {
        return new LoginInteractor();
    }

    @Provides
    public ToursInteractor provideToursInteractor() {
        return new ToursInteractor();
    }

}
