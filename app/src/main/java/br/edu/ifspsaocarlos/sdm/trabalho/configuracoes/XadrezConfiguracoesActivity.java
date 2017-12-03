package br.edu.ifspsaocarlos.sdm.trabalho.configuracoes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.UUID;

import br.edu.ifspsaocarlos.sdm.trabalho.MainActivity;
import br.edu.ifspsaocarlos.sdm.trabalho.R;
import br.edu.ifspsaocarlos.sdm.trabalho.XadrezActivity;

public class XadrezConfiguracoesActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_salvar_configuracoes;
    private EditText tempo_adicional, tempo_jogo;

    int thousandTimes, minutesForGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xadrez_configuracoes);

        btn_salvar_configuracoes = (Button) findViewById(R.id.btn_salvar_configuracoes);
        btn_salvar_configuracoes.setOnClickListener(this);

        tempo_adicional = (EditText) findViewById(R.id.tempo_adicional);
        tempo_jogo = findViewById(R.id.tempo_jogo);

        loadSettings();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_salvar_configuracoes:
                Boolean tempoValido = verificarSeTempoDeJogoPassaDeDuasHoras();
                if(tempoValido==true){
                    salvar();
                }else{
                    String text = "Adicione um tempo de jogo entre 2 e 120 minutos";
                    menssageSave(text);
                }
                break;
            default:
        }
    }

    private Boolean verificarSeTempoDeJogoPassaDeDuasHoras(){
        minutesForGame = Integer.parseInt(tempo_jogo.getText().toString());

        if(minutesForGame<=1 || minutesForGame>120){
            return false;
        }else{
            int ehPar = minutesForGame%2;
            if(ehPar!=0){
                minutesForGame+=1;
            }
            return true;
        }
    }


    public void salvar(){
        SharedPreferences prefs = getSharedPreferences("XadrezConfiguracoes",0);
        SharedPreferences.Editor editor = prefs.edit();
        thousandTimes = Integer.parseInt(tempo_adicional.getText().toString()) * 1000;
        editor.putString("bonus_segundos", String.valueOf(thousandTimes));
        editor.apply();

       // minutesForGame = Integer.parseInt(tempo_jogo.getText().toString());
        editor.putString("tempo_jogo", String.valueOf(minutesForGame));
        editor.apply();
        String text = "Configuração salva";
        menssageSave(text);
    }

    private void loadSettings(){
        try{
            SharedPreferences prefs = this.getSharedPreferences("XadrezConfiguracoes", Context.MODE_PRIVATE);
            String bonusTime = prefs.getString("bonus_segundos", null);
            int bonusTimeInt = Integer.parseInt(bonusTime)/1000;
            tempo_adicional.setText(String.valueOf(bonusTimeInt));

            String timeGame = prefs.getString("tempo_jogo", null);
            tempo_jogo.setText(timeGame);
        }catch (Exception e){
            SharedPreferences prefs = getSharedPreferences("XadrezConfiguracoes",0);
            SharedPreferences.Editor editor = prefs.edit();
            thousandTimes = 0 * 1000;
            editor.putString("bonus_segundos", String.valueOf(thousandTimes));
            editor.apply();

            editor.putString("tempo_jogo", String.valueOf(0));
            editor.apply();
        }
    }

    private void menssageSave(String mensagem){
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, mensagem, duration);
        toast.show();
        finish();
    }
}
