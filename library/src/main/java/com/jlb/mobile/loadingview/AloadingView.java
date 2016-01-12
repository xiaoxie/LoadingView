package com.jlb.mobile.loadingview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlb.mobile.loadingview.library.R;

/**
 * @author wqr
 * @des Loading view
 */
public class AloadingView extends FrameLayout {

    private int emptyView, errorView, loadingView, contentView, animation;
    private OnClickListener onRetryClickListener;
    private OnClickListener onEmptyClickListener;

    public AloadingView(Context context) {
        this(context, null);
    }

    public AloadingView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public AloadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AloadingView, 0, 0);
        try {
            emptyView = a.getResourceId(R.styleable.AloadingView_emptyView, R.layout.aloading_empty_view);
            errorView = a.getResourceId(R.styleable.AloadingView_errorView, R.layout.aloading_error_view);
            loadingView = a.getResourceId(R.styleable.AloadingView_loadingView, R.layout.aloading_view);
            contentView = a.getResourceId(R.styleable.AloadingView_contentView, 0);
            animation = a.getResourceId(R.styleable.AloadingView_animation, 0);
            LayoutInflater inflater = LayoutInflater.from(getContext());
            inflater.inflate(emptyView, this, true);  // 0
            inflater.inflate(errorView, this, true); // 1
            inflater.inflate(loadingView, this, true); //2
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        for (int i = 0; i < getChildCount() - 1; i++) {
            getChildAt(i).setVisibility(GONE);
        }

        findViewById(R.id.btn_retry).setOnClickListener(v -> {
            if (null != onEmptyClickListener) {
                onEmptyClickListener.onClick(v);
            }
        });

        findViewById(R.id.btn_retry).setOnClickListener(v -> {
            if (null != onRetryClickListener) {
                onRetryClickListener.onClick(v);
            }
        });
    }

    public void setOnRetryClickListener(OnClickListener onRetryClickListener) {
        this.onRetryClickListener = onRetryClickListener;
    }

    public void setOnEmptyClickListener(OnClickListener onEmptyClickListener) {
        this.onEmptyClickListener = onEmptyClickListener;
    }

    /**
     * 显示默认空页面
     */
    public void showEmpty() {
        this.showEmpty(null, 0);
    }

    /**
     * 显示指定内容的空页面
     *
     * @param info
     * @param resId
     */
    public void showEmpty(String info, int resId) {
        for (int i = 0; i < this.getChildCount(); i++) {
            View child = this.getChildAt(i);
            if (i == 0) {
                setEmptyInfoCostom(info, resId, child);
                child.setVisibility(VISIBLE);
            } else {
                child.setVisibility(GONE);
            }
        }
    }

    /**
     * 显示错误页
     */
    public void showError() {
        this.showError(null, 0);
    }

    /**
     * 显示错误页，指定内容
     *
     * @param info
     * @param resId
     */
    public void showError(String info, int resId) {
        for (int i = 0; i < this.getChildCount(); i++) {
            View child = this.getChildAt(i);
            if (i == 1) {
                setEmptyInfoCostom(info, resId, child);
                child.setVisibility(VISIBLE);
            } else {
                child.setVisibility(GONE);
            }
        }
    }

    /**
     * 显示错误页，指定内容、是否显示重试按钮
     *
     * @param info
     * @param resId
     * @param showRetryButton
     */
    public void showError(String info, int resId, boolean showRetryButton) {
        showError(info, resId);
        View retry = findViewById(R.id.btn_retry);
        if (retry != null) retry.setVisibility(GONE);
    }

    /**
     * 显示加载页
     */
    public void showLoading() {
        this.showLoading(null, 0);
    }

    /**
     * 显示加载页，指定内容
     *
     * @param info
     * @param resId
     */
    public void showLoading(String info, int resId) {
        for (int i = 0; i < this.getChildCount(); i++) {
            View child = this.getChildAt(i);
            if (i == 2) {
                child.setVisibility(VISIBLE);
                ImageView iv = (ImageView) child.findViewById(R.id.aload_icon);
                if (iv != null)
                    startAnimation(iv);
            } else {
                child.setVisibility(GONE);
            }
        }
    }

    /**
     * 显示 contentView
     */
    public void showContent() {
        for (int i = 0; i < this.getChildCount(); i++) {
            View child = this.getChildAt(i);
            if (i == 3 || child.getId() == contentView) {
                child.setVisibility(VISIBLE);
            } else {
                child.setVisibility(GONE);
            }
        }
    }

    private void setEmptyInfoCostom(String info, int resId, View child) {
        if (!TextUtils.isEmpty(info)) {
            TextView tv = (TextView) child.findViewById(R.id.aload_info);
            if (tv != null)
                tv.setText(info);
        }
        if (resId > 0) {
            ImageView iv = (ImageView) child.findViewById(R.id.aload_icon);
            if (iv != null)
                iv.setImageResource(resId);
        }
    }

    private void startAnimation(ImageView iv) {
        AnimationDrawable animDrawable = (AnimationDrawable) iv.getDrawable();
        if (null == animDrawable) {
            iv.setImageResource(animation == 0 ? R.anim.loading : animation);
            animDrawable = (AnimationDrawable) iv.getDrawable();
        }
        iv.clearAnimation();
        animDrawable.start();
    }
}
