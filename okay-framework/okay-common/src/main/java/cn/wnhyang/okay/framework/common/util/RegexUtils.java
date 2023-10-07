package cn.wnhyang.okay.framework.common.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wnhyang
 * @date 2023/8/8
 **/
@UtilityClass
@Slf4j
public class RegexUtils {
    public static final String MOBILE = "^(?:(?:\\+|00)86)?1(?:(?:3[\\d])|(?:4[0,1,4-9])|(?:5[0-3,5-9])|(?:6[2,5-7])|(?:7[0-8])|(?:8[\\d])|(?:9[0-3,5-9]))\\d{8}$";
    public static final String EMAIL = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(?:\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$";

}
