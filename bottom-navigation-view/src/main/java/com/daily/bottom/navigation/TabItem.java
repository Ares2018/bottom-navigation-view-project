package com.daily.bottom.navigation;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class TabItem {
    public int icon;
    public String title;
    public String className;
    private Fragment contentFragment;
    private Context mContext;

    public TabItem(Context context, String className, int icon, String title) {
        this.icon = icon;
        this.title = title;
        this.mContext = context;
        this.className = className;
    }


    public TabItem() {
    }

    public Fragment getContent(FragmentManager manager) {
        contentFragment = manager.findFragmentByTag(className);
        if (contentFragment == null) {
            contentFragment = Fragment.instantiate(mContext, className);
        }
        return contentFragment;
    }
}
