package hu.bme.aut.mobsoft.tourapp.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.tourapp.R;
import hu.bme.aut.mobsoft.tourapp.TourApplication;

public class MainActivity extends AppCompatActivity implements MainScreen {

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TourApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.detachScreen();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showMessage(int messageId) {

    }

    @Override
    public void showTours(String toursSearchTerm) {

    }
}
