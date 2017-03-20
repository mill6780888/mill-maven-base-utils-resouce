package mill.java.base;

import org.apache.commons.lang.ObjectUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by mill on 17/3/19.
 */
public class ObjectMillUtil {

    public static void main(String[] args) {


    }

    /**
     * 判断对象是否为空
     * @param args
     * @return
     */
    public static boolean isNull(Object args) {
        return !isNotEmpty(args);
    }
    /**
     * 判断对象是不为空,支持集合和map
     * @param obj
     * @return 空返回false，非空true
     */
    public static boolean isNotEmpty(Object obj)
    {
        if (null == obj)
        {
            return false;
        }
        if (obj instanceof Collection)
        {
            if(0 == ((Collection)obj).size())
            {
                return false;
            }
        }
        else if (obj instanceof Map)
        {
            if (0 == ((Map)obj).size())
            {
                return false;
            }
        }
        return true;
    }
}
