package com.hlh.bootfilter.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtils {
    public static String forTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
