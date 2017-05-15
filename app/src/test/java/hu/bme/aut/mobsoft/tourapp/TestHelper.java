package hu.bme.aut.mobsoft.tourapp;

import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowLog;

/**
 * Created by valentin on 2017. 05. 15..
 */

public class TestHelper {

    public static void setTestInjector() {
        ShadowLog.stream = System.out;
        TourApplication application = (TourApplication) RuntimeEnvironment.application;
        TourApplicationComponent injector = DaggerTestComponent.builder().testModule(
                new TestModule(application.getApplicationContext())).build();
        application.setInjector(injector);
    }
}
