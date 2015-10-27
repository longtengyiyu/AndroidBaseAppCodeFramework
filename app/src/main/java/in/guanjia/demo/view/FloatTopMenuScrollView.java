package in.guanjia.demo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import in.guanjia.demo.listener.OnScrollListener;

/**
 * Description: 浮动顶部菜单ScrollView（用于ScrollView 的滑动监听）
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/27
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/27        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class FloatTopMenuScrollView extends ScrollView {
	private OnScrollListener onScrollListener;
	
	public FloatTopMenuScrollView(Context context) {
		this(context, null);
	}
	
	public FloatTopMenuScrollView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FloatTopMenuScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	
	/**
	 * 滑动监听
	 * @param onScrollListener
	 */
	public void setOnScrollListener(OnScrollListener onScrollListener) {
		this.onScrollListener = onScrollListener;
	}
	

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if(onScrollListener != null){
			onScrollListener.onScroll(t);
		}
	}

}
