package hu.bme.aut.mobsoft.tourapp.ui.details;

import hu.bme.aut.mobsoft.tourapp.interactor.tours.events.Status;
import hu.bme.aut.mobsoft.tourapp.model.Tour;
import hu.bme.aut.mobsoft.tourapp.ui.Screen;

/**
 * Created by valentin on 2017. 03. 27..
 */

public interface DetailsScreen extends Screen {

    void loadTourDetails(Tour tour);

    void refreshTourMembers(int value);

    void refreshBtnLabel(Status status);

}
