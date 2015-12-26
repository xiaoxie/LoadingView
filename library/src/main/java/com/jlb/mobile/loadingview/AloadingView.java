package com.jlb.mobile.loadingview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.jlb.mobile.loadingview.library.R;

/**
 * Loading view
 */
public class AloadingView extends FrameLayout {

    private int emptyView, errorView, loadingView, contentView;
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

        findViewById(R.id.btn_empty_retry).setOnClickListener(v -> {
            if (null != onEmptyClickListener) {
                onEmptyClickListener.onClick(v);
            }
        });

        findViewById(R.id.btn_error_retry).setOnClickListener(v -> {
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

    public void showEmpty() {
        for (int i = 0; i < this.getChildCount(); i++) {
            View child = this.getChildAt(i);
            if (i == 0) {
                child.setVisibility(VISIBLE);
            } else {
                child.setVisibility(GONE);
            }
        }
    }

    public void showError() {
        for (int i = 0; i < this.getChildCount(); i++) {
            View child = this.getChildAt(i);
            if (i == 1) {
                child.setVisibility(VISIBLE);
            } else {
                child.setVisibility(GONE);
            }
        }
    }

    public void showLoading() {
        for (int i = 0; i < this.getChildCount(); i++) {
            View child = this.getChildAt(i);
            if (i == 2) {
                child.setVisibility(VISIBLE);
            } else {
                child.setVisibility(GONE);
            }
        }
    }

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
}
