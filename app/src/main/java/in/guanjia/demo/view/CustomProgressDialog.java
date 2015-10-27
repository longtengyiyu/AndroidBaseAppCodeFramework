package in.guanjia.demo.view;

import android.content.Context;

/**
 * Description:自定义dialog
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/26
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/26        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class CustomProgressDialog {
    private static android.app.ProgressDialog mProgressDialog;

    private CustomProgressDialog(){

    }

    /**
     * 显示progress dialog
     * @param msg content
     */
    public static void showProgressDialog(Context context, String msg){
        if (mProgressDialog == null){
            mProgressDialog = new android.app.ProgressDialog(context);
        }
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    /**
     * 显示progress dialog
     * @param Res content资源id
     */
    public static void showProgressDialog(Context context, int Res){
        if (mProgressDialog == null){
            mProgressDialog = new android.app.ProgressDialog(context);
        }
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setMessage(context.getResources().getString(Res));
        mProgressDialog.show();
    }

    public static void dismissProgressDialog(){
        if (mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }
}
