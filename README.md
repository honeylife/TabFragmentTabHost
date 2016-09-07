# TabFragmentTabHost项目
#FragmentTabHost的简单使用

*实现Imageview 和Textview 组合的tab点击切换功能，

#布局文件
```xml
 <android.support.v4.app.FragmentTabHost
        android:id="@android:id/tabhost"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/master_white_color">
        
        <FrameLayout
            android:id="@android:id/tabcontent"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_weight="0" />
    </android.support.v4.app.FragmentTabHost>
    
    <FrameLayout
        android:id="@+id/realtabcontent"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1" />
```
# 在activity中相应的处理：

```java
public class ListActivity extends BaseActivity {
    public static final int INDEX_ALL_ORDER = 2;
    private String mTextviewArray[] = {"首页", "发现", "我"};   // 这里的值可根据需求自己添加
    private FragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;
    //  可更具自己的需求是否创建多个fragment
    private Class fragmentArray[] = {ListFragment.class, ListFragment
            .class, ListFragment.class};
    private int autoID = 0;
    
    @Override
    protected void getBundle() {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            autoID = bundle.getInt(BUNDLE_ID, 0);
        }
        super.getBundle();
    }
    
    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_list);
        layoutInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);
        for (int i = 0; i < mTextviewArray.length; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator
                    (getTabItemView(i));
            Bundle bundle = new Bundle();  // 绑定相关的值到点击之后对应的fragment
            bundle.putString(StoreBundle.BUNDLE_TAG, mTextviewArray[i]);
            mTabHost.addTab(tabSpec, fragmentArray[i], bundle);
        }
        mTabHost.setCurrentTabByTag(mTextviewArray[autoID]);
        updateTab(mTabHost);
        mTabHost.setOnTabChangedListener(new OnTabChangedListener());
    }
    
    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);
        View vi = view.findViewById(R.id.vi);
        vi.setBackgroundColor(getResources().getColor(R.color.master_white_color));
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);
        return view;
    }
  // TextView与ImageView的组合，切换页面选中与不选中的逻辑
    private void updateTab(final TabHost tabHost) {
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            View vi = tabHost.getTabWidget().getChildAt(i).findViewById(R.id.vi);
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(R.id
                    .textview);
            if (tabHost.getCurrentTab() == i) {//选中
                vi.setBackgroundColor(getResources().getColor(R.color.master_text_color_red));
                tv.setTextColor(getResources().getColor(R.color.master_text_color_red));
            } else {//不选中
                vi.setBackgroundColor(getResources().getColor(R.color.master_white_color));
                tv.setTextColor(getResources().getColor(R.color.master_text_color_1));
            }
        }
    }
    class OnTabChangedListener implements TabHost.OnTabChangeListener {
        @Override
        public void onTabChanged(final String tabId) {
            mTabHost.setCurrentTabByTag(tabId);
            updateTab(mTabHost);
        }
    }
}
```
