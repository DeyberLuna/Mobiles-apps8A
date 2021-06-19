package com.example.actividad_final2.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actividad_final2.R;
import com.example.actividad_final2.models.User;
import com.example.actividad_final2.update_info_user;

import java.util.ArrayList;
import java.util.Collection;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHoldeUser> implements Filterable {

    ArrayList<User> arrayList;
    ArrayList<User> arrayListAll;
    private Context ctx;

    public UserAdapter(ArrayList<User> users, Context ctx)
    {
        this.arrayList=users;
        arrayListAll=new ArrayList<>();
        arrayListAll.addAll(arrayList);
        this.ctx=ctx;
    }
    @NonNull
    @Override
    public ViewHoldeUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        int layoutId=R.layout.activity_cardview_user;
        LayoutInflater inflaterlayout= LayoutInflater.from(context);
        View view=inflaterlayout.inflate(layoutId,parent,false);
        ViewHoldeUser viewHoldeUser=new ViewHoldeUser(view);

        return viewHoldeUser;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoldeUser holder, int position) {
           holder.asignarDatos(arrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public  Filter getFilter() {


        return myFilter;
    }

    Filter myFilter = new Filter() {

        //Automatic on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            ArrayList<User> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(arrayListAll);
            } else {
                for (User person: arrayListAll) {
                   String result= person.getName();
                    if (result.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(person);

                    }
                }
            }


            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        //Automatic on UI thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            arrayList.clear();
            arrayList.addAll((Collection<? extends User>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHoldeUser extends RecyclerView.ViewHolder {

        TextView name,email;
        ImageView profile_user_img;
        public ViewHoldeUser(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            email=itemView.findViewById(R.id.email);
            profile_user_img=itemView.findViewById(R.id.profile_user_img);
        }

        public void asignarDatos(User userDatos) {
            name.setText(userDatos.getName());
            email.setText(userDatos.getEmail());
            if(userDatos.getGender().equals("male")){
                profile_user_img.setImageResource(R.drawable.male);
            }else{
                profile_user_img.setImageResource(R.drawable.female);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i= new Intent(ctx,update_info_user.class);
                    i.putExtra("name",name.getText().toString());
                    i.putExtra("email",email.getText().toString());
                    i.putExtra("gender",userDatos.getGender());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    ctx.startActivity(i);



                }
            });
        }
    }
}
