package hu.bme.aut.mobsoft.tourapp.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import java.util.ArrayList;

import javax.inject.Inject;

import hu.bme.aut.mobsoft.tourapp.R;
import hu.bme.aut.mobsoft.tourapp.ui.login.LoginActivity;
import hu.bme.aut.mobsoft.tourapp.ui.main.MainActivity;
import hu.bme.aut.mobsoft.tourapp.ui.my_tours.MyToursActivity;

/**
 * Created by valentin on 2017. 05. 13..
 */

public class DrawerManager {

    private static final String TAG = DrawerManager.class.getSimpleName();

    private DrawerItem mLastSelectedItem;
    private Drawer mDrawer;
    private String profileUsername;

    @Inject
    public DrawerManager() {
        mLastSelectedItem = DrawerItem.DRAWER_ITEM1;
    }

    public void buildDrawer(Activity activity, Toolbar toolBar, Bundle savedInstanceState) {

        mDrawer = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolBar)
                .withDrawerItems(getDrawerItems(activity))
                .withSelectedItemByPosition(getLastSelectedItemPosition())
                .withAccountHeader(getAccountHeader(activity, savedInstanceState))
                .addDrawerItems()
                .withSavedInstance(savedInstanceState)
                .build();
    }

    private AccountHeader getAccountHeader(Activity activity, Bundle savedInstanceState) {

        Log.d(TAG, "getAccountHeader() called");

        return new AccountHeaderBuilder()
                .withActivity(activity)
                .withCompactStyle(true)
                .withHeightDp(100)
                .withHeaderBackground(R.drawable.nav_drawer_header)
                .withSelectionListEnabledForSingleProfile(false)    // remove drop-down
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName(profileUsername)
                                .withIcon(R.drawable.user_profile_unknown)
                                .withSelectedTextColorRes(R.color.dark_grey)
                )
                .withSavedInstance(savedInstanceState)
                .build();
    }

    private ArrayList<IDrawerItem> getDrawerItems(Activity activity) {

        // The menu items in the drawer
        ArrayList<IDrawerItem> items = new ArrayList<>();

        items.add(new PrimaryDrawerItem().withName(R.string.nav_tours).withIcon(R.drawable.ic_nav_tours)
                .withIdentifier(1)
                .withSelectedTextColorRes(R.color.colorPrimary)
                .withSelectedIconColorRes(R.color.colorPrimary));

        items.add(new PrimaryDrawerItem().withName(R.string.nav_my_tours).withIcon(R.drawable.ic_nav_my_tours)
                .withIdentifier(2)
                .withSelectedTextColorRes(R.color.colorPrimary)
                .withSelectedIconColorRes(R.color.colorPrimary));

        items.add(new PrimaryDrawerItem().withName(R.string.nav_logout).withIcon(R.drawable.ic_nav_logout)
                .withIdentifier(3)
                .withSelectedTextColorRes(R.color.colorPrimary)
                .withSelectedIconColorRes(R.color.colorPrimary));

        return items;
    }

    public boolean handleItemSelected(Activity activity, View view, int position, IDrawerItem drawerItem) {

        if (drawerItem instanceof Nameable) {

            switch (view.getId()) {
                case 1:
                    startTours(activity);
                    mLastSelectedItem = DrawerItem.DRAWER_ITEM1;
                    break;

                case 2:
                    startMyTours(activity);
                    mLastSelectedItem = DrawerItem.DRAWER_ITEM2;
                    break;

                case 3:
                    startLogout(activity);
                    mLastSelectedItem = DrawerItem.DRAWER_ITEM3;
                    break;

                default:
                    startTours(activity);
                    mLastSelectedItem = DrawerItem.DRAWER_ITEM1;
                    break;

            }

            closeDrawer();
            return true;
        }

        return false;
    }

    private void startTours(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    private void startMyTours(Activity activity) {
        Intent intent = new Intent(activity, MyToursActivity.class);
        activity.startActivity(intent);
    }

    private void startLogout(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
        // TODO delete loggedInUser
    }

    public void setLastSelectedDrawerItem(Activity activity) {
        if (mDrawer != null) {
            getDrawerItems(activity).get(mLastSelectedItem.getId()).withSelectable(true);
        }
    }

    public void closeDrawer() {
        if (mDrawer != null) {
            mDrawer.closeDrawer();
        }
    }

    public boolean isDrawerOpen() {
        return mDrawer != null && mDrawer.isDrawerOpen();
    }

    public Bundle saveInstanceState(Bundle outstate) {
        return mDrawer.saveInstanceState(outstate);
    }

    public void reset() {
        mLastSelectedItem = DrawerItem.DRAWER_ITEM1;
    }

    /**
     * Enables/disbales the drawer the drawer
     */
    public void enableDrawer(boolean isEnabled) {
        if (mDrawer != null) {
            mDrawer.getDrawerLayout().setDrawerLockMode(isEnabled ? DrawerLayout.LOCK_MODE_UNLOCKED : DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    /**
     * Enable / disable the drawer indicator
     */
    public void enableActionBarDrawerToggle(boolean isEnabled) {
        if (mDrawer != null) {
            mDrawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(isEnabled);
        }
    }

    public int getLastSelectedItemPosition() {
        return mLastSelectedItem.ordinal();
    }

    public Drawer getDrawer() {
        return mDrawer;
    }

}
