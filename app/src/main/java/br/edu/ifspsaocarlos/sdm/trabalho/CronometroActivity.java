package br.edu.ifspsaocarlos.sdm.trabalho;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import br.edu.ifspsaocarlos.sdm.trabalho.time.Cronometro;

public class CronometroActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_time;
    private Button btn_start, btn_lap, btn_stop;
    private EditText et_listar_laps;
    private ScrollView sv_listar_lap;

    private Cronometro cronometro;
    private Thread threadCronometro;
    private Context context;

    private int laps = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

        context = this;

        tv_time = (TextView) findViewById(R.id.tv_time);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_lap = (Button) findViewById(R.id.btn_lap);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        et_listar_laps = (EditText) findViewById(R.id.et_listar_laps);
        sv_listar_lap = (ScrollView) findViewById(R.id.sv_listar_lap) ;

        btn_start.setOnClickListener(this);
        btn_lap.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:

                if(cronometro==null){
                    cronometro = new Cronometro(context);
                    threadCronometro = new Thread(cronometro);
                    threadCronometro.start();
                    cronometro.start();

                    laps = 1;
                    et_listar_laps.setText("");
                }

                break;
            case R.id.btn_lap:

                if(cronometro==null){
                    return;
                }
                et_listar_laps.append("LAP "+ String.valueOf(laps) + " " + String.valueOf(tv_time) + " \n");

                sv_listar_lap.post(new Runnable() {
                    @Override
                    public void run() {
                        sv_listar_lap.smoothScrollTo(0, et_listar_laps.getBottom());
                    }
                });

                break;
            case R.id.btn_stop:

                if(cronometro!=null){
                    cronometro.stop();
                    threadCronometro.interrupt();
                    threadCronometro = null;
                    cronometro = null;
                }

                break;
            default:
        }
    }

    public void updateTimerText(final String time){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_time.setText(time);
            }
        });

    }

}
