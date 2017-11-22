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

import br.edu.ifspsaocarlos.sdm.trabalho.configuracoes.XadrezConfiguracoesActivity;

public class XadrezActivity extends AppCompatActivity implements View.OnClickListener {

    private Chronometer ch1, ch2, ch1Litle, ch2Litle;

    private long millisegundos_ch1, millisegundos_ch2, litle_millisegundos_ch1, litle_millisegundos_ch2;
   // private int firstTime = 0;
    private int firstTimeLitleChronometer1 = 0;
    private int firstTimeLitleChronometer2 = 0;
    private int bonusTimeInt = 0;
    private int usedTimeBonusChronometer1 = 0;
    private int usedTimeBonusChronometer2 = 0;
    private String setTimeLitleChronometer1 = "00:01";
    private String setTimeLitleChronometer2 = "00:01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xadrez);

        ch1 = (Chronometer) findViewById(R.id.chronometer_player1);
        ch1.setOnClickListener(this);

        ch2 = (Chronometer) findViewById(R.id.chronometer_player2);
        ch2.setOnClickListener(this);

        ch2Litle = (Chronometer) findViewById(R.id.chronometer_player2_litle);
        ch2Litle.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener(){
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if( chronometer.getText().toString().equalsIgnoreCase(setTimeLitleChronometer2)) {
                    ch2Litle.stop();
                    Log.i("usedTimeBonusChro2 C2: ", String.valueOf(usedTimeBonusChronometer2));
                    if(usedTimeBonusChronometer2==1){
                        ch2.setBase(SystemClock.elapsedRealtime() - millisegundos_ch2);
                        ch2.start();
                       // firstTime--;
                    }else{
                      //  player1PressChronometer();
                        //firstTime++;
                    }
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
                    Log.i("usedTimeBonusChro1 C1: ", String.valueOf(usedTimeBonusChronometer1));
                    if(usedTimeBonusChronometer1==1){
                        ch1.setBase(SystemClock.elapsedRealtime() - millisegundos_ch1);
                        ch1.start();
                        //firstTime--;
                    }else{
                        //player2PressChronometer();
                       // firstTime++;
                    }
                }
                setTimeLitleChronometer1 = "00:00";
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chronometer_player1:
                //Log.i("firstTime C1: ", String.valueOf(firstTime));
                stopChronometer1();
                haBonusTime();
                bonusTimeLitleChronometer2();
                break;
            case R.id.chronometer_player2:
              //  Log.i("firstTime C2: ", String.valueOf(firstTime));
                stopChronometer2();
                haBonusTime();
                bonusTimeLitleChronometer1();
                break;
            default:
        }
    }

    public void haBonusTime(){
        SharedPreferences prefs = this.getSharedPreferences("XadrezConfiguracoes", Context.MODE_PRIVATE);
        String bonusTime = prefs.getString("bonus_segundos", null);
        bonusTimeInt = Integer.parseInt(bonusTime);
    }

    public void bonusTimeLitleChronometer2(){
        ch1.setEnabled(false);
        ch2.setEnabled(true);
        if(usedTimeBonusChronometer2 == 1){
            //aqui vai....
        }else{
          //  firstTime++;
            if(bonusTimeInt!=0){
                ch2Litle.setVisibility(View.VISIBLE);
                ch2Litle.setBase(SystemClock.elapsedRealtime()+ bonusTimeInt);
                ch2Litle.start();
            }else{
                ch2Litle.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void bonusTimeLitleChronometer1(){
        ch1.setEnabled(true);
        ch2.setEnabled(false);
        Log.i("usedTimeBonusChronome1",String.valueOf(usedTimeBonusChronometer1));
        if(usedTimeBonusChronometer1 == 1){
            //ch2Litle.start();
        }else{
            //firstTime++;
            if(bonusTimeInt!=0){
                ch1Litle.setVisibility(View.VISIBLE);
                ch1Litle.setBase(SystemClock.elapsedRealtime()+ bonusTimeInt);
                ch1Litle.start();
            }else{
                ch1Litle.setVisibility(View.INVISIBLE);
            }
        }
    }

    /*
    public void player1PressChronometer(){
        Log.i("player1Press ", String.valueOf(firstTime));
        if(firstTime==0){
            millisegundos_ch2 = 0;
            ch2.setBase(SystemClock.elapsedRealtime() - millisegundos_ch2);
            ch2.start();
//            firstTime++;
        }else if(firstTime==1) {
            ch2.setBase(SystemClock.elapsedRealtime() - millisegundos_ch2);
            ch2.start();
  //          firstTime++;
        }else{
          //  firstTime=2;
        }
    }

    public void player2PressChronometer(){
        Log.i("player2Press ", String.valueOf(firstTime));
        if(firstTime==0){
            millisegundos_ch1 = 0;
            ch1.setBase(SystemClock.elapsedRealtime() - millisegundos_ch1);
            ch1.start();
    //        firstTime++;
        }else if(firstTime==1) {
            ch1.setBase(SystemClock.elapsedRealtime() - millisegundos_ch1);
            ch1.start();
      //      firstTime++;
        }else{
        //    firstTime=2;
        }
    }
*/
    public void stopChronometer1(){
        millisegundos_ch1 = SystemClock.elapsedRealtime() - ch1.getBase();
        ch1.stop();

        if(firstTimeLitleChronometer1>0){
            litle_millisegundos_ch1 = SystemClock.elapsedRealtime() - ch1Litle.getBase();
        }
        firstTimeLitleChronometer1 = 1;

        if(litle_millisegundos_ch1<=0){
            ch1Litle.stop();
            ch1Litle.setBase(SystemClock.elapsedRealtime());
            usedTimeBonusChronometer1 = 0;
        }else{
            usedTimeBonusChronometer1 = 1;
        }
    }

    public void stopChronometer2(){
        millisegundos_ch2 = SystemClock.elapsedRealtime() - ch2.getBase();
        ch2.stop();

        if(firstTimeLitleChronometer2>0){
            litle_millisegundos_ch2 = SystemClock.elapsedRealtime() - ch2Litle.getBase();
        }
        firstTimeLitleChronometer2 = 1;

        if(litle_millisegundos_ch2<=0){
            ch2Litle.stop();
            ch2Litle.setBase(SystemClock.elapsedRealtime());
            usedTimeBonusChronometer2 = 0;
        }else{
            usedTimeBonusChronometer2 = 1;
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
