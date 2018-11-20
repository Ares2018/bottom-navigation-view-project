# 使用方法

## 自定义样式
1. App Style中添加自定义的Style

	```
	 <com.daily.bottom.navigation.BottomNavigationView
        android:id="@+id/bottom_navigation"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:background="@color/bottom_navigation_background_color"
        app:itemDrawablePadding="4dp"
        app:itemTextColor="@color/bottom_navigation_item_title_color"
        app:itemTextSize="11sp" />
	```
	
	app:itemDrawablePadding 图片和文字的间距  
   app:itemTextColor 字体颜色  
   app:itemTextSize 字体大小  
	
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