package in.guanjia.demo.util;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Method;

import in.guanjia.demo.app.CustomApplication;

/**
 * Description: single instance
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/20
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/20        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class Utils {

    private static Utils utils = null;
    private Utils(){
        //private empty construction
    }
    public static Utils getInstance(){
        if (utils == null){
            utils = new Utils();
        }
        return utils;
    }

    /**
     * activity跳转
     * @param className 需跳转的acticity
     */
    public void startNewActivity(Class<?> className) {
        Intent intent = new Intent(CustomApplication.mApplicationContext, className);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        CustomApplication.mApplicationContext.startActivity(intent);
    }


    /**
     * activity跳转
     * @param bundle eg. SET:Bundle bundle = new Bundle(); bundle.putString(key, value); GET:String value = getIntent().getExtras().getString(key)
     * @param className className 需跳转的acticity
     */
    public void startNewActivity(Class<?> className, Bundle bundle) {
        Intent intent = new Intent(CustomApplication.mApplicationContext, className);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        CustomApplication.mApplicationContext.startActivity(intent);
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftKeyboard(Activity context, EditText et){
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }

    public static void convertActivityToTranslucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            convertActivityToTranslucentAfterL(activity);
        } else {
            convertActivityToTranslucentBeforeL(activity);
        }
    }

    public static void convertActivityFromTranslucent(Activity activity) {
        try {
            Method method = Activity.class.getDeclaredMethod("convertFromTranslucent");
            method.setAccessible(true);
            method.invoke(activity);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


    public static void convertActivityToTranslucentBeforeL(Activity activity) {
        try {
            Class<?>[] classes = Activity.class.getDeclaredClasses();
            Class<?> translucentConversionListenerClazz = null;
            for (Class clazz : classes) {
                if (clazz.getSimpleName().contains("TranslucentConversionListener")) {
                    translucentConversionListenerClazz = clazz;
                }
            }
            Method method = Activity.class.getDeclaredMethod("convertToTranslucent",
                    translucentConversionListenerClazz);
            method.setAccessible(true);
            method.invoke(activity, new Object[] {
                    null
            });
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static void convertActivityToTranslucentAfterL(Activity activity) {
        try {
            Method getActivityOptions = Activity.class.getDeclaredMethod("getActivityOptions");
            getActivityOptions.setAccessible(true);
            Object options = getActivityOptions.invoke(activity);
            Class<?>[] classes = Activity.class.getDeclaredClasses();
            Class<?> translucentConversionListenerClazz = null;
            for (Class clazz : classes) {
                if (clazz.getSimpleName().contains("TranslucentConversionListener")) {
                    translucentConversionListenerClazz = clazz;
                }
            }
            Method convertToTranslucent = Activity.class.getDeclaredMethod("convertToTranslucent",
                    translucentConversionListenerClazz, ActivityOptions.class);
            convertToTranslucent.setAccessible(true);
            convertToTranslucent.invoke(activity, null, options);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void closeQuietly(Closeable closeable) {
        if (closeable == null) return;
        try {
            closeable.close();
        } catch (IOException ignored) {
        }
    }

}
