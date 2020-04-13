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
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(Constants.BUTOANE_APASATE_NUMAR, butoaneApasateNumar);
    }



}

