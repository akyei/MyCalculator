package com.castle.mycalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.LayoutDirection;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Button;
import android.util.Log;
import java.util.regex.*;

public class MainActivity extends AppCompatActivity {
    private TextView display;
    private Button button;
    private Button operatorButton;
    private String input;
    private String operator;
    private  double num1;
    private boolean operatorDown = false;
    private final double SQRT_2 = Math.sqrt(2.0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        display = (TextView) findViewById(R.id.editText1);
        display.setText("0.0");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void onClick(View v){
        Button button = (Button) v;
        String text = display.getText().toString();
        input = button.getText().toString();
        Double result = 0.0;
        if (input.equals("+") || input.equals("-") || input.equals("x") || input.equals("÷") || input.equals("+/-") || input.equals("%")) {
            if (input.equals("+")) {
                if (operatorDown){
                    operator = "+";
                    operatorButton.setBackgroundColor(ContextCompat.getColor(this, R.color.simpleOperators));
                    operatorButton = button;
                    operatorButton.setBackgroundColor(ContextCompat.getColor(this, R.color.simpleOperatorsDown));
                } else {
                    operator = "+";
                    operatorButton = button;
                    button.setBackgroundColor(ContextCompat.getColor(this, R.color.simpleOperatorsDown));
                }
            }
            else if (input.equals("-")) {
                if (operatorDown) {
                    operator = "-";
                    operatorButton.setBackgroundColor(ContextCompat.getColor(this, R.color.simpleOperators));
                    operatorButton = button;
                    operatorButton.setBackgroundColor(ContextCompat.getColor(this, R.color.simpleOperatorsDown));
                }else {
                    operator = "-";
                    operatorButton = button;
                    button.setBackgroundColor(ContextCompat.getColor(this, R.color.simpleOperatorsDown));
                }
            }
            else if (input.equals("x")) {
                if (operatorDown) {
                    operator = "x";
                    operatorButton.setBackgroundColor(ContextCompat.getColor(this, R.color.simpleOperators));
                    operatorButton = button;
                    operatorButton.setBackgroundColor(ContextCompat.getColor(this, R.color.simpleOperatorsDown));
                }else {
                    operator = "x";
                    operatorButton = button;
                    button.setBackgroundColor(ContextCompat.getColor(this, R.color.simpleOperatorsDown));
                }
            }
            else if (input.equals("÷")) {
                if (operatorDown) {
                    operator = "÷";
                    operatorButton.setBackgroundColor(ContextCompat.getColor(this, R.color.simpleOperators));
                    operatorButton = button;
                    operatorButton.setBackgroundColor(ContextCompat.getColor(this, R.color.simpleOperatorsDown));
                }else {
                    operator = "÷";
                   // operatorDown = true;
                    operatorButton = button;
                    button.setBackgroundColor(ContextCompat.getColor(this, R.color.simpleOperatorsDown));
                }
            }
            else if (input.equals("+/-")){
                if (operatorDown){
                    display.setText("0.0");
                    text = "0.0";
                } else {
                    double currInput = Double.parseDouble(text);
                    display.setText(Double.toString(currInput * (-1.0)));
                }
            }
            else if (input.equals("%")){
                if (operatorDown) {
                    display.setText("0.0");
                    text = "0.0";
                } else {
                    double currInput = Double.parseDouble(text);
                    display.setText(Double.toString(currInput / 100));
                }
            }
            if (operatorDown){
                num1 = Double.parseDouble(text.substring(0, text.length()-1));
            } else {
                num1 = Double.parseDouble(text);
                if (! (input.equals("%") || input.equals("+/-")))
                    operatorDown = true;
            }
        }
        if (input.equals("x²") || input.equals("x³") || input.equals("√") ||
                input.equals("sin(x)") || input.equals("cos(x)") || input.equals("tan(x)")
                || input.equals("ln") || input.equals("log10")) {

            num1 = Double.parseDouble(text);

            if (input.equals("x²"))
                result = num1 * num1;
            else if (input.equals("x³"))
                result = num1 * num1 * num1;
            else if (input.equals("√"))
                result = Math.sqrt(num1);
            else if (input.equals("sin(x)"))
                result = Math.sin(num1);
            else if (input.equals("cos(x)"))
                result = Math.cos(num1);
            else if (input.equals("tan(x)"))
                result = Math.tan(num1);
            else if (input.equals("%"))
                result = num1 / 100;
            else if (input.equals("ln"))
                result = Math.log(num1);
            else if (input.equals("log10"))
                result = Math.log10(num1);

            display.setText(Double.toString(result));
        }

        else if (input.equals("=")) {
            if (operator.equals("+")) {
                result = num1 + Double.parseDouble(text.substring(text.lastIndexOf('+') + 1));
                operatorDown = false;

            }
            else if (operator.equals("-")) {
                result = num1 - Double.parseDouble(text.substring(text.lastIndexOf('-') + 1));
                operatorDown = false;
            }
            else if (operator.equals("x")) {
                result = num1 * Double.parseDouble(text.substring(text.lastIndexOf('x') + 1));
                operatorDown = false;
            }
            else if (operator.equals("÷")) {
                result = num1 / Double.parseDouble(text.substring(text.lastIndexOf('÷') + 1));
                operatorDown = false;
            }

            if (! operator.equals("=")) {
                display.setText(Double.toString(result));
                operator = "=";
            }
            operatorButton.setBackground(ContextCompat.getDrawable(this, R.drawable.simple_operator_border));
        }

        else {
            if (text.equals("0.0") || text.equals(" ") ) {
                if (input.equals("π")){
                    display.setText(Double.toString(Math.PI));
                } else if (input.equals("e")) {
                    display.setText(Double.toString(Math.E));
                } else if (input.equals("√2")){
                    display.setText(Double.toString(SQRT_2));
                } else {
                    display.setText(input);
                }
            } else
                if (operator != null && operator.equals("=")){
                    if (! (input.equals("π") || input.equals("e") || input.equals("%") || input.equals("√2"))){
                        display.setText(input);
                    } else if (input.equals("π")) {
                      display.setText(Double.toString(Math.PI));
                    } else if (input.equals("e")) {
                        display.setText(Double.toString(Math.E));
                    }else if (input.equals("√2")) {
                        display.setText(Double.toString(SQRT_2));
                    }
                    operator = null;
                } else if (input.equals("π")) {
                    display.append(Double.toString(Math.PI));
                } else if (input.equals("e")) {
                    display.append(Double.toString(Math.E));
                }else if (input.equals("√2")){
                    display.append(Double.toString(SQRT_2));
                }else{
                    Pattern p = Pattern.compile("[+\\-\\÷x]");
                    Matcher m = p.matcher(display.getText().toString());
                    if (m.find() && input.matches("[+\\-\\÷x]")){
                        String currText = display.getText().toString();
                        Log.v("I'm Here", "Regex Part");
                        String newText = currText.substring(0, currText.length()-1);
                        display.setText(newText + input);
                    } else if (!( input.equals("+/-") || input.equals("%")) ){
                        display.append(input);
                    }
                }
        }

        if (input.equals("Clear") || input.equals("C")){
            num1 = 0.0;
            operator = "";
            display.setText("0.0");
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
