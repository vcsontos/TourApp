package hu.bme.aut.mobsoft.tourapp;

import javax.inject.Singleton;

import dagger.Component;
import hu.bme.aut.mobsoft.tourapp.repository.RepositoryModule;
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
@Component(modules = {UIModule.class, RepositoryModule.class})
public interface TourApplicationComponent {

    void inject(TourApplication tourApplication);

    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(DetailsActivity detailsActivity);

    void inject(NewTourActivity newTourActivity);

    void inject(MyToursActivity myToursActivity);
}
