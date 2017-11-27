package br.edu.ifspsaocarlos.sdm.trabalho;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import br.edu.ifspsaocarlos.sdm.trabalho.configuracoes.XadrezConfiguracoesActivity;
import br.edu.ifspsaocarlos.sdm.trabalho.time.MinutesToMinutesToText;

public class XadrezActivity extends AppCompatActivity implements View.OnClickListener {

    private Chronometer ch1, ch2, ch1Litle, ch2Litle;

    private long millisegundos_ch1=0, millisegundos_ch2=0;
    private int firstTimeLitleChronometer1 = 0;
    private int firstTimeLitleChronometer2 = 0;
    private int bonusTimeInt = 0;
    private int pressChronometer = 0;
    private int timeGameForPlayer = 0;
    private String setTimeLitleChronometer1 = "00:01";
    private String setTimeLitleChronometer2 = "00:01";
    private String timeForPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xadrez);

        loadSettings();

        ch1 = (Chronometer) findViewById(R.id.chronometer_player1);
        ch1.setOnClickListener(this);

        ch2 = (Chronometer) findViewById(R.id.chronometer_player2);
        ch2.setOnClickListener(this);

        ch1.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            public void onChronometerTick(Chronometer chronometer) {
                if (chronometer.getText().toString().equalsIgnoreCase(timeForPlayer)) {
                    //Define here what happens when the Chronometer reaches the time above.
                    chronometer.stop();
                    Toast.makeText(getBaseContext(),"Terminou o tempo do jogador com as peças brancas.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        ch2.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            public void onChronometerTick(Chronometer chronometer) {
                if (chronometer.getText().toString().equalsIgnoreCase(timeForPlayer)) {
                    //Define here what happens when the Chronometer reaches the time above.
                    chronometer.stop();
                    Toast.makeText(getBaseContext(),"Terminou o tempo do jogador com as peças pretas.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        ch2Litle = (Chronometer) findViewById(R.id.chronometer_player2_litle);
        ch2Litle.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener(){
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if( chronometer.getText().toString().equalsIgnoreCase(setTimeLitleChronometer2)) {
                    ch2Litle.stop();

                    if(firstTimeLitleChronometer2==0){
                        ch2.setBase(SystemClock.elapsedRealtime());
                    }else if(firstTimeLitleChronometer2==1){
                        millisegundos_ch2 = SystemClock.elapsedRealtime() - ch2.getBase();
                        ch2.setBase(SystemClock.elapsedRealtime() - millisegundos_ch2);
                    }else if(firstTimeLitleChronometer2 == 2){
                        ch2.setBase(SystemClock.elapsedRealtime() - millisegundos_ch2);
                    }
                    ch2.start();
                    firstTimeLitleChronometer2 = 1;
                }
                setTimeLitleChronometer2 = "00:00";
            }
        });

        ch1Litle = (Chronometer) findViewById(R.id.chronometer_player1_litle);
        ch1Litle.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener(){
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if( chronometer.getText().toString().equalsIgnoreCase(setTimeLitleChronometer1)) {
                    ch1Litle.stop();

                    if(firstTimeLitleChronometer1==0){
                        ch1.setBase(SystemClock.elapsedRealtime());
                    }else if(firstTimeLitleChronometer1==1){
                        millisegundos_ch1 = SystemClock.elapsedRealtime() - ch1.getBase();
                        ch1.setBase(SystemClock.elapsedRealtime() - millisegundos_ch1);
                    }else if(firstTimeLitleChronometer1 == 2){
                        ch1.setBase(SystemClock.elapsedRealtime() - millisegundos_ch1);
                    }
                    ch1.start();
                    firstTimeLitleChronometer1 = 1;
                }
                setTimeLitleChronometer1 = "00:00";
            }
        });
    }

    public void stopLilteChronometer2(){
        ch2Litle.stop();
        ch2Litle.setBase(SystemClock.elapsedRealtime());
        setTimeLitleChronometer2 = "00:01";
    }

    public void stopLilteChronometer1(){
        ch1Litle.stop();
        ch1Litle.setBase(SystemClock.elapsedRealtime());
        setTimeLitleChronometer1 = "00:01";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chronometer_player1:
                stopChronometer1();
                bonusTimeLitleChronometer2();
                break;
            case R.id.chronometer_player2:
                stopChronometer2();
                bonusTimeLitleChronometer1();
                break;
            default:
        }
    }

    public void loadSettings(){
        try{
            SharedPreferences prefs = this.getSharedPreferences("XadrezConfiguracoes", Context.MODE_PRIVATE);
            String bonusTime = prefs.getString("bonus_segundos", null);
            bonusTimeInt = Integer.parseInt(bonusTime);

            String timeGame = prefs.getString("tempo_jogo", null);
            timeGameForPlayer = Integer.parseInt(timeGame)/2;

            MinutesToMinutesToText m = new MinutesToMinutesToText();
            timeForPlayer = m.minutesToMinutesString(timeGameForPlayer);

        }catch (Exception e){
            SharedPreferences prefs = getSharedPreferences("XadrezConfiguracoes",0);
            SharedPreferences.Editor editor = prefs.edit();
            int thousandTimes = 0 * 1000;
            editor.putString("bonus_segundos", String.valueOf(thousandTimes));
            editor.apply();
        }
    }

    public void bonusTimeLitleChronometer2(){
        //pressionou o cronômetro 1
        ch1.setEnabled(false);
        ch2.setEnabled(true);
        if(bonusTimeInt!=0){
            ch2Litle.setVisibility(View.VISIBLE);
            ch2Litle.setBase(SystemClock.elapsedRealtime()+ bonusTimeInt);
            ch2Litle.start();
        }else{
            //vai executar aqui quando não houver tempo bônus
            ch2Litle.setVisibility(View.INVISIBLE);
            ch1.stop();

            if(pressChronometer==0){
                ch1.setBase(SystemClock.elapsedRealtime());
                ch2.setBase(SystemClock.elapsedRealtime());
                pressChronometer = 1;
            }else if(pressChronometer==1){
                ch2.setBase(SystemClock.elapsedRealtime());
                millisegundos_ch1 = SystemClock.elapsedRealtime() - ch1.getBase();
                ch1.setBase(SystemClock.elapsedRealtime() - millisegundos_ch1);
                pressChronometer = 2;
            }else if(pressChronometer == 2){
                millisegundos_ch1 = SystemClock.elapsedRealtime() - ch1.getBase();
                ch2.setBase(SystemClock.elapsedRealtime() - millisegundos_ch2);
            }
            ch2.start();
        }
    }

    public void bonusTimeLitleChronometer1(){
        //pressionou o cronômetro 2
        ch1.setEnabled(true);
        ch2.setEnabled(false);
        if(bonusTimeInt!=0){
            ch1Litle.setVisibility(View.VISIBLE);
            ch1Litle.setBase(SystemClock.elapsedRealtime()+ bonusTimeInt);
            ch1Litle.start();
        }else{
            //vai executar aqui quando não houver tempo bônus
            ch1Litle.setVisibility(View.INVISIBLE);
            ch2.stop();

            if(pressChronometer==0){
                ch2.setBase(SystemClock.elapsedRealtime());
                ch1.setBase(SystemClock.elapsedRealtime());
                pressChronometer = 1;
            }else if(pressChronometer==1){
                ch1.setBase(SystemClock.elapsedRealtime());
                millisegundos_ch2 = SystemClock.elapsedRealtime() - ch2.getBase();
                ch2.setBase(SystemClock.elapsedRealtime() - millisegundos_ch2);
                pressChronometer=2;
            }else if(pressChronometer == 2){
                millisegundos_ch2 = SystemClock.elapsedRealtime() - ch2.getBase();
                ch1.setBase(SystemClock.elapsedRealtime() - millisegundos_ch1);
            }
            ch1.start();
        }
    }

    public void stopChronometer1(){
        if(bonusTimeInt!=0){
            stopLilteChronometer1();
            ch1.stop();
            firstTimeLitleChronometer2 = 2;
        }
    }

    public void stopChronometer2(){
        if(bonusTimeInt!=0){
            stopLilteChronometer2();
            ch2.stop();
            firstTimeLitleChronometer1 = 2;
        }
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
