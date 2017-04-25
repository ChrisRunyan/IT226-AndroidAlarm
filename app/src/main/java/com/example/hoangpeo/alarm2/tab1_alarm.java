package com.example.hoangpeo.alarm2;
import android.annotation.TargetApi;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.ToggleButton;

import java.util.Calendar;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.ALARM_SERVICE;
import static android.support.v7.appcompat.R.id.time;


/**
 * Created by hoang peo on 4/24/2017.
 */

public class tab1_alarm extends Fragment implements AdapterView.OnItemSelectedListener {
    AlarmManager alarmManager;
    private PendingIntent pending_intent;

    private TimePicker alarmTimePicker;
    private static tab1_alarm inst;
    private TextView alarmTextView;

    private AlarmReceiver alarm;
    private tab1_alarm context;
    Spinner spinner;
    int s_quote = 0;


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);


        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);


        this.context = this;

        //alarm = new AlarmReceiver();
        alarmTextView = (TextView) rootView.findViewById(R.id.alarmText);

        final Intent myIntent = new Intent(this.getActivity(), AlarmReceiver.class);

        // Get the alarm manager service
        alarmManager = (AlarmManager) this.getContext().getSystemService(ALARM_SERVICE);

        // set the alarm to the time that you picked
        final Calendar calendar = Calendar.getInstance();
        //calendar.add(Calendar.SECOND, 3);
        alarmTimePicker = (TimePicker) rootView.findViewById(R.id.alarmTimePicker);







        Button start_alarm= (Button) rootView.findViewById(R.id.start_alarm);


        start_alarm.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)

            @Override
            public void onClick(View v) {

                calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getHour());
                calendar.set(Calendar.MINUTE, alarmTimePicker.getMinute());

                final int hour = alarmTimePicker.getHour();
                final int minute = alarmTimePicker.getMinute();;

                String minute_string = String.valueOf(minute);
                String hour_string = String.valueOf(hour);

                if (minute < 10) {
                    minute_string = "0" + String.valueOf(minute);
                }

                if (hour > 12) {
                    hour_string = String.valueOf(hour - 12) ;
                }

                myIntent.putExtra("extra", "yes");
                myIntent.putExtra("quote id", String.valueOf(s_quote));


                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);

                setAlarmText("Alarm set to " + hour_string + ":" + minute_string);
            }

        });

        Button stop_alarm= (Button) rootView.findViewById(R.id.stop_alarm);
        stop_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                alarmManager.getNextAlarmClock();
                setAlarmText("Alarm canceled");


            }
        });

        return rootView;
    }
    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }




    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
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


    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("MyActivity", "on Destroy");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //Toast.makeText(parent.getContext(), "Spinner item 3!" + id, Toast.LENGTH_SHORT).show();
        s_quote = (int) id;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback

    }
}
