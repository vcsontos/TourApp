package hu.bme.aut.mobsoft.tourapp.navigation;

/**
 * Created by valentin on 2017. 05. 13..
 */

public enum DrawerItem {

    DRAWER_ITEM1(1),
    DRAWER_ITEM2(2),
    DRAWER_ITEM3(3),
    DRAWER_ITEM4(4),
    DRAWER_ITEM5(5);

    private int id;

    DrawerItem(int id) {
        id = id;
    }

    public int getId() {
        return id;
    }
}
