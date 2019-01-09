package knet.zgjlog.analysis;

import com.eltima.components.ui.DatePicker;

import javax.swing.*;
import java.util.Date;

/**
 * @Description:
 * @author: HU
 * @date: 2018/12/3 9:08
 */
public class DatePickerComponent {
    private static DatePicker datePicker;

    public static DatePicker getDatePicker(Date date) {

        datePicker =DateUtils.getDatePicker(date);
        return datePicker;
    }
}
