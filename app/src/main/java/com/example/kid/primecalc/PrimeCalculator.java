package com.example.kid.primecalc;

import android.app.Application;
import android.content.Context;
import android.text.Editable;
import android.view.Gravity;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by KID on 25.12.2014.
 */
public class PrimeCalculator {
    private int a;
    private int b;
    private int p;
    private boolean ok = false;
    public static int duration = Toast.LENGTH_LONG;


    ArrayList<String> res = new ArrayList<String>();
    String resRandom = "";
    int counter = 0;
    double preresult = 0;
    double result = 0;


    public void checkValue(Context context, String aEdit, String bEdit){
        while(!ok){
            if (aEdit == null || bEdit == null){
                Toast toast = Toast.makeText(context,R.string.toast_higher_than_0,duration);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            } else if (Integer.parseInt(aEdit) < 1) {
                Toast toast = Toast.makeText(context,R.string.toast_higher_than_0, duration);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            } else if (Integer.parseInt(bEdit) < 1) {
                Toast toast = Toast.makeText(context,R.string.toast_higher_than_0, duration);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            } else if (Integer.parseInt(bEdit) < Integer.parseInt(aEdit)) {
                Toast toast = Toast.makeText(context,R.string.toast_higher_than_A, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            } else ok = true;
        }
    }

    public ArrayList<Boolean> createList(int b) throws IOException {
        ArrayList<Boolean> fillArray = new ArrayList<Boolean>();
        for (int x = 0; x <= b; x++) fillArray.add(true);
        return fillArray;
    }

    public ArrayList<String> calc(ArrayList<Boolean> arrList, int a) {
        arrList.set(0, false);
        arrList.set(1, false);
        for (int i = 2; i < arrList.size(); i++) {
            if (arrList.get(i)) {
                for (int j = 2; i * j < arrList.size(); j++) {
                    arrList.set(j * i, false);
                }
            }
        }

        for (int k = 0; k <= a; k++) arrList.set(k, false);

        for (int y = 0; y < arrList.size(); y++) {
            if (arrList.get(y)) {
                res.add(Integer.toString(y));
            }
        }

        return res;
    }

    public String calcRandomArray(ArrayList<Boolean> arrList, int a, int p) {
        arrList.set(0, false);
        arrList.set(1, false);
        ArrayList<Integer> intArrList = new ArrayList<Integer>();
        ArrayList<Integer> rezArrList = new ArrayList<Integer>();
        Random rand = new Random();

        for (int i = 2; i < arrList.size(); i++) {
            if (arrList.get(i)) {
                for (int j = 2; i * j < arrList.size(); j++) {
                    arrList.set(j * i, false);
                }
            }
        }

        for (int k = 0; k <= a; k++) arrList.set(k, false);

        for (int z = 0; z < arrList.size(); z++){
            if (arrList.get(z)) intArrList.add(z);
        }
        rezArrList.add(intArrList.get(rand.nextInt(intArrList.size())));
        counter++;
        while (result >= p+1 || result <= p-1 || result != p){
            rezArrList.add(intArrList.get(rand.nextInt(intArrList.size())));
            counter++;
            for (int i = 0; i < rezArrList.size()-1; i++){
                for (int j = 1; j < rezArrList.size(); j++){
                    preresult += Math.abs(rezArrList.get(i)-rezArrList.get(j));
                }
            }
            result = preresult/counter;
        }
        resRandom = rezArrList.toString();
        return resRandom;
    }
}
