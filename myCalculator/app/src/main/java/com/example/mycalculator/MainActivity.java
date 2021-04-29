package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText number1;
    private EditText number2;
    private Button btnOperar;
    private TextView txtResult;
    private Spinner spinner;
    String spinenrItem="";
    float result=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1=findViewById(R.id.number1);
        number2=findViewById(R.id.number2);
        btnOperar=findViewById(R.id.btnOperar);
        txtResult=findViewById(R.id.txtResult);
        spinner=findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.spinnerop, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinenrItem=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnOperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n1= number1.getText().toString();
                String n2= number2.getText().toString();

                if(!n1.equals("")&&!n2.equals("")&&!spinenrItem.equals("Seleccione")){
                    float num1=Float.parseFloat(n1);
                    float num2=Float.parseFloat(n2);

                    number1.setBackgroundColor(Color.rgb(255, 255, 255 ));
                    number2.setBackgroundColor(Color.rgb(255, 255, 255 ));
                    spinner.setBackgroundColor(Color.rgb(255, 255, 255 ));

                    operarNums(num1,num2,spinenrItem);
                }else{
                    Toast.makeText(MainActivity.this, "Por favor completa los campos!", Toast.LENGTH_LONG).show();
                    txtResult.setText("");
                    if(n1.equals("")){
                        number1.setBackgroundColor(Color.rgb(242, 142, 143 ));
                    }else{
                        number1.setBackgroundColor(Color.rgb(255, 255, 255 ));
                    }
                    if(n2.equals("")){
                        number2.setBackgroundColor(Color.rgb(242, 142, 143 ));
                    }else{
                        number2.setBackgroundColor(Color.rgb(255, 255, 255 ));
                    }
                    if(spinenrItem.equals("Seleccione")){
                        spinner.setBackgroundColor(Color.rgb(242, 142, 143 ));
                    }else {
                        spinner.setBackgroundColor(Color.rgb(255, 255, 255 ));
                    }

                }
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

                inputMethodManager.hideSoftInputFromWindow(number2.getWindowToken(), 0);
            }
        });


    }

    private void operarNums(float num1, float num2, String spinenrItem) {

        Log.d("OPPPPPPPP: ",num1+num2+spinenrItem);



       if(spinenrItem.equals("Sumar")){
           result=num1+num2;
           String resultTxt=String.valueOf(result);
           txtResult.setText(resultTxt);
           result=0;
           Toast.makeText(this, "Resultado: "+resultTxt, Toast.LENGTH_LONG).show();

       }else if(spinenrItem.equals("Restar")){
           result=num1-num2;
           String resultTxt=String.valueOf(result);
           txtResult.setText(resultTxt);
           result=0;
           Toast.makeText(this, "Resultado: "+resultTxt, Toast.LENGTH_LONG).show();

       }else  if(spinenrItem.equals("Multiplicar")){
           result=num1*num2;
           String resultTxt=String.valueOf(result);
           txtResult.setText(resultTxt);
           result=0;
           Toast.makeText(this, "Resultado: "+resultTxt, Toast.LENGTH_LONG).show();

       }else if(spinenrItem.equals("Dividir")){
           result=num1/num2;
           String resultTxt=String.valueOf(result);
           txtResult.setText(resultTxt);
           result=0;
           Toast.makeText(this, "Resultado: "+resultTxt, Toast.LENGTH_LONG).show();

       }else{
           Toast.makeText(this, "Hubo un error", Toast.LENGTH_LONG).show();
       }


    }
}