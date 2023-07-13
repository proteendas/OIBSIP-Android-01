package com.example.oibsip_task3;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    TextView inputViewData;
    TextView outputViewData;
    String inputValue = "";
    String formula = "";
    String tempFormula = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextViewsIDs();
    }

    private void initTextViewsIDs()
    {
        inputViewData = findViewById(R.id.inputView);
        outputViewData = findViewById(R.id.outputView);
    }

    private void setDataValue(String givenValue)
    {
        inputValue =inputValue + givenValue;
        inputViewData.setText(inputValue);
    }


    public void equals(View view)
    {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf();

        try {
            result = (double)engine.eval(formula);
        } catch (ScriptException e)
        {
            Toast.makeText(this, "Invalid Data Input", Toast.LENGTH_SHORT).show();
        }

        if(result != null)
            outputViewData.setText(String.valueOf(result.doubleValue()));

    }

    private void checkForPowerOf()
    {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for(int i = 0; i < inputValue.length(); i++)
        {
            if (inputValue.charAt(i) == '^')
                indexOfPowers.add(i);
        }

        formula = inputValue;
        tempFormula = inputValue;
        for(Integer index: indexOfPowers)
        {
            changeFormula(index);
        }
        formula = tempFormula;
    }

    private void changeFormula(Integer index)
    {
        String numberLeft = "";
        String numberRight = "";

        for(int i = index + 1; i< inputValue.length(); i++)
        {
            if(isNumeric(inputValue.charAt(i)))
                numberRight = numberRight + inputValue.charAt(i);
            else
                break;
        }

        for(int i = index - 1; i >= 0; i--)
        {
            if(isNumeric(inputValue.charAt(i)))
                numberLeft = numberLeft + inputValue.charAt(i);
            else
                break;
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        tempFormula = tempFormula.replace(original,changed);
    }

    private boolean isNumeric(char c)
    {
        if((c <= '9' && c >= '0') || c == '.')
            return true;

        return false;
    }


    public void clear(View view)
    {
        inputViewData.setText("");
        inputValue = "";
        outputViewData.setText("");
        leftBracket = true;
    }

    boolean leftBracket = true;

    public void bracket(View view)
    {
        if(leftBracket)
        {
            setDataValue("(");
            leftBracket = false;
        }
        else
        {
            setDataValue(")");
            leftBracket = true;
        }
    }

    public void power(View view)
    {
        setDataValue("^");
    }

    public void division(View view)
    {
        setDataValue("/");
    }

    public void seven(View view)
    {
        setDataValue("7");
    }

    public void eight(View view)
    {
        setDataValue("8");
    }

    public void nine(View view)
    {
        setDataValue("9");
    }

    public void multiply(View view)
    {
        setDataValue("*");
    }

    public void four(View view)
    {
        setDataValue("4");
    }

    public void five(View view)
    {
        setDataValue("5");
    }

    public void six(View view)
    {
        setDataValue("6");
    }

    public void subtraction(View view)
    {
        setDataValue("-");
    }

    public void one(View view)
    {
        setDataValue("1");
    }

    public void two(View view)
    {
        setDataValue("2");
    }

    public void three(View view)
    {
        setDataValue("3");
    }

    public void addition(View view)
    {
        setDataValue("+");
    }

    public void decimal(View view)
    {
        setDataValue(".");
    }

    public void zero(View view)
    {
        setDataValue("0");
    }

    public void doublezero(View view)
    {
        setDataValue("00");
    }
}