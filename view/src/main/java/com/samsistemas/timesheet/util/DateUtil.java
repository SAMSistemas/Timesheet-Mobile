package com.samsistemas.timesheet.util;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.R;

import java.util.Calendar;
import java.util.Date;

/**
 * @author jonatan.salas
 */
public final class DateUtil {
    private static final String SPACE = " ";

    private DateUtil() { }

    public static String formatDate(@NonNull Context context, @NonNull Date dateToFormat) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateToFormat);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        final String of = context.getString(R.string.of);

        return day + SPACE + of + SPACE + formatMonth(context, month) + SPACE + of + SPACE + year;
    }

    private static String formatMonth(@NonNull Context context, int month) {
        switch (month) {
            case Calendar.JANUARY:
                return context.getString(R.string.january);
            case Calendar.FEBRUARY:
                return context.getString(R.string.february);
            case Calendar.MARCH:
                return context.getString(R.string.march);
            case Calendar.APRIL:
                return context.getString(R.string.april);
            case Calendar.MAY:
                return context.getString(R.string.may);
            case Calendar.JUNE:
                return context.getString(R.string.june);
            case Calendar.JULY:
                return context.getString(R.string.july);
            case Calendar.AUGUST:
                return context.getString(R.string.august);
            case Calendar.SEPTEMBER:
                return context.getString(R.string.september);
            case Calendar.OCTOBER:
                return context.getString(R.string.october);
            case Calendar.NOVEMBER:
                return context.getString(R.string.november);
            case Calendar.DECEMBER:
                return context.getString(R.string.december);
            default: return null;
        }
    }
}
