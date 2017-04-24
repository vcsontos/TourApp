package hu.bme.aut.mobsoft.tourapp.network.api;

import java.util.List;

import hu.bme.aut.mobsoft.tourapp.model.InlineResponse200;
import hu.bme.aut.mobsoft.tourapp.model.InlineResponse201;
import hu.bme.aut.mobsoft.tourapp.model.Tour;
import static hu.bme.aut.mobsoft.tourapp.utils.CollectionFormats.CSVParams;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TourApi {

    /**
     * Gets My `Tours` objects.
     *
     * @param xAuthToken Authentication of the person
     * @return Call<List<Tour>>
     */

    @GET("my_tours")
    Call<List<Tour>> findMyTours(
            @Header("X-Auth-Token") String xAuthToken
    );


    /**
     * Returns all `Tours` of the system
     *
     * @param xAuthToken Authentication of the person
     * @param tags       Tags used to filter the result
     * @param limit      Maximum number of results to return
     * @return Call<List<Tour>>
     */

    @GET("tours")
    Call<List<Tour>> findTours(
            @Header("X-Auth-Token") String xAuthToken, @Query("tags") CSVParams tags, @Query("limit") Integer limit
    );


    /**
     * Create `Tour` object
     *
     * @param xAuthToken Authentication of the person
     * @param tour       Create tour
     * @return Call<InlineResponse201>
     */

    @POST("tours")
    Call<InlineResponse201> createTour(
            @Header("X-Auth-Token") String xAuthToken, @Body Tour tour
    );


    /**
     * Gets `Tour` object.
     *
     * @param id         Identification of tour
     * @param xAuthToken Authentication of the person
     * @return Call<Tour>
     */

    @GET("tours/{id}")
    Call<Tour> findTourById(
            @Path("id") String id, @Header("X-Auth-Token") String xAuthToken
    );


    /**
     * Delete `Tour`
     *
     * @param id         Identification of tour
     * @param xAuthToken Authentication of the person
     * @return Call<Void>
     */

    @DELETE("tours/{id}")
    Call<Void> deleteTour(
            @Path("id") String id, @Header("X-Auth-Token") String xAuthToken
    );


    /**
     * User connect to a `Tour`
     *
     * @param id         Identification of tour
     * @param xAuthToken Authentication of the person
     * @return Call<InlineResponse200>
     */

    @POST("tours/{id}/connect")
    Call<InlineResponse200> connectTour(
            @Path("id") String id, @Header("X-Auth-Token") String xAuthToken
    );


    /**
     * User disconnect from a `Tour`
     *
     * @param id         Identification of tour
     * @param xAuthToken Authentication of the person
     * @return Call<InlineResponse200>
     */

    @POST("tours/{id}/disconnect")
    Call<InlineResponse200> disconnectTour(
            @Path("id") String id, @Header("X-Auth-Token") String xAuthToken
    );


}
