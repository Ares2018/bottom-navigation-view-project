package com.daily.bottom.navigation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.aliya.uimode.mode.Attr;
import com.aliya.uimode.utils.UiModeUtils;

public class BottomNavigationView extends FrameLayout implements RadioGroup.OnCheckedChangeListener {


    private NavigationAdapter mAdapter;
    private FragmentManager mFragmentManager;
    private RadioGroup mRadioGroup;
    private OnItemClickListener mOnItemClickListener;
    private BottomNavigationObserver mNavigationObserver;
    private int mIndex = 0;

    public interface OnItemClickListener {
        void onItemClick(int preIndex, int index);
    }

    public BottomNavigationView(Context context) {
        this(context, null);
    }

    public BottomNavigationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI(context, attrs, defStyleAttr);
    }


    private void initUI(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.bottom_navigation_view, this, true);
        mRadioGroup = findViewById(R.id.navigation_bottom_container);
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    public void setSelected(int index) {
        mIndex = index;
    }

    public void setNavigationAdapter(NavigationAdapter navigationAdapter) {
        mAdapter = navigationAdapter;
        if (mAdapter == null) {
            return;
        }

        if (mNavigationObserver == null) {
            mNavigationObserver = new BottomNavigationObserver(this);
        }
        mAdapter.registerDataSetObserver(mNavigationObserver);
        mFragmentManager = mAdapter.getFragmentManager();
        int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            RadioButton radioButton = createTab(i);
            if (i == mIndex) {
                radioButton.setChecked(true);
                addTabContent(mIndex);
            }
            mRadioGroup.addView(radioButton, i);
        }
    }

    public View getTabItemView(int index) {
        if (index < 0 || index > mRadioGroup.getChildCount()) {
            return null;
        }
        return mRadioGroup.getChildAt(index);
    }

    @NonNull
    private RadioButton createTab(int i) {
        TabItem item = mAdapter.getTabItem(i);
        RadioButton radioButton = (RadioButton) LayoutInflater.from(getContext()).inflate(R.layout.bottom_navigation_item, mRadioGroup, false);
        UiModeUtils.applySave(radioButton, Attr.NAME_DRAWABLE_TOP,item.icon);
        radioButton.setText(item.title);
        radioButton.setId(i);
        return radioButton;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switchTab(checkedId);
    }



    public void notifyDataChange() {
        if (mRadioGroup.getChildCount() != mAdapter.getCount()) {
            throw new IllegalStateException("不能修改Tab个数");
        }

        for (int i = 0; i < mAdapter.getCount(); i++) {
            TabItem item = mAdapter.getTabItem(i);
            RadioButton radioButton = (RadioButton) mRadioGroup.getChildAt(i);
            radioButton.setText(item.title);
            UiModeUtils.applySave(radioButton,Attr.NAME_DRAWABLE_TOP,item.icon);
        }
    }

    private void switchTab(int index) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(mIndex, index);
        }

        if (mIndex == index) {
            return;
        }
        addTabContent(index);
        hideTabContent(mIndex);
        mIndex = index;
    }

    private void hideTabContent(int index) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        Fragment fragment = mAdapter.getItem(index);
        transaction.hide(fragment);
        transaction.commitAllowingStateLoss();
    }

    private void addTabContent(int index) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        Fragment fragment = mAdapter.getItem(index);
        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            transaction.add(R.id.bottom_navigation_content, fragment,mAdapter.getTabItem(index).className);
        }
        transaction.commitAllowingStateLoss();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public NavigationAdapter getNavigationAdapter() {
        return mAdapter;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mAdapter != null && mNavigationObserver != null) {
            mAdapter.unregisterDataSetObserver(mNavigationObserver);
        }
    }
}
