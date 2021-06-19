package com.example.actividad_final2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class update_info_user extends AppCompatActivity {
    private Window window;
    private EditText name,username,gender,password;
    private Spinner sp_gender,sp_city;
    private ImageView btn_date;
    private TextView label_date;
    private Button btn_update_user;
    String gender_selected="Male";
    String city_selected="Colombia";
    String pass="";
 


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info_user);
        sp_city=findViewById(R.id.spinner_city);
        sp_gender=findViewById(R.id.spinner_gender);
        btn_date=findViewById(R.id.btn_update_date);
        label_date=findViewById(R.id.label_date_update);
        password=findViewById(R.id.input_pass_update);

        pass=password.getText().toString();




        btn_update_user=findViewById(R.id.btn_update_user);
        btn_update_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(update_info_user.this,list_users.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("clave","update");
                startActivity(intent);
            }
        });

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleChoseeDate();
            }
        });

        String[]array_gender={"Male","Female","Other"};
        String[]array_citys={"Colombia","Mexico","Peru","Argentina","Ecuador","España","Brasil","Ucrania","Libano","Sudafrica"};

        sp_city.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,array_citys));
        sp_gender.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,array_gender));

        sp_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city_selected=parent.getItemAtPosition(position).toString();
               
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        name=findViewById(R.id.input_name_update);
        username=findViewById(R.id.input_username_update);
        String nameExtra=getIntent().getStringExtra("name");
        String emailExtra=getIntent().getStringExtra("email");
        String genderExtra=getIntent().getStringExtra("gender");
        String password=getIntent().getStringExtra("pass");
        name.setText(nameExtra);
        username.setText(emailExtra);


        this.setTitle(R.string.app_update);

        if(Build.VERSION.SDK_INT>=21){
            window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.primary));

        }
        ActionBar appbar=getSupportActionBar();
        appbar.setDisplayHomeAsUpEnabled(true);
        appbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary_100)));


    }

    private void handleChoseeDate() {

       final Calendar calendar= Calendar.getInstance();
       int dia=calendar.get(Calendar.DAY_OF_MONTH);
       int mes=calendar.get(Calendar.MONTH);
       int anio=calendar.get(Calendar.YEAR);
       DatePickerDialog dpd=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
           @Override
           public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            label_date.setText(dayOfMonth+"/"+(month+1)+"/"+year);
           }
       },dia,mes,anio);
       dpd.show();

    }

    @Override
    public boolean onSupportNavigateUp() {
        backScreen();
        return false;
    }

    private void backScreen() {
        Intent i= new Intent(update_info_user.this,list_users.class);
        i.putExtra("clave","update");
        startActivity(i);
    }
}