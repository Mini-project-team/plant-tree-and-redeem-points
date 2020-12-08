package com.example.paid;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Bravo extends AppCompatActivity {

    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bravo);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("My Notification","My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        back = (Button) findViewById(R.id.backtoh);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SharedPreferences sp = getApplicationContext().getSharedPreferences("UsersDetails", Context.MODE_PRIVATE);
                String num = sp.getString("NoOfP","");
                if(num != null){
                    int number = Integer.valueOf(num);
                    number = number -1 ;
                    if(!num.equals("1")){
                        String message = "You have to click "+number+" more pictures";
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(Bravo.this,"My Notification");
                        builder.setContentTitle("Click More Pictures");
                        builder.setContentText(message);
                        builder.setSmallIcon(R.drawable.ic_baseline_message_24);
                        builder.setAutoCancel(true);

                        Intent intent = new Intent(Bravo.this,Notifications.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("message",message);

                        PendingIntent pendingIntent = PendingIntent.getActivity(Bravo.this,
                                0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                        builder.setContentIntent(pendingIntent);
                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Bravo.this);
                        managerCompat.notify(1,builder.build());
                    }
                    else {
                        String message = "You have clicked the pictures successfully now you can redeem your coins from rewards button :))";
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(Bravo.this,"My Notification");
                        builder.setContentTitle("Click More Pictures");
                        builder.setContentText(message);
                        builder.setSmallIcon(R.drawable.ic_baseline_message_24);
                        builder.setAutoCancel(true);

                        Intent intent = new Intent(Bravo.this,Notifications.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("message",message);

                        PendingIntent pendingIntent = PendingIntent.getActivity(Bravo.this,
                                0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                        builder.setContentIntent(pendingIntent);
                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Bravo.this);
                        managerCompat.notify(1,builder.build());
                    }
                }
            }
        });

    }
}