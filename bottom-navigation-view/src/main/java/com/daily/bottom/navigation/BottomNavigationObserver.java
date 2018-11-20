package com.daily.bottom.navigation;

import android.database.DataSetObserver;

public class BottomNavigationObserver extends DataSetObserver {
    private BottomNavigationView mBottomNavigationView;
    public BottomNavigationObserver(BottomNavigationView view) {
        super();
        mBottomNavigationView=view;
    }

    @Override
    public void onChanged() {
        mBottomNavigationView.notifyDataChange();
    }

    @Override
    public void onInvalidated() {
        mBottomNavigationView.notifyDataChange();
    }
}
