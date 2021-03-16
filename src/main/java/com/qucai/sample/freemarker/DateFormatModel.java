package com.qucai.sample.freemarker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.lang3.time.DateFormatUtils;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class DateFormatModel implements TemplateMethodModelEx {

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        if (arguments.size() != 1) {
            throw new TemplateModelException("Wrong arguments");
        }
        String dateArgu = String.valueOf(arguments.get(0));
        Date currentDate = new Date();
        // long型13位格式
        String regDate1 = "^\\d{13}$";
        Pattern p1 = Pattern.compile(regDate1);
        // 直接返回Date对象时的格式
        String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
        SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.US);
        
        Date newDate = null;
        if (p1.matcher(dateArgu).matches()) {
            newDate = new Date(Long.valueOf(dateArgu));
        } else {
            try {
                newDate = df.parse(dateArgu);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        long millisecond = currentDate.getTime() - newDate.getTime(); // 时间差的毫秒数
        long second = millisecond / 1000;
        long minute = second / 60;
        long hour = minute / 60;
        long day = hour / 24;
        String result = "";
        if (day >= 1) {
            if (day >= 8) {
                result = DateFormatUtils.format(newDate, "MM-dd");
            } else {
                result = Math.round(day) + "天前";
            }
        } else if (hour >= 1) {
            result = Math.round(hour) + "小时前";
        } else {
            if (minute > 1) {
                result = Math.round(minute) + "分钟前";
            } else {
                result = "1分钟前";
            }
        }
        return result;
    }

}
