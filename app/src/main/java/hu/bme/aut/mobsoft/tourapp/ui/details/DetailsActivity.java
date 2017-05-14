package hu.bme.aut.mobsoft.tourapp.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.orhanobut.hawk.Hawk;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.bme.aut.mobsoft.tourapp.R;
import hu.bme.aut.mobsoft.tourapp.TourApplication;
import hu.bme.aut.mobsoft.tourapp.interactor.tours.events.Status;
import hu.bme.aut.mobsoft.tourapp.model.Tour;
import hu.bme.aut.mobsoft.tourapp.model.User;
import hu.bme.aut.mobsoft.tourapp.navigation.DrawerManager;
import hu.bme.aut.mobsoft.tourapp.utils.Constants;
import hu.bme.aut.mobsoft.tourapp.utils.StringUtil;
import hu.bme.aut.mobsoft.tourapp.utils.Utils;

/**
 * Created by valentin on 2017. 03. 27..
 */

public class DetailsActivity extends AppCompatActivity implements DetailsScreen,
        Drawer.OnDrawerItemClickListener {

    private static final String TAG = Constants.LOG_PREFIX + DetailsActivity.class.getSimpleName();

    @BindView(R.id.tool_bar)
    Toolbar toolBar;

    @BindView(R.id.toolbar_title)
    TextView titleToolbar;

    @BindView(R.id.tour_details_photo)
    ImageView photo;

    @BindView(R.id.tour_details_name)
    TextView tourName;

    @BindView(R.id.tour_details_date)
    TextView tourStartDate;

    @BindView(R.id.tour_details_distance)
    TextView distance;

    @BindView(R.id.tour_details_difficulty)
    TextView difficulty;

    @BindView(R.id.tour_details_organizer)
    TextView organizer;

    @BindView(R.id.tour_details_category)
    TextView category;

    @BindView(R.id.tour_details_members_number)
    TextView membersNumber;

    @BindView(R.id.join_tour)
    Button connectBtn;

    @Inject
    DetailsPresenter detailsPresenter;

    @Inject
    DrawerManager drawerManager;

    private Tour tour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Hawk.init(this).build();
        ButterKnife.bind(this);
        TourApplication.injector.inject(this);
        setToolBar();
        initNavigationDrawer(savedInstanceState);
    }

    private void setToolBar() {
        Log.d(TAG, "setToolBar() called");
        setSupportActionBar(toolBar);
        titleToolbar.setText(R.string.toolbar_title_tour_details);
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
        detailsPresenter.attachScreen(this);
        if (drawerManager.isDrawerOpen()) {
            drawerManager.getDrawer().closeDrawer();
        }

        loadDetailsData();
    }

    private void loadDetailsData() {
        Intent intent = getIntent();
        tour = (Tour) intent.getExtras().getSerializable(Constants.TOUR_INTENT);

        if (tour.getImageUrl() != null) {
            int resId = getResources().getIdentifier(tour.getImageUrl(), "drawable", getPackageName());
            if (resId != 0) {
                photo.setImageResource(resId);
            } else {
                photo.setImageResource(R.drawable.no_image);
            }
        }

        if (tour.getTourName() != null) {
            tourName.setText(tour.getTourName());
        }
        tourStartDate.setText(StringUtil.dateFormatter(tour.getStartDate()));
        if (tour.getDistance() != null) {
            distance.setText(tour.getDistance().toString());
        }
        if (tour.getTourLeader() != null && tour.getTourLeader().getPersonName() != null) {
            organizer.setText(tour.getTourLeader().getPersonName());
        }
        difficulty.setText(tour.getDifficulty().toString().toLowerCase());
        category.setText(tour.getCategory().toString().toLowerCase());
        if (tour.getMembers() != null) {
            membersNumber.setText(String.valueOf(tour.getMembers().size()));
        } else {
            membersNumber.setText("0");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        detailsPresenter.detachScreen();
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

    @OnClick(R.id.join_tour)
    public void clickConnectionBtn() {
        if (!isMemberUser()) {
            detailsPresenter.connectTour(tour);
        } else {
            detailsPresenter.disconnectTour(tour);
        }
    }

    private boolean isMemberUser() {
        if (tour.getMembers() == null) {
            return false;
        } else {
            for (User member : tour.getMembers()) {
                if (Utils.getLoggedInUser().equals(member)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void refreshTourMembers(int value) {
        membersNumber.setText(String.valueOf(value));
    }

    @Override
    public void refreshBtnLabel(Status status) {
        if (status == Status.CONNECT) {
            connectBtn.setText(R.string.tour_details_disconnect);
        } else {
            connectBtn.setText(R.string.tour_details_connect);
        }
    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
        boolean isHandled = drawerManager.handleItemSelected(this, view, position, drawerItem);
        if (isHandled) {
            drawerManager.getDrawer().closeDrawer();
        }

        return isHandled;
    }
}
