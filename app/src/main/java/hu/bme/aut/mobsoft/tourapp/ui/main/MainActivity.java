package hu.bme.aut.mobsoft.tourapp.ui.main;

import android.app.SearchManager;
import android.content.Context;
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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.bme.aut.mobsoft.tourapp.R;
import hu.bme.aut.mobsoft.tourapp.TourApplication;
import hu.bme.aut.mobsoft.tourapp.model.Tour;
import hu.bme.aut.mobsoft.tourapp.utils.Constants;

public class MainActivity extends AppCompatActivity implements MainScreen {

    private static final String TAG = Constants.LOG_PREFIX + MainActivity.class.getSimpleName();

    @BindView(R.id.tool_bar)
    Toolbar toolBar;

    @BindView(R.id.toolbar_title)
    TextView titleToolbar;

    @Inject
    MainPresenter mainPresenter;

    @BindView(R.id.tours_recyclerView)
    RecyclerView toursRecyclerView;

    private ToursAdapter toursAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        TourApplication.injector.inject(this);
        setToolBar();
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

    private void setToolBar() {
        Log.d(TAG, "setToolBar() called");
        setSupportActionBar(toolBar);
        titleToolbar.setText(R.string.toolbar_title_home);
        assert getSupportActionBar() != null;
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_search:
                // TODO search
                break;
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void setToursRecyclerView() {
        toursRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        toursRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, LinearLayoutManager.VERTICAL));
        toursAdapter = new ToursAdapter(this, null);
        toursRecyclerView.setAdapter(toursAdapter);
        toursRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Tour tour = toursAdapter.getItem(position);
                // TODO navigato to tour details
            }
        }));
    }
}
