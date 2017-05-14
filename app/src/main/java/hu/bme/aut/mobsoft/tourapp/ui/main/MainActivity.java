package hu.bme.aut.mobsoft.tourapp.ui.main;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.bme.aut.mobsoft.tourapp.R;
import hu.bme.aut.mobsoft.tourapp.TourApplication;
import hu.bme.aut.mobsoft.tourapp.model.Tour;
import hu.bme.aut.mobsoft.tourapp.navigation.DrawerManager;
import hu.bme.aut.mobsoft.tourapp.ui.details.DetailsActivity;
import hu.bme.aut.mobsoft.tourapp.ui.new_tour.NewTourActivity;
import hu.bme.aut.mobsoft.tourapp.utils.Constants;

public class MainActivity extends AppCompatActivity implements MainScreen,
        Drawer.OnDrawerItemClickListener, SearchView.OnQueryTextListener {

    private static final String TAG = Constants.LOG_PREFIX + MainActivity.class.getSimpleName();

    @BindView(R.id.tool_bar)
    Toolbar toolBar;

    @BindView(R.id.toolbar_title)
    TextView titleToolbar;

    @BindView(R.id.tours_recyclerView)
    RecyclerView toursRecyclerView;

    @BindView(R.id.toursProgressBar)
    ProgressBarCircularIndeterminate toursProgressBar;

    @Inject
    DrawerManager drawerManager;

    @Inject
    MainPresenter mainPresenter;

    private ToursAdapter toursAdapter;
    private List<Tour> tours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        TourApplication.injector.inject(this);
        setToolBar();
        initNavigationDrawer(savedInstanceState);
        setToursRecyclerView();
    }

    private void setToolBar() {
        Log.d(TAG, "setToolBar() called");
        setSupportActionBar(toolBar);
        titleToolbar.setText(R.string.toolbar_title_home);
        assert getSupportActionBar() != null;
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initNavigationDrawer(Bundle savedInstanceState) {
        Log.d(TAG, "initNavigationDrawer() called");
        drawerManager.buildDrawer(this, toolBar, savedInstanceState);
        drawerManager.getDrawer().setOnDrawerItemClickListener(this);
    }

    private void setToursRecyclerView() {
        toursRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        toursRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, LinearLayoutManager.VERTICAL));
        toursRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Tour tour = toursAdapter.getItem(position);
                navigateToTourDetails();
            }
        }));
    }

    private void navigateToTourDetails() {
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachScreen(this);
        if (drawerManager.isDrawerOpen()) {
            drawerManager.getDrawer().closeDrawer();
        }
        mainPresenter.getTours();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.detachScreen();
    }

    @Override
    public void showTours(List<Tour> tours) {
        Log.d(TAG, "showTours called");
        this.tours = tours;
        toursAdapter = new ToursAdapter(this, tours);
        toursRecyclerView.setAdapter(toursAdapter);
    }

    @Override
    public void showProgressBar() {
        toursProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        toursProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showMessage(int messageId) {
        Toast.makeText(this, getString(messageId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTitle(CharSequence title) {
        titleToolbar.setText(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager)
                MainActivity.this.getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
            searchView.setOnQueryTextListener(this);
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
        boolean isHandled = drawerManager.handleItemSelected(this, view, position, drawerItem);
        if (isHandled) {
            drawerManager.getDrawer().closeDrawer();
        }

        return isHandled;
    }

    @OnClick(R.id.fab)
    public void clickCreateTourBtn() {
        Intent intent = new Intent(this, NewTourActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (drawerManager != null) {
            outState = drawerManager.saveInstanceState(outState);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (drawerManager != null) {
            drawerManager.setLastSelectedDrawerItem(this);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String toursSearchTerm) {
        final List<Tour> filteredTours = filter(tours, toursSearchTerm);
        toursAdapter.setFilter(filteredTours);
        return true;
    }

    private List<Tour> filter(List<Tour> tours, String query) {
        query = query.toLowerCase();
        final List<Tour> filteredTours = new ArrayList<>();
        for (Tour tour : tours) {
            final String text = tour.getTourName().toLowerCase();
            if (text.contains(query)) {
                filteredTours.add(tour);
            }
        }
        return filteredTours;
    }
}
