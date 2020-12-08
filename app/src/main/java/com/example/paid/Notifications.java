package com.example.paid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Notifications extends AppCompatActivity implements View.OnClickListener {

    private TextView back, notify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

       back = (TextView) findViewById(R.id.NotificationBar);
       back.setOnClickListener(this);

       notify = (TextView) findViewById(R.id.buttonNotify);
       String message = getIntent().getStringExtra("message");
       notify.setText(message);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(Notifications.this,Home.class));
    }
}