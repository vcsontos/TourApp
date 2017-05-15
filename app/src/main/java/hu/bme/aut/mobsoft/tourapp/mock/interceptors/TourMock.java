package hu.bme.aut.mobsoft.tourapp.mock.interceptors;

import com.google.gson.reflect.TypeToken;

import android.net.Uri;

import java.lang.reflect.Type;

import hu.bme.aut.mobsoft.tourapp.model.Tour;
import hu.bme.aut.mobsoft.tourapp.network.NetworkConfig;
import hu.bme.aut.mobsoft.tourapp.repository.MemoryRepository;
import hu.bme.aut.mobsoft.tourapp.utils.GsonHelper;
import hu.bme.aut.mobsoft.tourapp.utils.Utils;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static hu.bme.aut.mobsoft.tourapp.mock.interceptors.MockHelper.makeResponse;
import static hu.bme.aut.mobsoft.tourapp.utils.GsonHelper.getGson;

/**
 * Created by valentin on 2017. 05. 15..
 */

public class TourMock {

    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        String responseString;
        int responseCode;
        Headers headers = request.headers();

        String tourId = uri.getQueryParameter("id");


        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "tours")
                && request.method().equals("GET")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            responseString = getGson().toJson(memoryRepository.getTours());
            responseCode = 200;
        } else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "tours")
                && request.method().equals("POST")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            String body = MockHelper.bodyToString(request);

            Type type = new TypeToken<Tour>() {}.getType();
            Tour tour = GsonHelper.getGson().fromJson(body, type);
            memoryRepository.saveTour(tour);

            responseString = "Successfully saved";
            responseCode = 200;
        } else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "tours/" + tourId)
                && request.method().equals("GET")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            responseString = getGson().toJson(memoryRepository.getTour(tourId));
            responseCode = 200;
        } else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "tours/" + tourId)
                && request.method().equals("DELETE")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            Tour tour = memoryRepository.getTour(tourId);
            memoryRepository.removeTour(tour);
            responseString = "";
            responseCode = 200;
        } else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "tours/" + tourId + "/connect")
                && request.method().equals("POST")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            Tour tour = memoryRepository.getTour(tourId);
            if (!Utils.isMemberUser(tour)) {
                memoryRepository.connectTour(tour);
                responseString = "Successfully connection";
                responseCode = 200;
            } else {
                responseString = "You are already a member.";
                responseCode = 200;
            }
        } else if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "tours/" + tourId + "/disconnect")
                && request.method().equals("POST")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            Tour tour = memoryRepository.getTour(tourId);
            if (Utils.isMemberUser(tour)) {
                memoryRepository.disconnectTour(tour);
                responseString = "Successfully disconnection";
                responseCode = 200;
            } else {
                responseString = "You are not a member yet.";
                responseCode = 200;
            }
        } else {
            responseString = "ERROR";
            responseCode = 503;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }
}
