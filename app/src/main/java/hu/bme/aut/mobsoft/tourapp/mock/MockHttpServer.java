package hu.bme.aut.mobsoft.tourapp.mock;

import hu.bme.aut.mobsoft.tourapp.mock.interceptors.MockInterceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by valentin on 2017. 05. 15..
 */

public class MockHttpServer {

    public static Response call(Request request) {
        MockInterceptor mockInterceptor = new MockInterceptor();
        return mockInterceptor.process(request);
    }
}
