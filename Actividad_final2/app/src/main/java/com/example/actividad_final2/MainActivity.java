package com.example.actividad_final2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_sign_up,btn_sign_in;
    private EditText editTextEmail,editPass;
    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextEmail=findViewById(R.id.editTextTextEmailAddress);
        editPass=findViewById(R.id.editTextTextPassword);

        getSupportActionBar().hide();
        shared=getSharedPreferences("login", Context.MODE_PRIVATE);

        btn_sign_in=findViewById(R.id.btn_sign_in);
        btn_sign_up=findViewById(R.id.btn_sign_up);

        btn_sign_up.setOnClickListener(this);
        btn_sign_in.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sign_up:
                Intent i= new Intent(MainActivity.this,sign_up.class);
                startActivity(i);
                break;
            case R.id.btn_sign_in:
                if(!editTextEmail.getText().toString().equals("")&& !editPass.getText().toString().equals("")){
                    if(!validateEmail(editTextEmail.getText().toString())){
                        Toast.makeText(this, "E-mail no valido", Toast.LENGTH_SHORT).show();
                    }else{
                        if(editTextEmail.getText().toString().equals(shared.getString("username","")) &&
                        editPass.getText().toString().equals(shared.getString("password",""))){
                            Intent inten= new Intent(MainActivity.this,list_users.class);
                            inten.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            inten.putExtra("clave","main");
                            startActivity(inten);
                            break;
                        }else{
                            Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                        }

                    }
                }else{
                    Toast.makeText(this, "Complete los datos", Toast.LENGTH_SHORT).show();
                }


        }
    }

    private boolean validateEmail(String email) {
        Pattern pattern= Patterns.EMAIL_ADDRESS;

        return pattern.matcher(email).matches();
    }
}