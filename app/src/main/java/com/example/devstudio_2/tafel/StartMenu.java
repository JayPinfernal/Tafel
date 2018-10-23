package com.example.devstudio_2.tafel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StartMenu extends AppCompatActivity implements View.OnClickListener {
        Button start,can;
        SharedPreferences sp;
        String id,nam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
        start=(Button)findViewById(R.id.startr);
        can=(Button)findViewById(R.id.cancel);
        start.setOnClickListener(this);
        can.setOnClickListener(this);
        sp=getSharedPreferences("bookedId",Context.MODE_PRIVATE);
        id=sp.getString("tableId","0");
        nam=sp.getString("tableName","none");
    }

    @Override
    public void onClick(View view) {
        id=sp.getString("tableId","0");
        nam=sp.getString("tableName","none");
        if(view==start){
          if("0".equalsIgnoreCase(id)){
              Intent intent=new Intent(this,Reservation.class);
              startActivity(intent);
          }
          else{
              Toast.makeText(this,"Please cancel a prior reservation before addding a new one",Toast.LENGTH_LONG);
          }
        }
        if(view==can){
            if("0".equalsIgnoreCase(id)){
                Toast.makeText(this,"Please create a new reservation so that you can cancel it",Toast.LENGTH_LONG);
            }
            else{


                AlertDialog.Builder permit = new AlertDialog.Builder(this);
                permit.setMessage("Do you want to cancel reservation for table "+nam)
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                updateDb(id,nam,"n","0-0-0","00:00");
                                SharedPreferences.Editor edit= sp.edit();
                                edit.putString("tableId","0");
                                edit.putString("tableName","none");
                                edit.commit();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alert=permit.create();
                alert.setTitle("Cancelling Reservation");
                alert.show();
               // Toast.makeText(this,"Please cancel a prior reservation before addding a new one",Toast.LENGTH_LONG);
            }
        }
    }

    public boolean updateDb(String id,String name,String status,String date,String time){
        DatabaseReference dtab= FirebaseDatabase.getInstance().getReference("tables").child(id);
        Tables tupd= new Tables(id,name,status,date,time);
        dtab.setValue(tupd);
        Toast.makeText(this,name +
                        " has been updated",
                Toast.LENGTH_LONG).show();
        return true;
    }
}
