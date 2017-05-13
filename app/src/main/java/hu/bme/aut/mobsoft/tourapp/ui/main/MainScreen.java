package hu.bme.aut.mobsoft.tourapp.ui.main;

import java.util.List;

import hu.bme.aut.mobsoft.tourapp.model.Tour;
import hu.bme.aut.mobsoft.tourapp.ui.Screen;

/**
 * Created by mobsoft on 2017. 03. 27..
 */

public interface MainScreen extends Screen {

    void showTours(List<Tour> tours);
}
