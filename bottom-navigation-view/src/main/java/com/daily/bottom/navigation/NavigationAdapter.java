package com.daily.bottom.navigation;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public abstract class NavigationAdapter extends FragmentPagerAdapter {
    private FragmentManager mFragmentManager;

    public NavigationAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    public abstract TabItem getTabItem(int position);

    public FragmentManager getFragmentManager() {
        return mFragmentManager;
    }

}
