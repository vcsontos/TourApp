package hu.bme.aut.mobsoft.tourapp.ui.details;

import hu.bme.aut.mobsoft.tourapp.ui.Screen;

/**
 * Created by valentin on 2017. 03. 27..
 */

public interface DetailsScreen extends Screen {

    void showTourDetail();

    void refreshTourMembers(int value);

}
