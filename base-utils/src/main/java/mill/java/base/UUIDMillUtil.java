package mill.java.base;
/**
 *
 * @title UUIDUtil.java
 * @package com.bjhit.erange.util
 * @copyright(c) 2009-2012 BJHIT.com All rights reserved.
 * @author xie_pkuan
 * @date 2013-3-13 上午9:52:12
 * @version V1.0
 */

import java.util.UUID;






/**
 *
 * @date 2013-3-13 上午10:08:07
 */
public class UUIDMillUtil {

    /**
     * 获取40位随机码
     *
     * @return 40位随机码
     * @author xie_pkuan
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取指定前缀的40位随机码
     *
     * @param prefix
     *            前缀
     * @return 指定前缀的40位随机码
     * @author xie_pkuan
     */
    public static String getUUID(String prefix) {
        String uuid = getUUID();
        if (prefix == null || prefix.length() > uuid.length())
            return uuid;
        return prefix + uuid.substring(prefix.length());
    }

    /**
     *
     * @return 6位随机数
     */
    public static Integer randomQueueNumber() {
        return (int) ((Math.random() * 9 + 1) * 100000);
    }
    public static void main(String[] args) {
        for(int i=0;i<20;i++)
        {
            System.out.println(getUUID());
        }
    }
}
