package br.edu.ifspsaocarlos.sdm.trabalho.configuracoes;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.UUID;

import br.edu.ifspsaocarlos.sdm.trabalho.R;

public class XadrezConfiguracoesActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_salvar_configuracoes;
    private EditText tempo_adicional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xadrez_configuracoes);

        btn_salvar_configuracoes = (Button) findViewById(R.id.btn_salvar_configuracoes);
        btn_salvar_configuracoes.setOnClickListener(this);

        tempo_adicional = (EditText) findViewById(R.id.tempo_adicional);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_salvar_configuracoes:
                salvar();
                break;
            default:
        }
    }

    public void salvar(){
        SharedPreferences prefs = getSharedPreferences("XadrezConfiguracoes",0);
        SharedPreferences.Editor editor = prefs.edit();
        int thousandTimes = Integer.parseInt(tempo_adicional.getText().toString()) * 1000;
        editor.putString("bonus_segundos", String.valueOf(thousandTimes));
        editor.apply();
    }
}
