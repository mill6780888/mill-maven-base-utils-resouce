package mill.java.base;

import com.sun.deploy.net.proxy.ProxyUtils;

/**
 * Created by mill on 17/3/20.
 */
public class StringMillUtil {
    /**
     * 判断一个字符串是否为空
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        return s==null||s.length()==0;
    }
}
