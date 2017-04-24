package hu.bme.aut.mobsoft.tourapp;

import javax.inject.Singleton;

import dagger.Component;
import hu.bme.aut.mobsoft.tourapp.interactor.login.LoginInteractor;
import hu.bme.aut.mobsoft.tourapp.interactor.tours.ToursInteractor;
import hu.bme.aut.mobsoft.tourapp.network.NetworkModule;
import hu.bme.aut.mobsoft.tourapp.repository.RepositoryModule;
import hu.bme.aut.mobsoft.tourapp.interactor.InteractorModule;
import hu.bme.aut.mobsoft.tourapp.ui.UIModule;
import hu.bme.aut.mobsoft.tourapp.ui.details.DetailsActivity;
import hu.bme.aut.mobsoft.tourapp.ui.login.LoginActivity;
import hu.bme.aut.mobsoft.tourapp.ui.main.MainActivity;
import hu.bme.aut.mobsoft.tourapp.ui.my_tours.MyToursActivity;
import hu.bme.aut.mobsoft.tourapp.ui.new_tour.NewTourActivity;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

@Singleton
@Component(modules = {UIModule.class, RepositoryModule.class, InteractorModule.class, NetworkModule.class})
public interface TourApplicationComponent {

    void inject(TourApplication tourApplication);

    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(LoginInteractor loginInteractor);

    void inject(DetailsActivity detailsActivity);

    void inject(NewTourActivity newTourActivity);

    void inject(MyToursActivity myToursActivity);

    void inject(ToursInteractor toursInteractor);
}
