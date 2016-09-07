package com.phone.day21_tab_fragmenttabhost;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.widget.TabHost.TabSpec;

/**
 * 使用FragmentTabHost 方法--不推荐使用
 * 1 实例化FragmentTabHost
 * 2 初始化 数据 setUp
 * 3 实例化标签TabSpec
 * 4 添加标签 add(tab  ,Class<?>,Bundle)
 *
 * 
 */
public class MainActivity extends FragmentActivity {

	// 实例化TabHost
	FragmentTabHost tabHost;
	String[] titles = new String[] { "书签1", "书签2", "书签3", "书签4" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tabHost = (FragmentTabHost) findViewById(R.id.tabHost);

		// 初始化
		tabHost.setup(this, getSupportFragmentManager(), R.id.containers);

		for (int i = 0; i < titles.length; i++) {

			/**
			 * 第一个参数是 标识 第二个参数 Tab标签的汉字
			 */
			TabSpec tab = tabHost.newTabSpec("tab_" + i)
					.setIndicator(titles[i]);

			/**
			 * 第一个参数 Tab 书签 第二个参数 页卡Fragment 第三个参数 传递到Fragment 里面的参数 ，没有的话传null
			 */

			Bundle bundle = new Bundle();
			bundle.putInt("index", i + 1);

			tabHost.addTab(tab, MyFragment.class, bundle);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
