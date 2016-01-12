package appewtc.masterung.learnalermmaster1;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private static final int RQS_1 = 1;

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

            Calendar curentCalendar = Calendar.getInstance();
            Calendar setCalendar = (Calendar) curentCalendar.clone();

            setCalendar.set(Calendar.HOUR_OF_DAY, i);
            setCalendar.set(Calendar.MINUTE, i1);
            setCalendar.set(Calendar.SECOND, 0);
            setCalendar.set(Calendar.MILLISECOND, 0);

            if (setCalendar.compareTo(curentCalendar) <= 0) {
                setCalendar.add(Calendar.DATE, 1);
            }
            setAlarm(setCalendar);

        }   // onTimeSet
    };

    private void setAlarm(Calendar setCalendar) {

        //Show Alarm
        showTimeTextView.setText("Time = " + setCalendar.getTime());

    }   // setAlarm

    private void bindWidget() {
        clickButton = (Button) findViewById(R.id.button);
        showTimeTextView = (TextView) findViewById(R.id.alarmprompt);
    }

}   // Main Class
