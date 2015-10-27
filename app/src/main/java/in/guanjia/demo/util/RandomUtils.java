package in.guanjia.demo.util;

/**
 * Description:
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/21
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/21        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class RandomUtils {
    /**
     * 字符串随机数-指定长度的
     *
     * @param sLen
     * @return
     */
    public static String randomKey(int sLen) {
        final String base = "1234567890";
        StringBuilder temp = new StringBuilder();
        int p;
        for (int i = 0; i < sLen; i++) {
            p = (int) (Math.random() * 10);
            temp.append(base.substring(p, p + 1));
        }
        return temp.toString();
    }
}
