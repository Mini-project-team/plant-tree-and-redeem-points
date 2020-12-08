package com.example.paid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Form extends AppCompatActivity implements View.OnClickListener {

     EditText NameOfPlant, NoOfPlant;
     Button camera;
     int numberOfPictures;
     TextView back;
     SharedPreferences sp;
     SharedPreferences sp2;
     String name, number, coins;
     DatabaseReference mDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        NameOfPlant =  findViewById(R.id.Plantname);
        NoOfPlant =  findViewById(R.id.NoOfPlants);

        camera =  findViewById(R.id.camera) ;
        camera.setOnClickListener(this);

        back =  findViewById(R.id.pl);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       switch(v.getId()){
           case R.id.camera:
               name = NameOfPlant.getText().toString();
               number = NoOfPlant.getText().toString();
               if(name.isEmpty()){
                   NameOfPlant.setError("This is compulsory");
                   NameOfPlant.requestFocus();
                   return;
               }

               if(number.isEmpty()){
                   NoOfPlant.setError("This is compulsory");
                   NoOfPlant.requestFocus();
                   return;
               }

               if(name != null && number !=null){
                   numberOfPictures = Integer.valueOf(number);
                   numberOfPictures = numberOfPictures * 50 ;
                   //coins = String.valueOf(numberOfPictures);
                   sp = getSharedPreferences("UsersDetails", Context.MODE_PRIVATE);
                   SharedPreferences.Editor editor = sp.edit();
                   editor.putString( "Name", name);
                   editor.putString("NoOfP", number);
                   editor.apply();
                   sp2 = getApplicationContext().getSharedPreferences("Login",Context.MODE_PRIVATE);
                   String mail = sp2.getString("EmailIDofUser","anushreepoojary@gmail.com");
                   Plant plant = new Plant(mail ,name, number, numberOfPictures);
                   mDataBase = FirebaseDatabase.getInstance().getReference();
                   String PlantID = mDataBase.push().getKey();
                   mDataBase.child("PlantDetails").child(PlantID).setValue(plant);
                   Toast.makeText(Form.this,"Information saved", Toast.LENGTH_LONG).show();
                   startActivity(new Intent(Form.this,Camera.class));
                   break;
               }

           case R.id.pl:
               startActivity(new Intent(Form.this,Home.class));
               break;
       }
    }

}