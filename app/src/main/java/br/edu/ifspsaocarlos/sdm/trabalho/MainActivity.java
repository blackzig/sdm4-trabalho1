package br.edu.ifspsaocarlos.sdm.trabalho;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_cronometro, btn_cronometro_rev, btn_xadrez, btn_dice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_cronometro = (Button) findViewById(R.id.btn_cronometro);
        btn_cronometro.setOnClickListener(this);

       // btn_cronometro_rev = (Button) findViewById(R.id.btn_cronometroRev);
       // btn_cronometro_rev.setOnClickListener(this);

        btn_xadrez = (Button) findViewById(R.id.btn_xadrez);
        btn_xadrez.setOnClickListener(this);

        btn_dice = findViewById(R.id.btn_dice);
        btn_dice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cronometro:
                startActivity(new Intent(MainActivity.this, CronometroActivity.class));
                break;
         //   case R.id.btn_cronometroRev:
           //     startActivity(new Intent(MainActivity.this, ChronometerReverseActivity.class));
             //   break;
            case R.id.btn_xadrez:
                startActivity(new Intent(MainActivity.this, XadrezActivity.class));
                break;
            case R.id.btn_dice:
                startActivity(new Intent(MainActivity.this, DiceActivity.class));
                break;
            default:
        }
    }
}
