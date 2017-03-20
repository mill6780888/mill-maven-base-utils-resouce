package mill.java.base;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;







/**
 * @date 2013-3-18 下午2:50:26
 */
public class StringMillUtil {

    /**
     * Windows操作系统文件路径分隔符
     */
    private static final String SEPARATOR_WINDOW = "\\";

    /**
     * Linux/Unix操作系统文件路径分隔符
     */
    private static final String SEPARATOR_LINUX = "/";
    /**
     * 汉字大写数字
     */
    private static final String[] TRADITIONAL_CHINESE = { "零", "壹", "贰", "叁",
            "肆", "伍", "陆", "柒", "捌", "玖" };

    /**
     * 汉字小写数字
     */
    private static final String[] SIMPLIFIED_CHINESE = { "零", "一", "二", "三",
            "四", "五", "六", "七", "八", "九" };

    /**
     * 汉字小写数量单位
     */
    private static final String[] SIMPLIFIED_UNITS = { "", "十", "百", "千", "万",
            "十", "百", "千", "亿" };

    /**
     * 汉字大写数量单位
     */
    private static final String[] TRADITIONAL_UNITS = { "", "拾", "佰", "仟", "万",
            "拾", "佰", "仟", "亿" };

    /**
     * 汉字 - 零
     */
    private static final String ZERO = "零";

    /**
     * 邮箱的正则表达式
     */
    public static final String REGMAIL = "^([a-zA-Z0-9]+[-|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[-|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";

    /**
     * 注册用户名的正则表达式
     */
    public static final String REGNAME = "^([\u4e00-\u9fa5_a-zA-Z0-9]){1,16}";

    /**
     * 手机的正则表达式
     */
    public static final String PHONE = "^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8]))\\d{8}$";

    /**
     * 固定电话号码正则表达式(必须带区号)
     */
    public static final String PHONEREGWITHAREA = "^[0][1-9][0-9]{1,2}-[0-9]{5,10}$";

    /**
     * 固定电话号码正则表达式(可以不带区号)
     */

    public static final String PHONEREGWITHAREA1 = "^[0][1-9][0-9]{1,2}[0-9]{5,10}$";
    /**
     * 手机号码正则表达式
     */
    public static final String PHONEREGNOAREA = "^[1-9]{1}[0-9]{5,8}$";

    /**
     * 是否数字(Integer)
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        boolean flag = true;
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 是否是Double类型的数字
     *
     * @param str
     * @return
     * @version V2.0
     */
    public static boolean isDBNumber(String str) {
        boolean flag = true;
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 是否是Long类型的数字
     *
     * @param str
     * @return
     * @version V2.0
     */
    public static boolean isLNumber(String str) {
        boolean flag = true;
        try {
            Long.parseLong(str);
        } catch (NumberFormatException e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 是否手机号码
     *
     * @param str
     * @return
     * @version V2.0
     */
    public static boolean isPhone(String str) {
        boolean flag = true;
        if (notNOEs(str)) {
            flag = str.matches(PHONE);
        }
        return flag;
    }

    /**
     * 是否是电话支持带区号,区号和电话之间必须带 “-” 例如 010-69681000
     *
     * @return
     * @version V2.0
     */
    public static boolean isHomePhone(String strPhone) {
        boolean flag = false;
        if (notNOEs(strPhone)) {
            if (strPhone.length() > 9) {
                if (strPhone.matches(PHONEREGWITHAREA)) {
                    flag = true;
                } else {
                    if (strPhone.matches(PHONEREGWITHAREA1)) {
                        flag = true;
                        return flag;
                    }
                    flag = false;
                }
            } else {
                flag = strPhone.matches(PHONEREGNOAREA);
            }
        }
        return flag;
    }

    /**
     * 是否为邮箱格式
     *
     * @param str
     * @return
     * @version V2.0
     */
    public static boolean isEmail(String str) {
        boolean flag = true;
        if (notNOEs(str)) {
            flag = str.matches(REGMAIL);
        }
        return flag;
    }

    /**
     * 合并字符串
     *
     * @param ss
     *            字符串序列
     * @return
     */
    public static String mergeString(String... ss) {
        StringBuffer sBuffer = new StringBuffer();
        for (String s : ss) {
            sBuffer.append(s);
        }
        return sBuffer.toString();
    }

    /**
     * 合并一个字符串集合
     *
     * @param strs
     * @return
     * @version V2.0
     */
    public static String mergeString(List<String> strs) {
        StringBuffer sBuffer = new StringBuffer();
        for (String s : strs) {
            sBuffer.append(s);
        }
        return sBuffer.toString();
    }

    /**
     * 合并对象为字符串
     *
     * @param objs
     *            对象序列
     * @return
     */
    public static String mergeString(Object... objs) {
        StringBuffer sBuffer = new StringBuffer();
        for (Object o : objs) {
            sBuffer.append(o);
        }
        return sBuffer.toString();
    }

    /**
     * 根据占位符(推荐使用$)加下标……进行匹配格式化字符串
     *
     * @param source
     * @param placeholder
     * @param objs
     * @return
     * @version V2.0
     */
    public static String format(String source, String placeholder,
                                Object... objs) {
        for (int idx = 0; idx < objs.length; idx++) {
            if (source.contains(placeholder + idx) && objs[idx] != null) {
                source = source
                        .replace(placeholder + idx, objs[idx].toString());
            }
        }
        return source;
    }

    /**
     * 根据占位符(推荐使用$)加指定key……进行匹配格式化字符串
     *
     * @param source
     * @param placeholder
     * @param formats
     * @return
     * @version V2.0
     */
    public static String format(String source, String placeholder,
                                Map<String, String> formats) {
        Iterator<String> it = formats.keySet().iterator();
        while (it.hasNext()) {
            String format = it.next();
            if (source.contains(placeholder + format)
                    && formats.get(format) != null) {
                source = source.replace(placeholder + format,
                        formats.get(format));
            }
        }
        return source;
    }

    /**
     * 判断字符串是否为空指针或空字符串
     *
     * @param param
     *            字符串参数
     * @return
     */
    public static boolean isNullOrEmpty(String param) {
        if (null == param || param.isEmpty())
            return true;
        return false;
    }

    /**
     * 与isNullOrEmpty是相反的
     *
     * @param param
     * @return
     * @version V2.0
     */
    public static boolean notNOE(String param) {
        return !isNullOrEmpty(param);
    }

    /**
     * 长度介于两个值之间
     *
     * @param param
     * @param minLength
     * @param maxLength
     * @return
     * @version V2.0
     */
    public static boolean lengthBetween(String param, int minLength,
                                        int maxLength) {
        boolean flag = false;
        if (notNOE(param)) {
            if (param.length() >= minLength && param.length() <= maxLength) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * @Description: 正则验证
     * @param: @param param
     * @param: @param matches
     * @param: @return
     * @return: boolean
     * @throws:
     * @Author:justhin
     */
    public static boolean matchesStr(String param, String matches) {
        if (notNOEs(param, matches)) {
            return param.matches(matches);
        }
        return false;
    }

    /**
     * 判断一组字符串是否全都不为空
     *
     * @param args
     * @return
     * @version V2.0
     */
    public static boolean notNOEs(String... args) {
        for (String item : args)
            if (!notNOE(item)) {
                return false;
            }
        return true;
    }

    public static String script(String param) {
        return param.replaceAll(">", "&gt;").replaceAll("<", "&lt;")
                .replaceAll("\"", "&quot;").replaceAll("'", "&#39;")
                .replaceAll(" ", "");
    }

    public static String valisql(String param) {
        return param.replaceAll("'", "").replaceAll("=", "")
                .replaceAll(" ", "").replaceAll(">", "").replaceAll("<", "");
    }

    /**
     * 判断字符串是"null",调用了isNullOrEmpty
     *
     * @param param
     *            字符串参数
     * @return
     */
    public static boolean eqNull(String param) {
        if (isNullOrEmpty(param) || param.equalsIgnoreCase("null")) {
            return true;
        }
        return false;
    }

    /**
     * 不为空，并且不equals“null”字符串
     *
     * @param param
     * @return
     * @version V2.0
     */
    public static boolean notNull(String param) {
        return !eqNull(param);
    }

    /**
     * 首字母大写
     *
     * @param str
     *            字符串
     * @return
     */
    public static String initialToUpper(String str) {
        char[] array = str.toCharArray();
        if (array[0] > 96 && array[0] < 123)
            array[0] -= 32;
        return String.valueOf(array);
    }

    /**
     * 首字母小写
     *
     * @param str
     *            字符串
     * @return
     */
    public static String initialToLower(String str) {
        char[] array = str.toCharArray();
        if (array[0] > 64 && array[0] < 91)
            array[0] += 32;
        return String.valueOf(array);
    }

    /**
     * 转换数字为汉字形式
     *
     * @param number
     *            数字
     * @return 转换后的字符串
     */
    public static String toChinese(int number) {
        StringBuffer result = new StringBuffer();
        String str = Integer.toString(number);
        int length = str.length();
        boolean bZero = false;
        for (int i = 0; i < length; i++) {
            String ch = SIMPLIFIED_CHINESE[Integer.parseInt(str.charAt(i) + "")];
            String digit = SIMPLIFIED_UNITS[length - 1 - i];
            // 处理零和连续零的情况
            if (ch.equals(ZERO)) {
                bZero = true;
                if (digit.equals("万")) {
                    result.append(digit);
                }
                continue;
            }
            if (bZero) {
                result.append(ZERO);
                bZero = false;
            }
            // 处理一十的情况
            if (digit.equals("十") && ch.equals("一")) {
                result.append(digit);
                continue;
            }
            result.append(ch).append(digit);
        }
        return result.toString().trim();
    }

    /**
     * 把金额转换为汉字表示的数量，小数点后四舍五入保留两位
     *
     * @param amount
     * @return
     */
    public static String amount2Chinese(double amount) {
        if (amount > 99999999999999.99 || amount < -99999999999999.99)
            throw new IllegalArgumentException(
                    "参数值超出允许范围 (-99999999999999.99 ～  99999999999999.99)！");
        boolean negative = false;
        if (amount < 0) {
            negative = true;
            amount = amount * (-1);
        }
        long temp = Math.round(amount * 100);
        int numFen = (int) (temp % 10); // 分
        temp = temp / 10;
        int numJiao = (int) (temp % 10); // 角
        temp = temp / 10;// temp 目前是金额的整数部分
        int[] parts = new int[20]; // 其中的元素是把原来金额整数部分分割为值在 0~9999 之间的数的各个部分
        int numParts = 0; // 记录把原来金额整数部分分割为了几个部分（每部分都在 0~9999 之间）
        for (int i = 0;; i++) {
            if (temp == 0)
                break;
            int part = (int) (temp % 10000);
            parts[i] = part;
            numParts++;
            temp = temp / 10000;
        }
        boolean beforeWanIsZero = true; // 标志“万”下面一级是不是 0
        String chineseStr = "";
        for (int i = 0; i < numParts; i++) {
            String partChinese = partTranslate(parts[i]).trim();
            if (i % 2 == 0) {
                if ("".equals(partChinese))
                    beforeWanIsZero = true;
                else
                    beforeWanIsZero = false;
            }
            if (i != 0) {
                if (i % 2 == 0)
                    chineseStr = "亿" + chineseStr;
                else {
                    if ("".equals(partChinese) && !beforeWanIsZero) // 如果“万”对应的
                        // part 为
                        // 0，而“万”下面一级不为
                        // 0，则不加“万”，而加“零”
                        chineseStr = ZERO + chineseStr;
                    else {
                        if (parts[i - 1] < 1000 && parts[i - 1] > 0) // 如果"万"的部分不为
                            // 0,
                            // 而"万"前面的部分小于
                            // 1000
                            // 大于 0，
                            // 则万后面应该跟“零”
                            chineseStr = ZERO + chineseStr;
                        chineseStr = "万" + chineseStr;
                    }
                }
            }
            chineseStr = partChinese + chineseStr;
        }
        if ("".equals(chineseStr)) // 整数部分为 0, 则表达为"零元"
            chineseStr = TRADITIONAL_CHINESE[0];
        else if (negative) // 整数部分不为 0, 并且原金额为负数
            chineseStr = "负" + chineseStr;
        chineseStr = chineseStr + "元";
        if (numFen == 0 && numJiao == 0) {
            chineseStr = chineseStr + "整";
        } else if (numFen == 0) { // 0 分，角数不为 0
            chineseStr = chineseStr + TRADITIONAL_CHINESE[numJiao] + "角";
        } else { // “分”数不为 0
            if (numJiao == 0)
                chineseStr = chineseStr + ZERO + TRADITIONAL_CHINESE[numFen]
                        + "分";
            else
                chineseStr = chineseStr + TRADITIONAL_CHINESE[numJiao] + "角"
                        + TRADITIONAL_CHINESE[numFen] + "分";
        }
        return chineseStr;
    }

    /**
     * 把一个 0~9999 之间的整数转换为汉字的字符串，如果是 0 则返回 ""
     *
     * @param amountPart
     * @return
     */
    private static String partTranslate(int amountPart) {
        if (amountPart < 0 || amountPart > 10000) {
            throw new IllegalArgumentException("参数必须是大于等于 0，小于 10000 的整数！");
        }
        int temp = amountPart;
        String amountStr = new Integer(amountPart).toString();
        int amountStrLength = amountStr.length();
        boolean lastIsZero = true; // 在从低位往高位循环时，记录上一位数字是不是 0
        String chineseStr = "";
        for (int i = 0; i < amountStrLength; i++) {
            if (temp == 0) // 高位已无数据
                break;
            int digit = temp % 10;
            if (digit == 0) { // 取到的数字为 0
                if (!lastIsZero) // 前一个数字不是 0，则在当前汉字串前加“零”字;
                    chineseStr = ZERO + chineseStr;
                lastIsZero = true;
            } else { // 取到的数字不是 0
                chineseStr = TRADITIONAL_CHINESE[digit] + TRADITIONAL_UNITS[i]
                        + chineseStr;
                lastIsZero = false;
            }
            temp = temp / 10;
        }
        return chineseStr;
    }

    /**
     * 金额转换成中文（性能稍差）
     *
     * @param moneyNumber
     * @return
     */
    @Deprecated
    public static String amount2Chinese2(double moneyNumber) {
        String[] cPattern = { "", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿" };
        String[] cfPattern = { "", "角", "分" };
        String moneyString = Double.toString(moneyNumber);
        int dotPoint = moneyString.indexOf("."); // 判断是否为小数
        String moneyStr;
        if (dotPoint != -1) {
            moneyStr = moneyString.substring(0, moneyString.indexOf("."));
        } else {
            moneyStr = moneyString;
        }
        StringBuffer fraction = null; // 小数部分的处理,以及最后的yuan.
        StringBuffer ms = new StringBuffer();
        for (int i = 0; i < moneyStr.length(); i++) {
            ms.append(TRADITIONAL_CHINESE[moneyStr.charAt(i) - 48]); // 按数组的编号加入对应大写汉字
        }

        int cpCursor = 1;
        for (int j = moneyStr.length() - 1; j > 0; j--) {
            ms.insert(j, cPattern[cpCursor]); // 在j之后加字符,不影响j对原字符串的相对位置
            // 只是moneyStr.length()不断增加
            // insert(j,"string")就在j位置处插入,j=0时为第一位
            cpCursor = cpCursor == 8 ? 1 : cpCursor + 1; // 亿位之后重新循环
        }

        while (ms.indexOf("零拾") != -1) { // 当十位为零时用一个"零"代替"零拾"
            // replace的起始于终止位置
            ms.replace(ms.indexOf("零拾"), ms.indexOf("零拾") + 2, ZERO);
        }
        while (ms.indexOf("零佰") != -1) { // 当百位为零时,同理
            ms.replace(ms.indexOf("零佰"), ms.indexOf("零佰") + 2, ZERO);
        }
        while (ms.indexOf("零仟") != -1) { // 同理
            ms.replace(ms.indexOf("零仟"), ms.indexOf("零仟") + 2, ZERO);
        }
        while (ms.indexOf("零万") != -1) { // 万需保留，中文习惯
            ms.replace(ms.indexOf("零万"), ms.indexOf("零万") + 2, "万");
        }
        while (ms.indexOf("零亿") != -1) { // 同上
            ms.replace(ms.indexOf("零亿"), ms.indexOf("零亿") + 2, "亿");
        }
        while (ms.indexOf("零零") != -1) {// 有连续数位出现零，即有以下情况，此时根据习惯保留一个零即可
            ms.replace(ms.indexOf("零零"), ms.indexOf("零零") + 2, ZERO);
        }
        while (ms.indexOf("亿万") != -1) { // 特殊情况，如:100000000,根据习惯保留高位
            ms.replace(ms.indexOf("亿万"), ms.indexOf("亿万") + 2, "亿");
        }
        while (ms.lastIndexOf("零") == ms.length() - 1) { // 当结尾为零j，不必显示,经过处理也只可能出现一个零
            ms.delete(ms.lastIndexOf("零"), ms.lastIndexOf("零") + 1);
        }
        int end;
        if ((dotPoint = moneyString.indexOf(".")) != -1) { // 是小数的进入
            String fs = moneyString.substring(dotPoint + 1,
                    moneyString.length());
            if (fs.indexOf("00") == -1 || fs.indexOf("00") >= 2) {// 若前两位小数全为零，则跳过操作
                end = fs.length() > 2 ? 2 : fs.length(); // 仅保留两位小数
                fraction = new StringBuffer(fs.substring(0, end));
                for (int j = 0; j < fraction.length(); j++) {
                    fraction.replace(j, j + 1,
                            TRADITIONAL_CHINESE[fraction.charAt(j) - 48]); // 替换大写汉字
                }
                for (int i = fraction.length(); i > 0; i--) { // 插入中文标识
                    fraction.insert(i, cfPattern[i]);
                }
                fraction.insert(0, "元"); // 为整数部分添加标识
            } else {
                fraction = new StringBuffer("元整");
            }
        } else {
            fraction = new StringBuffer("元整");
        }
        ms.append(fraction); // 加入小数部分
        return ms.toString();
    }

    /**
     * 编码Html字符串
     *
     * @param html
     * @return
     */
    public static String encodeHtml(String html) {
        String retStr = html;
        retStr = retStr.replaceAll("&", "&amp;");
        retStr = retStr.replaceAll("<", "&lt;");
        retStr = retStr.replaceAll(">", "&gt;");
        retStr = retStr.replaceAll("/", "&#47;");
        // retStr = retStr.replaceAll("|", "&#124;");
        retStr = retStr.replaceAll("\"", "&quot;");
        return retStr;
    }

    /**
     * 编码字符串
     *
     * @param source
     * @param symbol
     * @param replace
     * @return
     * @author xie_pkuan
     * @version V2.0
     */
    public static String encode(String source, String symbol, String replace) {
        // $ &#36;
        source = source.replaceAll(symbol, replace);
        return source;
    }

    /**
     * 解码字符串
     *
     * @param source
     * @param symbol
     * @param replace
     * @return
     * @author xie_pkuan
     * @version V2.0
     */
    public static String decode(String source, String symbol, String replace) {
        // $ &#36;
        source = source.replaceAll(symbol, replace);
        return source;
    }

    /**
     * 解码Html字符串
     *
     * @param html
     * @return
     */
    public static String decodeHtml(String html) {
        String retStr = html;
        retStr = retStr.replaceAll("&amp;", "&");
        retStr = retStr.replaceAll("&lt;", "<");
        retStr = retStr.replaceAll("&gt;", ">");
        retStr = retStr.replaceAll("&#47;", "/");
        // retStr = retStr.replaceAll("|", "&#124;");
        retStr = retStr.replaceAll("&quot;", "\"");
        return retStr;
    }

    /**
     * 适配字符串，只支持${xxxx}类型的替换
     *
     * @param str
     *            原生字符串
     * @param parameters
     *            需要替换的参数及值
     * @return 适配后的字符串
     * @version V2.0
     */
    public static String adapterStr(String str, Map<String, String> parameters) {
        if (parameters != null) {
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                String encode = entry.getValue().replaceAll("\\$", "&#36;");
                str = str.replaceAll("\\$\\{" + entry.getKey() + "\\}", encode);
            }
        }
        str = str.replaceAll("&#36;", "\\$");
        return str;
    }

    /**
     * 混合两个字符串
     *
     * @param a
     * @param b
     * @return
     */
    public static String mixString(String a, String b) {
        final int aLength = a.length();
        final int bLength = b.length();
        final int min = Math.min(aLength, bLength);
        final StringBuilder sb = new StringBuilder(aLength + bLength);
        for (int i = 0; i < min; i++) {
            sb.append(a.charAt(i));
            sb.append(b.charAt(i));
        }
        if (aLength > bLength) {
            sb.append(a, bLength, aLength);
        } else if (aLength < bLength) {
            sb.append(b, aLength, bLength);
        }
        return sb.toString();
    }

    /**
     * 子字符串在源字符串中存在的个数
     *
     * @param sourceStr
     *            源字符串
     * @param findStr
     *            子字符串
     * @return
     * @version V2.0
     */
    public static int containsNum(String sourceStr, String findStr) {
        int num = 0;
        if (sourceStr.indexOf(findStr) == -1) {
            num = 0;
        } else {
            while (sourceStr.indexOf(findStr) != -1) {
                num++;
                sourceStr = sourceStr.substring(sourceStr.indexOf(findStr)
                        + findStr.length());
            }
        }
        return num;

    }

    /**
     * 获取随机字符串
     *
     * @param length
     *            字符串长度
     * @return
     * @version V2.0
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        return getRandomString(base, length);
    }

    /**
     * 随机从字符串抽取一部分字符作为新字符串
     *
     * @param source
     * @param length
     * @return
     * @version V2.0
     */
    public static String getRandomString(String source, int length) {
        String rdm = null;
        if (notNOE(source)) {
            Random random = new Random();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < length; i++) {
                int number = random.nextInt(source.length());
                sb.append(source.charAt(number));
            }
            rdm = sb.toString();
        }
        return rdm;
    }

    /**
     * 检查文件夹路径
     *
     * @param folderPath
     * @return
     * @version V1.0
     */
    public static String checkFolder(String folderPath) {
        if (!folderPath.endsWith(SEPARATOR_LINUX)
                && !folderPath.endsWith(SEPARATOR_WINDOW)) {
            folderPath += SEPARATOR_LINUX;
        }
        return folderPath;
    }

    /**
     * 检查文件夹分隔符，统一使用“/”
     *
     * @param fileName
     * @return
     * @version V1.0
     */
    public static String formatSeparator(String fileName) {
        fileName = fileName.replace(SEPARATOR_WINDOW,
                SEPARATOR_LINUX);
        return fileName;
    }

    /**
     * 大字段转换string
     *
     * @param buf
     * @return
     * @throws IOException
     */
    public static String formatBolb(byte[] buf) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(buf);// 数组元 转换成二进制流
        // InputStream insm = guidBookLibInfo.getBinaryStream(); //读取二进制io流
        byte[] fileContent = new byte[(int) buf.length];// 数组元
        bais.read(fileContent); // 读取数组元
        String filetxt = new String(fileContent);
        return filetxt;
    }

    public static boolean isEmpty(String s) {
        return  isNullOrEmpty(s);
    }
}