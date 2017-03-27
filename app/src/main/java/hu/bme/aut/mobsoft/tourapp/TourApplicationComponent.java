package hu.bme.aut.mobsoft.tourapp;

import javax.inject.Singleton;

import dagger.Component;
import hu.bme.aut.mobsoft.tourapp.ui.UIModule;
import hu.bme.aut.mobsoft.tourapp.ui.main.MainActivity;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

@Singleton
@Component(modules = {UIModule.class})
public interface TourApplicationComponent {

    void inject(MainActivity mainActivity);
}
