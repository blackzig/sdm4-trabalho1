package br.edu.ifspsaocarlos.sdm.trabalho;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class XadrezActivity extends AppCompatActivity implements View.OnClickListener {

    private Chronometer ch;
    private Button btn_start, btn_pause;

    private long millisegundos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xadrez);

        ch = (Chronometer) findViewById(R.id.chronometer_player1);
        millisegundos = 0;

        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);

        btn_pause = (Button) findViewById(R.id.btn_pause);
        btn_pause.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                ch.setBase(SystemClock.elapsedRealtime() - millisegundos);
                ch.start();
                break;
            case R.id.btn_pause:
                millisegundos = SystemClock.elapsedRealtime() - ch.getBase();
                ch.stop();
                break;
            default:
        }
    }
}
