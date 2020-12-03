package com.example.paid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Rewards extends AppCompatActivity implements View.OnClickListener {

   // private TextView totalcoin;
    private TextView reward;
    //private final int coin;

    /*public Rewards(int coin) {
        this.coin = coin;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

       /* totalcoin = (TextView) findViewById(R.id.totalcoin);
        totalcoin.setText(coin);*/

        reward = (TextView) findViewById(R.id.coin);
        reward.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(Rewards.this,Home.class));
    }
}