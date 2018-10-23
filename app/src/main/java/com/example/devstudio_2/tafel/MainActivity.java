package com.example.devstudio_2.tafel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt=(TextView)findViewById(R.id.tafel);

    }
    public void onClick(View v){
        txt.setTextColor(Color.parseColor("#00ff33"));
        txt=findViewById(R.id.tafel2);
        txt.setTextColor(Color.parseColor("#001f1e"));
        Toast.makeText(this,"Welcome to the restaurant reservation app",Toast.LENGTH_SHORT).show();
        SharedPreferences sp= getSharedPreferences("bookedId",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit= sp.edit();

        String id=sp.getString("tableId","123");
        if("123".equals(id)){
            edit.putString("tableId","0");
            edit.commit();
        }
        else{
            Toast.makeText(this," "+id,Toast.LENGTH_SHORT).show();

        }

        Intent intent=new Intent(this,StartMenu.class);
        startActivity(intent);
    }

    public void saveInfo(){

    }
}
