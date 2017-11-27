package br.edu.ifspsaocarlos.sdm.trabalho;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class DiceActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_jogar;
    ImageView img_dice1, img_dice2;
    TextView sum_dice;
    Random r;

    int rolleNumber;
    int sumDices = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        btn_jogar = findViewById(R.id.btn_play_dice);
        btn_jogar.setOnClickListener(this);

        img_dice1 = findViewById(R.id.img_dice1);
        img_dice2 = findViewById(R.id.img_dice2);
        sum_dice = findViewById(R.id.sum_dice);

        r = new Random();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_play_dice:
                sumDices = 0;
                ImageView[] imv = {img_dice1,img_dice2};
                for(int i=0; i<=1; i++){
                    rolleNumber = r.nextInt(6)+1;

                    if(rolleNumber==1){
                        imv[i].setImageResource(R.drawable.dice1);
                        sumDices += 1;
                    }else if(rolleNumber==2){
                        imv[i].setImageResource(R.drawable.dice2);
                        sumDices += 2;
                    }else if(rolleNumber==3){
                        imv[i].setImageResource(R.drawable.dice3);
                        sumDices += 3;
                    }else if(rolleNumber==4){
                        imv[i].setImageResource(R.drawable.dice4);
                        sumDices += 4;
                    }else if(rolleNumber==5){
                        imv[i].setImageResource(R.drawable.dice5);
                        sumDices += 5;
                    }else if(rolleNumber==6){
                        imv[i].setImageResource(R.drawable.dice6);
                        sumDices += 6;
                    }
                }
                sum_dice.setText("Soma: " + String.valueOf(sumDices));
                break;
            default:
        }
    }
}
