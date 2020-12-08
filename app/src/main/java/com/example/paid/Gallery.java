package com.example.paid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Gallery extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private SharedPreferences sp;
    private DatabaseReference mDatabaseRef;
    DatabaseReference reference;
    FirebaseAuth fAuth;
    FirebaseUser user;
    private List<Upload> mUploads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUploads = new ArrayList<>();


        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    SharedPreferences sp2 = getApplicationContext().getSharedPreferences("Mail",Context.MODE_PRIVATE);
                    String s = sp2.getString("MailId","");
                    SharedPreferences sp3 = getApplicationContext().getSharedPreferences("Login",Context.MODE_PRIVATE);
                    String p = sp3.getString("EmailIDOfUser","");
                    if(s.equals(p)){
                        Upload upload = postSnapshot.getValue(Upload.class);
                        mUploads.add(upload);
                        Toast.makeText(Gallery.this, "Image reatrieved successfully", Toast.LENGTH_SHORT).show();
                    }
                }
                mAdapter = new ImageAdapter(Gallery.this, mUploads);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Gallery.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void username(){
        user = fAuth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference().child("uploads").child(user.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Username = snapshot.child("mUser").getValue().toString();
                SharedPreferences sp = getSharedPreferences("Mail", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("MailId",Username);
                editor.commit();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}