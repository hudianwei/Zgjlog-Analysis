package knet.zgjlog.analysis;

import java.util.Collection;
import java.util.Map;

/**
 * @Description:
 * @author: HU
 * @date: 2018/12/4 9:25
 */
public class DataUtil {
    /**
     * 判断目标对象是否为空。
     *
     * <pre>
     * DataUtils.isBlank(null)      = true
     * DataUtils.isBlank("")        = true
     * DataUtils.isBlank(" ")       = true
     * DataUtils.isBlank("bob")     = false
     * DataUtils.isBlank(new ArrayList()) = true
     * DataUtils.isBlank(new String[1]) = true
     * </pre>
     *
     * @param o
     *            目标对象。
     * @return 目标对象是否为空。
     */
    public static boolean isBlank(Object o) {
        if (o == null) {
            return true;
        }

        if (o instanceof String) {
            return isBlankCharSequence((String) o);
        } else if (o instanceof Collection) {
            return ((Collection<?>) o).isEmpty();
        } else if (o instanceof Map) {
            return ((Map<?, ?>) o).isEmpty();
        } else if (o instanceof Object[]) {
            return ((Object[]) o).length == 0;
        }

        return false;
    }

    /**
     * @Description: 判断字符序列是否为空
     * @Param: [cs]
     * @return: boolean
     * @Author: HU
     * @Date: 2018/8/3
     */

    private static boolean isBlankCharSequence(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

}
