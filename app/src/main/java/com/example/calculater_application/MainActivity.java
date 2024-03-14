package com.example.calculater_application;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv , soulationTv;
    MaterialButton buttonC, button_open_bracket, buttonClose_bracket;
    MaterialButton button1,button2,button3,button4,button5,button6,button7,button8,button9,button0;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEquals,buttonAC,buttonDot;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.result_tv);
        soulationTv = findViewById(R.id.Soulation_tv);

        assignId(button0, R.id.button_0);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(buttonC, R.id.button_c);
        assignId(buttonDivide, R.id.button_divide);
        assignId(buttonMultiply, R.id.button_mlultiply);
        assignId(buttonPlus, R.id.button_plus);
        assignId(buttonMinus, R.id.button_minus);
        assignId(buttonDot, R.id.button_dot);
        assignId(buttonAC, R.id.button_ac);
        assignId(buttonEquals, R.id.button_equals);
        assignId(button_open_bracket, R.id.button_open_bracket);
        assignId(buttonClose_bracket, R.id.button_close_bracket);

    }

     void assignId(MaterialButton btn, int id)
     {
         btn = findViewById(id);
         btn.setOnClickListener(this);
     }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataTOCalculate = soulationTv.getText().toString();

        if (buttonText.equals("AC")) {
            soulationTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            soulationTv.setText(resultTv.getText());
            return;
        }
        if (buttonText.equals("X")) {
            try {
                dataTOCalculate = dataTOCalculate.substring(0, dataTOCalculate.length() - 1);
            }
            catch (Exception e)
            {
                System.out.println("you are Chutiya");
            }
        } else {
            dataTOCalculate = dataTOCalculate + buttonText;
        }

        soulationTv.setText(dataTOCalculate);

        String finalResult = getResult(dataTOCalculate);

        if (!finalResult.equals("Error")) {
            resultTv.setText(finalResult);
        }
    }

    String getResult(String data)
    {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
           String finalResult = context.evaluateString(scriptable,data,"Javascript",1 ,null).toString();
           if (finalResult.endsWith(".0")) {
               finalResult = finalResult.replace(".0","");
           }

           return finalResult;
        }
        catch (Exception e)
        {
            return "Error";
        }
    }




}