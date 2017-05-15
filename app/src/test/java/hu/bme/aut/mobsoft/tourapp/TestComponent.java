package hu.bme.aut.mobsoft.tourapp;

import javax.inject.Singleton;

import dagger.Component;
import hu.bme.aut.mobsoft.tourapp.interactor.InteractorModule;
import hu.bme.aut.mobsoft.tourapp.mock.MockNetworkModule;
import hu.bme.aut.mobsoft.tourapp.repository.TestRepositoryModule;

/**
 * Created by valentin on 2017. 05. 15..
 */

@Singleton
@Component(modules = {MockNetworkModule.class, TestModule.class, InteractorModule.class, TestRepositoryModule.class})
public interface TestComponent extends TourApplicationComponent {

}
