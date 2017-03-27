package hu.bme.aut.mobsoft.tourapp.ui.new_tour;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.tourapp.R;
import hu.bme.aut.mobsoft.tourapp.TourApplication;

/**
 * Created by valentin on 2017. 03. 27..
 */

public class NewTourActivity extends AppCompatActivity implements NewTourScreen {

    @Inject
    NewTourPresenter newTourPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TourApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        newTourPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        newTourPresenter.detachScreen();
    }

    @Override
    public void showMessage(int messageId) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showTours(String toursSearchTerm) {

    }
}
