# 使用方法

## 自定义样式
1. App Style中添加自定义的Style

	```
	  <!-- Base application theme. -->
	    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
	        <!-- Customize your theme here. -->
	        <item name="colorPrimary">@color/colorPrimary</item>
	        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
	        <item name="colorAccent">@color/colorAccent</item>
	        <item name="bottomNavigationViewStyle">@style/BottomNavigationViewStyle</item>
	        <item name="bottomNavigationViewItemStyle">@style/BottomNavigationViewItemStyle</item>
	    </style>
	```
2. 自定义Style

	```
	
	    <style name="BottomNavigationViewStyle" parent="@style/DailyBottomNavigationView" >
	        <item name="android:background">#dddddd</item>
	    </style>
	
	    <style name="BottomNavigationViewItemStyle" parent="@style/DailyBottomNavigationViewItemStyle" >
	        <item name="android:textColor">@color/bottom_navigation_item_title_color</item>
	        <item name="android:textSize">11sp</item>
	        <item name="android:drawablePadding">4dp</item>
	    </style>
	```
	
3. 添加TAB

	```
	        mBottomNavigationView.setNavigationAdapter(new NavigationAdapter(getSupportFragmentManager()) {
	            @Override
	            public TabItem getTabItem(int position) {
	                TabItem item = new TabItem();
	                switch (position) {
	                    case 0:
	                        item.icon=R.drawable.home_selector;
	                        item.title="首页";
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
	```
	
4. 添加监听器

	```
	mBottomNavigationView.setOnItemClickListener(new BottomNavigationView.OnItemClickListener() {
	            @Override
	            public void onItemClick(int preIndex, int index) {
	                Log.e("simple", "preIndex:" + preIndex + " index:" + index);
	            }
	        });
	```