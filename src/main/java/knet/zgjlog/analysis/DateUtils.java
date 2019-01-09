package knet.zgjlog.analysis;

import com.eltima.components.ui.DatePicker;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Description:
 * @author: HU
 * @date: 2018/12/3 14:42
 */
public class DateUtils {
    public static DatePicker getDatePicker(Date date) {

        final DatePicker datePicker;
        // 格式
        String DefaultFormat = "yyyy-MM";
        //String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
        // 当前时间
        //Date date = new Date();
        // 字体
        Font font = new Font("Times New Roman", Font.BOLD, 14);

        Dimension dimension = new Dimension(80, 24);

        int[] hilightDays = {1, 3, 5, 7};

        int[] disabledDays = {4, 6, 5, 9};
        //构造方法（初始时间，时间显示格式，字体，控件大小）
        datePicker = new DatePicker(date, DefaultFormat, font, dimension);

        datePicker.setLocation(80, 83);//设置起始位置
        /*
        //也可用setBounds()直接设置大小与位置
        datepick.setBounds(137, 83, 177, 24);
        */
        // 设置一个月份中需要高亮显示的日子
        datePicker.setHightlightdays(hilightDays, Color.red);
        // 设置一个月份中不需要的日子，呈灰色显示
        datePicker.setDisableddays(disabledDays);
        // 设置国家
        datePicker.setLocale(Locale.CHINA);
        // 设置时钟面板可见
        datePicker.setTimePanleVisible(false);
        return datePicker;
    }

    public static Date cutDate(Date olddate, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(olddate);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }
}
