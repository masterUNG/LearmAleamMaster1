package appewtc.masterung.learnalermmaster1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by masterUNG on 1/13/16 AD.
 */
public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm Success", Toast.LENGTH_SHORT).show();
    }   // onReceive
}   // Main Class
