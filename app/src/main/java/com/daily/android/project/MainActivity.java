package com.daily.android.project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daily.bottom.navigation.BottomNavigationView;
import com.daily.bottom.navigation.NavigationAdapter;
import com.daily.bottom.navigation.TabItem;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mBottomNavigationView;
    private TabItem mHomeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHomeItem = new TabItem();
        mHomeItem.icon = R.drawable.home_selector;
        mHomeItem.title = "首页";

        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setSelected(0);
        mBottomNavigationView.setOnItemClickListener(new BottomNavigationView.OnItemClickListener() {
            @Override
            public void onItemClick(int preIndex, int index) {
                Log.e("simple", "preIndex:" + preIndex + " index:" + index);
            }
        });
        mBottomNavigationView.setNavigationAdapter(new NavigationAdapter(getSupportFragmentManager()) {
            @Override
            public TabItem getTabItem(int position) {
                TabItem item = new TabItem();
                switch (position) {
                    case 0:
                        item = mHomeItem;
                        break;
                    case 1:
                        item.icon = R.drawable.local_selector;
                        item.title = "本地";
                        break;
                    case 2:
                        item.icon = R.drawable.subscription_selector;
                        item.title = "订阅";
                        break;
                    case 3:
                        item.icon = R.drawable.video_selector;
                        item.title = "视频";
                        break;
                }

                return item;
            }

            @Override
            public Fragment getItem(int i) {
                return SimpleFragment.newInstance(i);
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
    }

    public static class SimpleFragment extends Fragment {
        static Map<Integer, SimpleFragment> cacheMap = new HashMap<>();

        public static SimpleFragment newInstance(int index) {
            SimpleFragment fragment = cacheMap.get(index);
            if (fragment == null) {
                fragment = new SimpleFragment();
                Bundle args = new Bundle();
                args.putInt("index", index);
                fragment.setArguments(args);
                cacheMap.put(index, fragment);
            }
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Bundle args = getArguments();
            int index = args.getInt("index");
            TextView textView = new TextView(getActivity());
            textView.setText("index:" + index);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            return textView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
