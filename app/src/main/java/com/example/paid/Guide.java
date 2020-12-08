package com.example.paid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Guide extends AppCompatActivity implements View.OnClickListener {

     Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        ok = (Button) findViewById(R.id.ok1);
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(Guide.this,Home.class));
    }
}