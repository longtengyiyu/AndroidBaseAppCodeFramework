package in.guanjia.demo.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import in.guanjia.demo.param.TextViewParam;

/**
 * Description: Custom loading TextView
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/9/6
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/9/6        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class LoadingTextView extends LinearLayout {
    private static final int TIME_0 = 0;
    private static final int TIME_1 = 1;
    private static final int TIME_2 = 2;

    private long mDuring = 1000L;
    private boolean isLoading = true;
    private int mCurrentIndex = 0;

    private TextView mContentTextView;
    private TextView mLoadingTextView;

    private static LoadingHandler mLoadingHandler;

    public LoadingTextView(Context context) {
        super(context, null, 0);
        init();
    }

    public LoadingTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        init();
    }

    public LoadingTextView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        init();
    }

    private void init() {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mContentTextView = new TextView(getContext());
        mLoadingTextView = new TextView(getContext());
        addView(mContentTextView, 0);
        addView(mLoadingTextView, 1);
        setOrientation(HORIZONTAL);
        setLayoutParams(layoutParams);
    }

    //set content text color
    public void setTextColor(int colorResource) {
        mContentTextView.setTextColor(colorResource);
        mLoadingTextView.setTextColor(colorResource);
    }

    //set content text size
    public void setTextSize(float size) {
        mContentTextView.setTextSize(size);
        mLoadingTextView.setTextSize(size);
    }

    //set content text size and color
    public void setTextSizeAndColor(float size, int colorResource) {
        mContentTextView.setTextSize(size);
        mContentTextView.setTextColor(colorResource);
        mLoadingTextView.setTextSize(size);
        mLoadingTextView.setTextColor(colorResource);
    }

    //set content text with string
    public void setTextContent(String content) {
        mContentTextView.setText(content);
    }

    //set content text with resource id
    public void setTextContent(int resourceId) {
        mContentTextView.setText(resourceId);
    }

    //set content text content size color with resource string
    public void setTextContentColorSize(String content, float size, int colorResource) {
        mContentTextView.setText(content);
        mContentTextView.setTextSize(size);
        mContentTextView.setTextColor(colorResource);

        mLoadingTextView.setTextSize(size);
        mLoadingTextView.setTextColor(colorResource);

    }

    //set content text content size color with resource resource id
    public void setTextContentColorSize(int resourceId, float size, int colorResource) {
        mContentTextView.setText(resourceId);
        mContentTextView.setTextSize(size);
        mContentTextView.setTextColor(colorResource);

        mLoadingTextView.setTextSize(size);
        mLoadingTextView.setTextColor(colorResource);
    }

    public void setLayoutParams(int width, int height) {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, height);
        setLayoutParams(layoutParams);
    }

    public void setDuring(long during) {
        mDuring = during;
    }

    public long getDuring() {
        return mDuring;
    }

    public void startLoading() {
        if (mLoadingHandler == null) {
            mLoadingHandler = new LoadingHandler(this);
        } else {
            mLoadingHandler.setContext(this);
        }

        Thread mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isLoading) {
                    try {
                        Thread.sleep(mDuring);
                        mLoadingHandler.sendEmptyMessage(mCurrentIndex);
                        mCurrentIndex++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        mThread.start();
    }

    public void stopLoading() {
        isLoading = false;
        postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadingTextView.setText("");
            }
        }, 500L);
    }

    private static class LoadingHandler extends Handler {
        private WeakReference<LoadingTextView> contextWeakReference;

        public LoadingHandler(LoadingTextView view) {
            contextWeakReference = new WeakReference<>(view);
        }

        public void setContext(LoadingTextView view) {
            contextWeakReference.clear();
            contextWeakReference = new WeakReference<>(view);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LoadingTextView view = contextWeakReference.get();
            switch (msg.what) {
                case TIME_0:
                    view.mLoadingTextView.setText(".");
                    break;
                case TIME_1:
                    view.mLoadingTextView.setText("..");
                    break;
                case TIME_2:
                    view.mLoadingTextView.setText("...");
                    view.mCurrentIndex = 0;
                    break;
            }
        }
    }

    private void aVoid() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.create();
    }

    //hope use builder set a loading TextView
    public static class Builder {
        private TextViewParam param;
        private Context mContext;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setContent(String content){
            param.content =  content;
            return this;
        }

        //other theory
        public void create() {
            LoadingTextView view = new LoadingTextView(mContext);
            view.init();
        }
    }
}
