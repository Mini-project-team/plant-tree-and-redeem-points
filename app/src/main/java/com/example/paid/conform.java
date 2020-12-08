package com.example.paid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class conform extends AppCompatActivity implements View.OnClickListener {

    private Button yes, no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conform);

        yes = (Button) findViewById(R.id.yes);
        yes.setOnClickListener(this);

        no = (Button) findViewById(R.id.no);
        no.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.yes:
                startActivity(new Intent(conform.this, Bravo.class));
                break;
            case R.id.no:
                startActivity(new Intent(conform.this, Camera.class));
                break;
        }
    }
}