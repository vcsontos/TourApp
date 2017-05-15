package hu.bme.aut.mobsoft.tourapp.mock;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.mobsoft.tourapp.network.NetworkModule;
import hu.bme.aut.mobsoft.tourapp.network.api.PersonApi;
import hu.bme.aut.mobsoft.tourapp.network.api.TourApi;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * Created by valentin on 2017. 05. 15..
 */

@Module
public class MockNetworkModule {

    private NetworkModule networkModule = new NetworkModule();

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder() {
        return networkModule.provideOkHttpClientBuilder();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {

        builder.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                return MockHttpServer.call(request);
            }
        });

        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client) {
        return networkModule.provideRetrofit(client);
    }

    @Provides
    @Singleton
    public PersonApi providePersonApi(Retrofit retrofit) {
        return networkModule.providePersonApi(retrofit);
    }

    @Provides
    @Singleton
    public TourApi provideTourApi(Retrofit retrofit) {
        return networkModule.provideTourApi(retrofit);
    }

}
