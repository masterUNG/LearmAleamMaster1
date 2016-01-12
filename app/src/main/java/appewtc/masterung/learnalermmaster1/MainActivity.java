package appewtc.masterung.learnalermmaster1;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

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

            }   // event
        });
    }   // buttonController

    private void bindWidget() {
        clickButton = (Button) findViewById(R.id.button);
        showTimeTextView = (TextView) findViewById(R.id.alarmprompt);
    }

}   // Main Class
