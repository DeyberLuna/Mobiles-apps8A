package com.example.actividad_final2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.actividad_final2.adapters.UserAdapter;
import com.example.actividad_final2.models.User;

import java.util.ArrayList;

public class list_users extends AppCompatActivity {
    private RecyclerView rv;
    ArrayList<User> arreglo= new ArrayList<User>();
    ArrayList<User> male= new ArrayList<User>();
    ArrayList<User> female= new ArrayList<User>();
    SearchView search_All;
    Dialog dialog;
    String clave;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        this.setTitle(R.string.app_list_name);
        clave=getIntent().getExtras().getString("clave");


        if(clave.equals("main")){
            clave="";

            dialog=new Dialog(this);
            dialog.setContentView(R.layout.cardview_welcome);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Button btn_alert=dialog.findViewById(R.id.btn_close_alert);
            dialog.show();
            btn_alert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }else if(clave.equals("")){

        }



        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.primary));

        }
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(this.getResources().getColor(R.color.primary_100)));
        arreglo.add(new User("fernando","fernando@gmail.com","male"));
        arreglo.add(new User("hernan","hernan@gmail.com","male"));
        arreglo.add(new User("sebastian","sebastian@gmail.com","male"));
        arreglo.add(new User("maria","maria@gmail.com","female"));
        arreglo.add(new User("luis","luis@gmail.com","male"));
        arreglo.add(new User("diana","diana@gmail.com","female"));
        arreglo.add(new User("alejandra","alejandra@gmail.com","female"));
        arreglo.add(new User("rodrigo","esteban@gmail.com","male"));
        arreglo.add(new User("esteban","esteban@gmail.com","male"));

        male.add(new User("fernando","fernando@gmail.com","male"));
        male.add(new User("hernan","hernan@gmail.com","male"));
        male.add(new User("sebastian","sebastian@gmail.com","male"));
        male.add(new User("luis","luis@gmail.com","male"));
        male.add(new User("rodrigo","esteban@gmail.com","male"));
        male.add(new User("esteban","esteban@gmail.com","male"));


        female.add(new User("maria","maria@gmail.com","female"));
        female.add(new User("diana","diana@gmail.com","female"));
        female.add(new User("alejandra","alejandra@gmail.com","female"));



        rv=findViewById(R.id.recycler_list);
        rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        LinearLayoutManager lmanager=new LinearLayoutManager(this);
        rv.setLayoutManager(lmanager);

        userAdapter= new UserAdapter(arreglo,this);
        rv.setAdapter(userAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options_list, menu);
        MenuItem menuItem = menu.findItem(R.id.search_btn);
        search_All = (SearchView) menuItem.getActionView();
        search_All.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                userAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.search_btn:

                return true;
            case R.id.option_male:
                rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
                LinearLayoutManager malelayout=new LinearLayoutManager(this);
                rv.setLayoutManager(malelayout);

                UserAdapter maleA= new UserAdapter(male,this);
                rv.setAdapter(maleA);
                search_All.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        maleA.getFilter().filter(newText);
                        return false;
                    }
                });

                return true;
            case R.id.option_female:
                rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
                LinearLayoutManager layoutM=new LinearLayoutManager(this);
                rv.setLayoutManager(layoutM);

                UserAdapter femaleAdapter= new UserAdapter(female,this);
                rv.setAdapter(femaleAdapter);
                search_All.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        femaleAdapter.getFilter().filter(newText);
                        return false;
                    }
                });

                return true;
            case R.id.option_all:
                rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
                LinearLayoutManager lmanager=new LinearLayoutManager(this);
                rv.setLayoutManager(lmanager);

                UserAdapter userAdpt= new UserAdapter(arreglo,this);
                rv.setAdapter(userAdpt);
                search_All.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        userAdpt.getFilter().filter(newText);
                        return false;
                    }
                });

                return true;
            case R.id.option_about:
                Intent intent=new Intent(list_users.this,About_me.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            case R.id.option_logout:

                Intent it=new Intent(list_users.this,MainActivity.class);
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(it);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }




}