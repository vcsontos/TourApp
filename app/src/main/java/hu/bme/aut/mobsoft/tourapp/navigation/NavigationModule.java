package hu.bme.aut.mobsoft.tourapp.navigation;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by valentin on 2017. 05. 13..
 */

@Module
public class NavigationModule {

    @Provides
    @Singleton
    protected DrawerManager provideDrawerManager() {
        return new DrawerManager();
    }
}
