package in.guanjia.demo.listener;

/**
 * Description:
 * Author:    Oscar
 * Version    V1.0
 * Date:      2016/2/23
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2016/2/23        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public interface OnRecyclerViewClickItemListener {
    /**
     *  itmeView  点击事件监听
     * @param position 点击索引
     * @param objects  传递对象
     */
    void onItemClick(int position, Object...objects);
}
