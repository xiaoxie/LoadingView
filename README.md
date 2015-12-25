## LoadingLayout

APP经常有这样的情景，加载页面时不同的状态给用户展示不同的页面，让用户减少等待的焦虑。
为了方便的切换到加载中、空页面、出错页面、重试、内容页面 几种场景 。

![效果图](/demo/device-2015-12-25-184234.png)

LoadingLayout集成自Framelayout，默认把第一个子view当做内容视图，其他的子view会被忽略

使用如下：

```
<<com.jlb.mobile.loadingview.library.LoadingLayout
    android:id="@+id/loading_layout"
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_weight="1">
  <RelativeLayout
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      >
    <include layout="@layout/content_view" />
  </RelativeLayout>
</<com.jlb.mobile.loadingview.library.LoadingLayout>
```

可以通过代码来切换到不同的view

```
final LoadingLayout loadingLayout = (LoadingLayout) findViewById(R.id.loading_layout);
findViewById(R.id.btn_content).setOnClickListener((view) -> loadingLayout.showContent());
findViewById(R.id.btn_error).setOnClickListener((view) -> loadingLayout.showError());
findViewById(R.id.btn_empty).setOnClickListener((view) -> loadingLayout.showEmpty());
findViewById(R.id.btn_loading).setOnClickListener((view) -> loadingLayout.showLoading());
```

另外，针对errorView和emptyView，提供了两个重新加载的按钮。
需要注意的是,这里的重新加载的按钮的id必须是btn_error_retry或者btn_empty_retry
```
loadingLayout.setOnRetryClickListener((view) -> loadingLayout.showLoading());
```


## License

MIT
