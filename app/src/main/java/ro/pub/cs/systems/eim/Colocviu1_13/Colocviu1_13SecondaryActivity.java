package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;



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




public class Colocviu1_13SecondaryActivity extends AppCompatActivity {


    Button register;
    Button cancel;
    TextView cardinalsText;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements  View.OnClickListener {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.register_button:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.cancel_button:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_secondary);

        cardinalsText = findViewById(R.id.cardinals_number_edit_text);


        Intent intent = getIntent();
        if (intent != null) {
            String values = intent.getStringExtra(Constants.CARDINALS);


            if (values != null) {
                cardinalsText.setText(values);
            } else {
                Toast.makeText(this, "EROARE", Toast.LENGTH_LONG).show();
            }
        }


        cancel = findViewById(R.id.cancel_button);
        cancel.setOnClickListener(buttonClickListener);
        register = findViewById(R.id.register_button);
        register.setOnClickListener(buttonClickListener);
    }
}
