package com.example.paid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity  {

    String Plantname, Plantnum, Name, Email;
    TextView t1, t2, coins, username , mailid;
    SharedPreferences sp;
    SharedPreferences sp3;
    int number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        t1 = findViewById(R.id.namep);
        t2 = findViewById(R.id.num);
        coins = findViewById(R.id.coins);
        username = findViewById(R.id.username);
        mailid = findViewById(R.id.mailID);

        coins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,Rewards.class));
            }
        });

        SharedPreferences sp3 = getApplicationContext().getSharedPreferences("Profile",Context.MODE_PRIVATE);
        Name = sp3.getString("Username","");
        Email = sp3.getString("Email","");

        username.setText(Name);
        mailid.setText(Email);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("UsersDetails", Context.MODE_PRIVATE);
        Plantname = sp.getString("Name", "");
        Plantnum = sp.getString("NoOfP","");

        t1.setText(Plantname);
        t2.setText(Plantnum);
        number = Integer.valueOf(Plantnum);
        number = number *50;
        Plantnum = String.valueOf(number);
        coins.setText(Plantnum);

        TextView prof = (TextView) findViewById(R.id.profile);
        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( Profile.this, Home.class));
            }
        });

    }
    
    public void Logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
    }

}