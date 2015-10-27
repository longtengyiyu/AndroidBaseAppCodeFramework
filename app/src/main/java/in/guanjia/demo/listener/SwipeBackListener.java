package in.guanjia.demo.listener;

import in.guanjia.demo.view.SwipeBackLayout;

/**
 * @author Yrom
 */
public interface SwipeBackListener {
    /**
     * @return the SwipeBackLayout associated with this activity.
     */
    SwipeBackLayout getSwipeBackLayout();

    void setSwipeBackEnable(boolean enable);

    /**
     * Scroll out contentView and finish the activity
     */
    void scrollToFinishActivity();

}
