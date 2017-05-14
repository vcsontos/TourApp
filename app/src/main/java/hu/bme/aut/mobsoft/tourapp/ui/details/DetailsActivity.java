package hu.bme.aut.mobsoft.tourapp.ui.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.bme.aut.mobsoft.tourapp.R;
import hu.bme.aut.mobsoft.tourapp.TourApplication;
import hu.bme.aut.mobsoft.tourapp.navigation.DrawerManager;
import hu.bme.aut.mobsoft.tourapp.utils.Constants;

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

    @Inject
    DetailsPresenter detailsPresenter;

    @Inject
    DrawerManager drawerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

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

    @Override
    public void showTourDetail() {

    }

    @Override
    public void refreshTourMembers(int value) {

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
