package br.edu.ifspsaocarlos.sdm.trabalho;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_cronometro, btn_xadrez;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_cronometro = (Button) findViewById(R.id.btn_cronometro);
        btn_cronometro.setOnClickListener(this);

        btn_xadrez = (Button) findViewById(R.id.btn_xadrez);
        btn_xadrez.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cronometro:
                startActivity(new Intent(MainActivity.this, CronometroActivity.class));
                break;
            case R.id.btn_xadrez:
                startActivity(new Intent(MainActivity.this, XadrezActivity.class));
                break;
            default:
        }
    }
}
