package hu.bme.aut.mobsoft.tourapp.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.mobsoft.tourapp.network.api.PersonApi;
import hu.bme.aut.mobsoft.tourapp.network.api.TourApi;
import hu.bme.aut.mobsoft.tourapp.utils.GsonHelper;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by mobsoft on 2017. 04. 24..
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder() {
        return new OkHttpClient().newBuilder();
    }


    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(NetworkConfig.SERVICE_ENDPOINT).client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonHelper.getGson())).build();
    }

    @Provides
    @Singleton
    public PersonApi providePersonApi(Retrofit retrofit) {
        return retrofit.create(PersonApi.class);
    }

    @Provides
    @Singleton
    public TourApi provideTourApi(Retrofit retrofit) {
        return retrofit.create(TourApi.class);
    }

}
