package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;


public class Colocviu1_13MainActivity extends AppCompatActivity {


    Button north;
    Button east;
    Button west;
    Button south;
    Button navigate;

    TextView buttonsPressed;


    private int butoaneApasateNumar = 0;

    final public static int SERVICE_STOPPED = 0;
    final public static int SERVICE_STARTED = 1;
    private int serviceStatus = SERVICE_STOPPED;

    private IntentFilter intentFilter = new IntentFilter();


    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(Constants.BROADCAST_RECEIVER_TAG, intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA));
        }
    }


    private NavigateToSecondaryActivityClickListener navigateToSecondaryActivityClickListener = new NavigateToSecondaryActivityClickListener();
    private class NavigateToSecondaryActivityClickListener implements  View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), Colocviu1_13SecondaryActivity.class);
            intent.putExtra(Constants.CARDINALS, buttonsPressed.getText().toString());
            startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
        }
    }


    private CardinalsButtonClickListener cardinalsButtonClickListener = new CardinalsButtonClickListener();

    private class CardinalsButtonClickListener implements  View.OnClickListener {
        @Override
        public void onClick(View view) {
            String currentText = buttonsPressed.getText().toString();

            switch (view.getId()) {
                case R.id.north_button:
                    currentText += " North,";
                    buttonsPressed.setText(currentText);
                    butoaneApasateNumar++;
                    break;
                case R.id.east_button:
                    currentText += " East,";
                    buttonsPressed.setText(currentText);
                    butoaneApasateNumar++;
                    break;
                case R.id.west_button:
                    currentText += " West,";
                    buttonsPressed.setText(currentText);
                    butoaneApasateNumar++;
                    break;
                case R.id.south_button:
                    currentText += " South,";
                    buttonsPressed.setText(currentText);
                    butoaneApasateNumar++;
                    break;
            }
            if (butoaneApasateNumar >=4
                    && serviceStatus == SERVICE_STOPPED) {
                Intent intent = new Intent(getApplicationContext(), Colocviu1_13Service.class);
                intent.putExtra(Constants.CARDINAL_LIST, buttonsPressed.getText().toString());
                getApplicationContext().startService(intent);
                serviceStatus = SERVICE_STARTED;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_main);

        north = findViewById(R.id.north_button);
        north.setOnClickListener(cardinalsButtonClickListener);
        east = findViewById(R.id.east_button);
        east.setOnClickListener(cardinalsButtonClickListener);
        west = findViewById(R.id.west_button);
        west.setOnClickListener(cardinalsButtonClickListener);
        south = findViewById(R.id.south_button);
        south.setOnClickListener(cardinalsButtonClickListener);
        navigate = findViewById(R.id.navigate_button);
        navigate.setOnClickListener(navigateToSecondaryActivityClickListener);
        buttonsPressed = findViewById(R.id.button_pressed_edit_text);



        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.BUTOANE_APASATE_NUMAR)) {
                butoaneApasateNumar =savedInstanceState.getInt(Constants.BUTOANE_APASATE_NUMAR);
                Toast.makeText( this, "Numar butoane apasate: " + butoaneApasateNumar, Toast.LENGTH_LONG ).show();

            } else {
                butoaneApasateNumar = 0;
            }
        } else {
            butoaneApasateNumar = 0;
        }



        for (int index = 0; index < Constants.actionTypes.length; index++) {
            intentFilter.addAction(Constants.actionTypes[index]);
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(Constants.BUTOANE_APASATE_NUMAR, butoaneApasateNumar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, Colocviu1_13Service.class);
        stopService(intent);
        super.onDestroy();
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }


}

