package com.example.actividad_final2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class sign_up extends AppCompatActivity {

     Button btn_register;
     EditText username,first_name,last_name,password,conf_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btn_register=(Button)findViewById(R.id.btn_register);
        username=findViewById(R.id.input_username);
        first_name=findViewById(R.id.input_name);
        last_name=findViewById(R.id.input_lastname);
        password=findViewById(R.id.input_pass);
        conf_password=findViewById(R.id.input_confpass);



        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateDatos();


            }
        });

        getSupportActionBar().hide();
    }

    private void validateDatos() {
        if(!username.getText().toString().equals("")&&!first_name.getText().toString().equals("")&&!last_name.getText().toString().equals("")&&
                !password.getText().toString().equals("")&&!conf_password.getText().toString().equals("")){
            if(!validarEmail(username.getText().toString())){
                Toast.makeText(this, "E-mail no valido", Toast.LENGTH_SHORT).show();
            }else{

                if(password.getText().toString().equals(conf_password.getText().toString())){

                    Intent i= new Intent(sign_up.this,MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }else{
                    Toast.makeText(this, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show();

                }
            }
        }else{
            Toast.makeText(this, "Completa los datos", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}