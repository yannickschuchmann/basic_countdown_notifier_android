package com.yannickschuchmann.bembelboyscountdown.app;

import android.text.format.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yannick on 26.06.15.
 */
public class CountDown {

    static public double getRemainingTime() {
        SimpleDateFormat dformat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = dformat.parse("21-08-2015");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date now = Calendar.getInstance().getTime();
        return (double) date.getTime() - now.getTime();
    }

    static public String getRemainingText() {
        int hours = 24;
        int minutes = 60;
        int seconds = 60;
        int milliseconds = 1000;
        int days = hours * minutes * seconds * milliseconds;

        int rDays = (int) Math.floor(getRemainingTime() / days);
        int rHours = (int) Math.floor((getRemainingTime() % days) / ((double) minutes * seconds * milliseconds));

        String countDownText;
        if (rDays <= 5) {
            countDownText = rDays + " Tage, " + rHours + " Stunden";
        } else {
            countDownText = rDays + " Tage";
        }

        return countDownText;
    }

}
