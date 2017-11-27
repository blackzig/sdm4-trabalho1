package br.edu.ifspsaocarlos.sdm.trabalho;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.Toast;

public class ChronometerReverseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometer_reverse);

        Chronometer mChronometer = (Chronometer) findViewById(R.id.chronometer);
        mChronometer.start();

        //IF you want to stop your chrono after X seconds or minutes.
        mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            public void onChronometerTick(Chronometer chronometer) {
                if (chronometer.getText().toString().equalsIgnoreCase("00:05")) { //When reaches 5 seconds.
                    //Define here what happens when the Chronometer reaches the time above.
                    chronometer.stop();
                    Toast.makeText(getBaseContext(),"Reached the end.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
