package com.example.paid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Rewards extends AppCompatActivity implements View.OnClickListener {

    TextView reward, coin, total;
    String Plantnum;
    int number;
    View coupon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        coin = findViewById(R.id.coins2);
        total = findViewById(R.id.couponamount);
        coupon = findViewById(R.id.coupon);
        coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Rewards.this,Redeem.class));
            }
        });
        SharedPreferences sp = getApplicationContext().getSharedPreferences("UsersDetails", Context.MODE_PRIVATE);
        Plantnum = sp.getString("NoOfP","");
        number = Integer.valueOf(Plantnum);
        number = number *50;
        Plantnum = String.valueOf(number);
        coin.setText(Plantnum);
        total.setText(Plantnum);

        reward = (TextView) findViewById(R.id.coin);
        reward.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(Rewards.this,Home.class));
    }
}