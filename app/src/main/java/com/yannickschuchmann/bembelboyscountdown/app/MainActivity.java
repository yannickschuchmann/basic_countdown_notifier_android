package com.yannickschuchmann.bembelboyscountdown.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

    public AlarmManager alarmManager;
    Intent alarmIntent;
    PendingIntent pendingIntent;

    TextView countDownText;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countDownText = (TextView) findViewById(R.id.countDown);
        countDownText.setText(CountDown.getRemainingText());

        handler = new Handler();
        autoRefresh();

        setAlarm();
    }

    private void autoRefresh() {
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = getIntent();
                startActivity(intent);
                finish();
                autoRefresh();
            }
        }, 1000 * 60 * 10);
    }

    public void onPause() {
        super.onPause();
        handler.removeCallbacksAndMessages(null);
    }

    public void setAlarm() {
        Context context = this.getApplicationContext();

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmIntent = new Intent(context, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

        alarmManager.setRepeating(AlarmManager.RTC, getStartTime().getTimeInMillis(), getInterval(), pendingIntent);
    }

    static public int getInterval() {
        int days = 1;
        int hours = 24;
        int minutes = 60;
        int seconds = 60;
        int milliseconds = 1000;
        return days * hours * minutes * seconds * milliseconds;
    }

    static public Calendar getStartTime() {
        Calendar alarmStartTime = Calendar.getInstance();
        alarmStartTime.set(Calendar.HOUR_OF_DAY, 10);
        alarmStartTime.set(Calendar.MINUTE, 0);
        alarmStartTime.set(Calendar.SECOND, 0);
        return alarmStartTime;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
