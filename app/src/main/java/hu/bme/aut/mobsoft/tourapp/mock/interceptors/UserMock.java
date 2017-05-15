package hu.bme.aut.mobsoft.tourapp.mock.interceptors;

import android.net.Uri;

import hu.bme.aut.mobsoft.tourapp.network.NetworkConfig;
import hu.bme.aut.mobsoft.tourapp.repository.MemoryRepository;
import hu.bme.aut.mobsoft.tourapp.utils.GsonHelper;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static hu.bme.aut.mobsoft.tourapp.mock.interceptors.MockHelper.makeResponse;

/**
 * Created by valentin on 2017. 05. 15..
 */

public class UserMock {

    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        String responseString;
        int responseCode;
        Headers headers = request.headers();

        String username = uri.getQueryParameter("username");
        String password = uri.getQueryParameter("password");

        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "login") && request.method().equals("POST")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            responseString = GsonHelper.getGson().toJson(memoryRepository.getUser(username, password));
            responseCode = 200;
        } else {
            responseString = "ERROR";
            responseCode = 503;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }
}
