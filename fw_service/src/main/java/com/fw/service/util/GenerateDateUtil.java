package com.fw.service.util;

import com.fw.utils.CheckUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author xkliu
 * @date 2020/10/28
 */
public class GenerateDateUtil {

    /**
     * 根据初始时间,提前通知时间,生成计划最后保养时间
     *
     * @param startTime
     * @param week
     * @param month
     * @return
     */
    public static String generateLastTime(String startTime, Integer week, Integer month, Integer day) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cd = Calendar.getInstance();//获取一个Calendar对象
            cd.setTime(sdf.parse(startTime));//设置calendar日期
            if (!CheckUtils.isNull(month)) {
                cd.add(Calendar.MONTH, month);//增加月
            }
            if (!CheckUtils.isNull(week)) {
                cd.add(Calendar.DATE, -week);//减去周
            }
            if (!CheckUtils.isNull(day)) {
                cd.add(Calendar.DATE, day);//增加天
            }
            return sdf.format(cd.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 当前时间减去创建时间,计算是否延期
     *
     * @param nowDate
     * @param createTime
     * @return
     */
    public static Long differHour(Date nowDate, String createTime) {
        // 格式化时间
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 按以上格式 将当前时间转换成字符串
        String nowtime = d.format(nowDate);
        try {
            // 当前时间减去测试时间
            // 这个的除以1000得到秒，相应的60000得到分，3600000得到小时
            Long result = (d.parse(nowtime).getTime() - d.parse(createTime).getTime()) / 3600000;
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 任务生成时间 + 24小时 + 延期天数时
     *
     * @param createTime
     * @param hour
     * @param day
     * @return
     */
    public static String calDelayTime(String createTime, Integer hour, Integer day) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cd = Calendar.getInstance();//获取一个Calendar对象
            cd.setTime(sdf.parse(createTime));//设置calendar日期
            if (!CheckUtils.isNull(hour)) {
                cd.add(Calendar.HOUR, hour);// 24小时制
            }
            if (!CheckUtils.isNull(day)) {
                cd.add(Calendar.DATE, day);
            }
            return sdf.format(cd.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 当前时间比较延期时间大小
     *
     * @param date
     * @param endTime
     * @return
     */
    public static Integer compareToTime(Date date, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String beginTime = sdf.format(date);
        Integer result = beginTime.compareTo(endTime);
        return result;
    }

    /**
     * 两个时间相减,返回天
     *
     * @param beginDateStr
     * @param endDateStr
     * @return
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate;
        Date endDate;
        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
            day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }

    /**
     * 时间的时间差 HH:mm:ss格式相减
     * @param startTime
     * @param endTime
     * @return
     */
    public static Long getMinutesSub(String startTime, String endTime) {
        long m = 0L;
        try {
            String[] start = StringUtils.split(startTime, " ");
            String[] end = StringUtils.split(endTime, " ");
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            Date d1 = df.parse(start[1]);
            Date d2 = df.parse(end[1]);
            //这样得到的差值是毫秒级别
            long diff = d1.getTime() - d2.getTime();
            m = diff / 1000 / 60;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return m;
    }


}




