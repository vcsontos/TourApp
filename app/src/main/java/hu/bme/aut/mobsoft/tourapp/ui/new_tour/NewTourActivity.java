package hu.bme.aut.mobsoft.tourapp.ui.new_tour;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.orhanobut.hawk.Hawk;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.bme.aut.mobsoft.tourapp.R;
import hu.bme.aut.mobsoft.tourapp.TourApplication;
import hu.bme.aut.mobsoft.tourapp.model.Category;
import hu.bme.aut.mobsoft.tourapp.model.Difficulty;
import hu.bme.aut.mobsoft.tourapp.model.Tour;
import hu.bme.aut.mobsoft.tourapp.navigation.DrawerManager;
import hu.bme.aut.mobsoft.tourapp.ui.main.MainActivity;
import hu.bme.aut.mobsoft.tourapp.utils.Constants;

/**
 * Created by valentin on 2017. 03. 27..
 */

public class NewTourActivity extends AppCompatActivity implements NewTourScreen,
        Drawer.OnDrawerItemClickListener {

    private static final String TAG = Constants.LOG_PREFIX + NewTourActivity.class.getSimpleName();

    @BindView(R.id.tool_bar)
    Toolbar toolBar;

    @BindView(R.id.toolbar_title)
    TextView titleToolbar;

    @BindView(R.id.tour_name_et)
    EditText tourName;

    @BindView(R.id.tour_start_date)
    TextView startDate;

    @BindView(R.id.tour_location_et)
    EditText location;

    @BindView(R.id.tour_distance_et)
    EditText distance;

    @BindView(R.id.difficulty_spinner)
    Spinner difficultySpinner;

    @BindView(R.id.category_spinner)
    Spinner categorySpinner;

    @Inject
    NewTourPresenter newTourPresenter;

    @Inject
    DrawerManager drawerManager;

    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tour);

        Hawk.init(this).build();
        ButterKnife.bind(this);
        TourApplication.injector.inject(this);
        setToolBar();
        initNavigationDrawer(savedInstanceState);
        calendar = Calendar.getInstance();
    }

    private void setToolBar() {
        Log.d(TAG, "setToolBar() called");
        setSupportActionBar(toolBar);
        titleToolbar.setText(R.string.toolbar_title_new_tour);
        assert getSupportActionBar() != null;
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initNavigationDrawer(Bundle savedInstanceState) {
        Log.d(TAG, "initNavigationDrawer() called");
        drawerManager.buildDrawer(this, toolBar, savedInstanceState);
        drawerManager.getDrawer().setOnDrawerItemClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        newTourPresenter.attachScreen(this);
        if (drawerManager.isDrawerOpen()) {
            drawerManager.getDrawer().closeDrawer();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        newTourPresenter.detachScreen();
    }

    @Override
    public void showMessage(int messageId) {
        Toast.makeText(this, getString(messageId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
        boolean isHandled = drawerManager.handleItemSelected(this, view, position, drawerItem);
        if (isHandled) {
            drawerManager.getDrawer().closeDrawer();
        }

        return isHandled;
    }

    @OnClick(R.id.buttonLoadPicture)
    public void loadPhotoBtn() {
        // TODO open file chooser
    }

    @OnClick(R.id.set_date_btn)
    public void clickSetDateBtn() {
        new DatePickerDialog(this, myDateListener, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @OnClick(R.id.new_tour_btn)
    public void newTourBtn() {
        Random rand = new Random();
        long randNumber = (long)(rand.nextInt(10000) + 1);
        Tour tour = new Tour(randNumber,UUID.randomUUID().toString(), tourName.getText().toString());

        DateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.US);
        try {
            Date date = format.parse(startDate.getText().toString());
            tour.setStartDate(date);
        } catch (ParseException e) {
            Log.e(TAG, "convert string to startDate error ", e);
        }

        tour.setTourLocation(location.getText().toString());
        if (!distance.getText().toString().isEmpty()) {
            tour.setDistance(Double.valueOf(distance.getText().toString()));
        }
        try {
            tour.setDifficulty(Difficulty.valueOf(difficultySpinner.getSelectedItem().toString().toUpperCase()));
            tour.setCategory(Category.valueOf(categorySpinner.getSelectedItem().toString().toUpperCase()));
        } catch (Exception e) {
            Log.e(TAG, "spinner parsing error ", e);
        }
        newTourPresenter.createTour(tour);
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int year, int month, int day) {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, day);
                    updateLabel();
                }
            };

    private void updateLabel() {
        String myFormat = "MM.dd.yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        startDate.setText(sdf.format(calendar.getTime()));
    }
}
