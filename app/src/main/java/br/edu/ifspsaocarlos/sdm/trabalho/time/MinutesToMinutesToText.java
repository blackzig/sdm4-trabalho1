package br.edu.ifspsaocarlos.sdm.trabalho.time;

/**
 * Created by zigui on 27/11/2017.
 */

public class MinutesToMinutesToText {

    public String minutesToMinutesString(Integer i){
        String minutesText = "00:00";
        if(i>=1 && i<=9){
            minutesText = "0"+String.valueOf(i)+":00";
        }else if(i>=10 && i<=60){
            minutesText = String.valueOf(i)+":00";
        }
        return minutesText;
    }
}
