package com.truepaas.log.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by mas on 2016/11/7.
 */
public class UtilMethod {
    public static int getStartNum(int num1,int num2){
        return num1;
    }

    public static int getEndNum(int num1,int num2){
        return num2-num1+1;
    }

    private static String valueOfString(String str, int len) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len - str.length(); i++) {
            sb.append("0");
        }
        return (sb.length() == 0) ? (str) : (sb.toString() + str);
    }

    private static String getDateFormat(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    private static Date getDateFormat(String time){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return df.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getTimeString(Calendar calendar) {
        StringBuffer sb = new StringBuffer();
        sb.append(String.valueOf(calendar.get(Calendar.YEAR)))
                .append(valueOfString(String.valueOf(calendar.get(Calendar.MONTH) + 1),2))
                .append(valueOfString(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)),2))
                .append(valueOfString(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)),2))
                .append(valueOfString(String.valueOf(calendar.get(Calendar.MINUTE)),2))
                .append(valueOfString(String.valueOf(calendar.get(Calendar.SECOND)),2))
                .append(valueOfString(String.valueOf(calendar.get(Calendar.MILLISECOND)),3));
        return sb.toString();
    }

    private static String getTimeString(String time){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(getDateFormat(time));
        return getTimeString(calendar);
    }

    public static String getTimeString(){
        Calendar calendar = new GregorianCalendar();
        return getTimeString(calendar);
    }
}
