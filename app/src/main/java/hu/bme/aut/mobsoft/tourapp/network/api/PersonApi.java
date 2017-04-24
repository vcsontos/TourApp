package hu.bme.aut.mobsoft.tourapp.network.api;

import hu.bme.aut.mobsoft.tourapp.model.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PersonApi {

    /**
     * @param username Username of the person
     * @param password Password of the person
     * @return Call<User>
     */

    @FormUrlEncoded
    @POST("login")
    Call<User> login(
            @Field("username") String username, @Field("password") String password
    );


}
