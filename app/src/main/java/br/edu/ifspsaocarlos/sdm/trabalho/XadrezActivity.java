package br.edu.ifspsaocarlos.sdm.trabalho;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import br.edu.ifspsaocarlos.sdm.trabalho.configuracoes.XadrezConfiguracoesActivity;

public class XadrezActivity extends AppCompatActivity implements View.OnClickListener {

    private Chronometer ch1, ch2;

    private long millisegundos_ch1, millisegundos_ch2;
    private int firstTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xadrez);

        ch1 = (Chronometer) findViewById(R.id.chronometer_player1);
        ch1.setOnClickListener(this);

        ch2 = (Chronometer) findViewById(R.id.chronometer_player2);
        ch2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chronometer_player1:
                player1PressChronometer();
                break;
            case R.id.chronometer_player2:
                player2PressChronometer();
                break;
            default:
        }
    }

    public void player1PressChronometer(){
        if(firstTime==0){
            millisegundos_ch2 = 0;
            ch2.setBase(SystemClock.elapsedRealtime() - millisegundos_ch2);
            ch2.start();
            //comecei pelo o cronômetro 1
            firstTime++;
        }else if(firstTime==1) {
            millisegundos_ch1 = SystemClock.elapsedRealtime() - ch1.getBase();
            ch1.stop();

            millisegundos_ch2 = 0;
            ch2.setBase(SystemClock.elapsedRealtime() - millisegundos_ch2);
            ch2.start();

            firstTime++;
        }else{
            millisegundos_ch1 = SystemClock.elapsedRealtime() - ch1.getBase();
            ch1.stop();

            ch2.setBase(SystemClock.elapsedRealtime() - millisegundos_ch2);
            ch2.start();

            firstTime=2;
        }
        ch1.setEnabled(false);
        ch2.setEnabled(true);
    }

    public void player2PressChronometer(){
        if(firstTime==0){
            millisegundos_ch1 = 0;
            ch1.setBase(SystemClock.elapsedRealtime() - millisegundos_ch1);
            ch1.start();
            //comecei pelo o cronômetro 2
            firstTime++;
        }else if(firstTime==1) {
            millisegundos_ch2 = SystemClock.elapsedRealtime() - ch2.getBase();
            ch2.stop();

            millisegundos_ch1 = 0;
            ch1.setBase(SystemClock.elapsedRealtime() - millisegundos_ch1);
            ch1.start();

            firstTime++;
        }else{
            millisegundos_ch2 = SystemClock.elapsedRealtime() - ch2.getBase();
            ch2.stop();

            ch1.setBase(SystemClock.elapsedRealtime() - millisegundos_ch1);
            ch1.start();

            firstTime=2;
        }
        ch1.setEnabled(true);
        ch2.setEnabled(false);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.configuracoes:
                startActivity(new Intent(XadrezActivity.this, XadrezConfiguracoesActivity.class));
                break;
            default:
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

}
