## LoadingView

APP 中经常有这样的情景，加载页面时不同的状态给用户展示不同的页面，让用户减少等待的焦虑。
为了方便的切换到加载中、空页面、出错页面、重试、内容页面 几种场景 。

![效果图](/screenshots/device-2015-12-26-115331.png)
![效果图](/screenshots/device-2015-12-26-120941.png)

##AloadingView集成自Framelayout，使用如下：


* 默认把第一个子 View 当做内容视图，其他的子 View 会被忽略

```
<com.jlb.mobile.loadingview.library.LoadingLayout
    android:id="@+id/loading_layout"
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_weight="1">
        <include layout="@layout/content_view" />
</<com.jlb.mobile.loadingview.library.LoadingLayout>
```

* 存在多个子 View 时，默认把第一个子 View 当做内容视图，其他的子 View 会被忽略

```
<com.jlb.mobile.loadingview.library.LoadingLayout
    android:id="@+id/loading_layout"
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_weight="1">
        <include layout="@layout/content_view1" />  //显示
        <include layout="@layout/content_view2" />  //不显示
        <include layout="@layout/content_view3" />  //不显示
        <include layout="@layout/content_view4" />  //不显示
</com.jlb.mobile.loadingview.library.LoadingLayout>
```

* 存在多个子 View 时，推荐添加一个 ContainView 包裹，用方法类似ScrollView

```
<com.jlb.mobile.loadingview.library.LoadingLayout
    android:id="@+id/loading_layout"
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_weight="1">
        <include layout="@layout/ContainView" />  //显示
</com.jlb.mobile.loadingview.library.LoadingLayout>
```



```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
        <include layout="@layout/content_view1" /> 
        <include layout="@layout/content_view2" />
        <include layout="@layout/content_view3" />
        <include layout="@layout/content_view4" />
</LinearLayout>
```

切换到不同 View 的代码如下：

```
final LoadingLayout loadingLayout = (LoadingLayout) findViewById(R.id.loading_layout);
findViewById(R.id.btn_content).setOnClickListener((view) -> loadingLayout.showContent());
findViewById(R.id.btn_error).setOnClickListener((view) -> loadingLayout.showError());
findViewById(R.id.btn_empty).setOnClickListener((view) -> loadingLayout.showEmpty());
findViewById(R.id.btn_loading).setOnClickListener((view) -> loadingLayout.showLoading());
```

另外，针对 errorView 和 emptyView ，提供了两个重新加载的按钮

```
loadingLayout.setOnRetryClickListener((view) -> loadingLayout.showLoading());
loadingLayout.setOnEmptyClickListener((view) -> loadingLayout.showLoading());
```


## License

MIT
