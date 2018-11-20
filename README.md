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