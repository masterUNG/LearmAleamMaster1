package appewtc.masterung.learnalermmaster1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private TimePicker objTimePicker;
    private Button clickButton;
    private TextView showTimeTextView;
    private TimePickerDialog objTimePickerDialog;
    private static final int RQS_1 = 0;
    private static final String tag = "master";
    private static final String tag2 = "master2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();

        //Button Controller
        buttonController();

    }    // Main Method

    private void buttonController() {
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeTextView.setText("");
                myTimePickerDialog(false);
            }   // event
        });
    }   // buttonController

    private void myTimePickerDialog(boolean is24r) {

        Calendar objCalendar = Calendar.getInstance();
        Log.d(tag, "currentDate ==> " + objCalendar.get(Calendar.DATE));
        Log.d(tag, "currentMonth ==> " + objCalendar.get(Calendar.MONTH));
        Log.d(tag, "currentYear ==> " + objCalendar.get(Calendar.YEAR));

        objTimePickerDialog = new TimePickerDialog(MainActivity.this,
                objOnTimeSetListener,
                objCalendar.get(Calendar.HOUR_OF_DAY),
                objCalendar.get(Calendar.MINUTE),
                is24r);
        objTimePickerDialog.setTitle("Please Set Time");
        objTimePickerDialog.show();

    }   // myTimePickerDialog

    TimePickerDialog.OnTimeSetListener objOnTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {

            Log.d(tag2, "setup to HOUR OF Day ==> " + i);
            Log.d(tag2, "setup to MINUTE ==> " + i1);

            Calendar curentCalendar = Calendar.getInstance();
            Calendar setCalendar = (Calendar) curentCalendar.clone();

            setCalendar.set(Calendar.HOUR_OF_DAY, i);
            setCalendar.set(Calendar.MINUTE, i1);
            setCalendar.set(Calendar.SECOND, 0);
            setCalendar.set(Calendar.MILLISECOND, 0);

            Log.d(tag2, "currentDate2 ==> " + curentCalendar.get(Calendar.DATE));
            Log.d(tag2, "currentMonth2 ==> " + curentCalendar.get(Calendar.MONTH));
            Log.d(tag2, "currentYear2 ==> " + curentCalendar.get(Calendar.YEAR));


            if (setCalendar.compareTo(curentCalendar) <= 0) {
                setCalendar.add(Calendar.DATE, 1);
            }

            Log.d(tag2, "setCalendar ==> " + setCalendar.getTime());
            setAlarm(setCalendar);

        }   // onTimeSet
    };

    private void setAlarm(Calendar setCalendar) {

        //Show Alarm
        showTimeTextView.setText("Time = " + setCalendar.getTime());

        //Intent
        Intent objIntent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent objPendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, objIntent, 0);
        AlarmManager objAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        objAlarmManager.set(AlarmManager.RTC_WAKEUP, setCalendar.getTimeInMillis(), objPendingIntent);

    }   // setAlarm

    private void bindWidget() {
        clickButton = (Button) findViewById(R.id.button);
        showTimeTextView = (TextView) findViewById(R.id.alarmprompt);
    }

}   // Main Class
