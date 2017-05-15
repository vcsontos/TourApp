package hu.bme.aut.mobsoft.tourapp.repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by valentin on 2017. 05. 15..
 */

@Module
public class TestRepositoryModule {

    @Singleton
    @Provides
    public Repository provideRepository() {
        return new MemoryRepository();
    }
}
