package com.example.paid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ngo extends AppCompatActivity implements View.OnClickListener {

     private Button ngo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo);

        ngo = (Button) findViewById(R.id.ok2);
        ngo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(Ngo.this,Home.class));
    }
}