package com.phone.day21_tab_fragmenttabhost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/*  定义一个Fragment
 * ListFragment 
 * 布局文件中必须有ListView
 * 且id 为  @android:id/list
 * 固定写法
 * 
 */
public class MyFragment extends ListFragment {

	private int index = 0;
	private TextView index_tv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//  获取传递值的bundle对象
		Bundle bundle = getArguments();
		if (bundle != null) {
			index = bundle.getInt("index");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View view = inflater.inflate(R.layout.fragment_layout, null);
		index_tv = (TextView) view.findViewById(R.id.index_tv);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		switch (index) {
		case 1:
			index_tv.setText("现在是书签1");
			break;
		case 2:
			index_tv.setText("现在是书签2");
			break;
		case 3:
			index_tv.setText("现在是书签3");
			break;
		case 4:
			index_tv.setText("现在是书签4");
			break;

		default:
			break;
		}

		/**
		 *   定义Adapter
		 */
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), getData(),
				R.layout.list_item, new String[] { "name", "age" }, new int[] {
						R.id.name_tv, R.id.age_tv });
		
		// 设置Adapter
		setListAdapter(adapter);
	}
	/**
	 * 模拟数据 
	 */
	private List<Map<String, String>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (int i = 0; i < 10; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", "张三" + index);
			map.put("age", "18" + index);
			list.add(map);
		}
		return list;
	}

}
