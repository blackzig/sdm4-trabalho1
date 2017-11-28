package br.edu.ifspsaocarlos.sdm.trabalho;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class DiceActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_play_dice, btn_play_coin;
    ImageView img_dice1, img_dice2, img_dice3;
    ImageView img_coin1, img_coin2, img_coin3;
    TextView sum_dice, who_win_heads_or_tails;
    Spinner dices, coins;
    Random r;

    int rolleNumber;
    int sumDices = 0;
    int heads=0, tails=0;
    int numbersDicesCount = 0, numbersCoinsCount = 0;
    String numbersDices, numbersCoins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        btn_play_dice = findViewById(R.id.btn_play_dice);
        btn_play_dice.setOnClickListener(this);

        btn_play_coin = findViewById(R.id.btn_play_coin);
        btn_play_coin.setOnClickListener(this);

        img_coin1 = findViewById(R.id.img_coin1);
        img_coin2 = findViewById(R.id.img_coin2);
        img_coin3 = findViewById(R.id.img_coin3);
        who_win_heads_or_tails = findViewById(R.id.who_win_heads_or_tails);

        img_dice1 = findViewById(R.id.img_dice1);
        img_dice2 = findViewById(R.id.img_dice2);
        img_dice3 = findViewById(R.id.img_dice3);
        sum_dice = findViewById(R.id.sum_dice);

        dices = findViewById(R.id.spinner_dices);
        coins = findViewById(R.id.spinner_coins);

        comboboxDices();
        comboboxCoins();

        r = new Random();
    }

    public void comboboxCoins(){
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.how_many_coins, android.R.layout.simple_spinner_item);
        coins.setAdapter(arrayAdapter);

        AdapterView.OnItemSelectedListener choiced = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                numbersCoins = coins.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), numbersCoins +" moedas(s) escolhida(s).", Toast.LENGTH_SHORT).show();

                if(numbersCoins.equals("1")){
                    img_coin1.setVisibility(View.VISIBLE);
                    img_coin2.setVisibility(View.INVISIBLE);
                    img_coin3.setVisibility(View.INVISIBLE);
                    numbersCoinsCount = 0;
                }
                else if(numbersCoins.equals("2")){
                    img_coin1.setVisibility(View.VISIBLE);
                    img_coin2.setVisibility(View.VISIBLE);
                    img_coin3.setVisibility(View.INVISIBLE);
                    numbersCoinsCount = 1;
                }
                else if(numbersCoins.equals("3")){
                    img_coin1.setVisibility(View.VISIBLE);
                    img_coin2.setVisibility(View.VISIBLE);
                    img_coin3.setVisibility(View.VISIBLE);
                    numbersCoinsCount = 2;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };

        coins.setOnItemSelectedListener(choiced);
    }

    public void comboboxDices(){
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.how_many_dices, android.R.layout.simple_spinner_item);
        dices.setAdapter(arrayAdapter);

        AdapterView.OnItemSelectedListener choiced = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                numbersDices = dices.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), numbersDices +" dado(s) escolhido(s).", Toast.LENGTH_SHORT).show();

                if(numbersDices.equals("1")){
                    img_dice2.setVisibility(View.INVISIBLE);
                    img_dice3.setVisibility(View.INVISIBLE);
                    numbersDicesCount = 0;
                }
                else if(numbersDices.equals("2")){
                    img_dice2.setVisibility(View.VISIBLE);
                    img_dice3.setVisibility(View.INVISIBLE);
                    numbersDicesCount = 1;
                }
                else if(numbersDices.equals("3")){
                    img_dice2.setVisibility(View.VISIBLE);
                    img_dice3.setVisibility(View.VISIBLE);
                    numbersDicesCount = 2;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };

        dices.setOnItemSelectedListener(choiced);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_play_dice:

                sumDices = 0;
                ImageView[] imv = {img_dice1,img_dice2,img_dice3};
                int numberDicesInt = Integer.parseInt(numbersDices);

                for(numbersDicesCount=0; numberDicesInt>numbersDicesCount; numbersDicesCount++){
                    rolleNumber = r.nextInt(6)+1;

                    if(rolleNumber==1){
                        imv[numbersDicesCount].setImageResource(R.drawable.dice1);
                        sumDices += 1;
                    }else if(rolleNumber==2){
                        imv[numbersDicesCount].setImageResource(R.drawable.dice2);
                        sumDices += 2;
                    }else if(rolleNumber==3){
                        imv[numbersDicesCount].setImageResource(R.drawable.dice3);
                        sumDices += 3;
                    }else if(rolleNumber==4){
                        imv[numbersDicesCount].setImageResource(R.drawable.dice4);
                        sumDices += 4;
                    }else if(rolleNumber==5){
                        imv[numbersDicesCount].setImageResource(R.drawable.dice5);
                        sumDices += 5;
                    }else if(rolleNumber==6){
                        imv[numbersDicesCount].setImageResource(R.drawable.dice6);
                        sumDices += 6;
                    }
                }
                sum_dice.setText("Soma: " + String.valueOf(sumDices));
                break;

            case R.id.btn_play_coin:
                ImageView[] imv1 = {img_coin1,img_coin2,img_coin3};
                int numberCoinsInt = Integer.parseInt(numbersCoins);

                heads = 0;
                tails = 0;

                for(numbersCoinsCount=0; numberCoinsInt>numbersCoinsCount; numbersCoinsCount++) {
                    rolleNumber = r.nextInt(2) + 1;
                    if(numbersCoinsCount==0){
                        headsOrTails(imv1);
                    }else if(numbersCoinsCount==1){
                        headsOrTails(imv1);
                    }else if(numbersCoinsCount==2){
                        headsOrTails(imv1);
                    }
                }

                Log.i("heads ", String.valueOf(heads));
                Log.i("tails ", String.valueOf(tails));

                if(heads>tails){
                    who_win_heads_or_tails.setText("Ganhou: " + String.valueOf("Cara"));
                }else if(heads<tails){
                    who_win_heads_or_tails.setText("Ganhou: " + String.valueOf("Coroa"));
                }else{
                    who_win_heads_or_tails.setText("Ganhou: " + String.valueOf("Empatou"));
                }
                break;
            default:
        }
    }

    public void headsOrTails(ImageView[] imv1){
        if(rolleNumber==1){
            imv1[numbersCoinsCount].setImageResource(R.drawable.cara);
            heads++;
        }else{
            imv1[numbersCoinsCount].setImageResource(R.drawable.coroa);
            tails++;
        }
    }
}
