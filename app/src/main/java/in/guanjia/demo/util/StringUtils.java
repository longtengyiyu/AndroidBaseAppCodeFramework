package in.guanjia.demo.util;

/**
 * Description:字符串帮助类
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/21
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/21        Oscar            1.0                    1.0
 * Why & What is modified:
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        return str == null || str.equals("null") || str.equals("");
    }

    public static boolean notEmpty(String str) {
        return !isEmpty(str);
    }

}
