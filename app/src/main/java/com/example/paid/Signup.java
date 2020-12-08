package com.example.paid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.LENGTH_LONG;

public class Signup extends AppCompatActivity  {

    public static final String TAG = "TAG";
    EditText mName, mEmail, mPassword;
    Button mRegisterBtn;
    TextView mLoginBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    SharedPreferences sp;
    String userID;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup);

            mName = findViewById(R.id.name);
            mEmail = findViewById(R.id.Email);
            mPassword = findViewById(R.id.Password);
            mLoginBtn = findViewById(R.id.relogin);
            mRegisterBtn = findViewById(R.id.signup);

            fAuth = FirebaseAuth.getInstance();
            fstore = FirebaseFirestore.getInstance();
            progressBar = findViewById(R.id.progressBar);


            /*if(fAuth.getCurrentUser() != null){
                startActivity(new Intent(getApplicationContext(), Home.class));
                finish();
            }*/

            mRegisterBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String email = mEmail.getText().toString().trim();
                    String password = mPassword.getText().toString().trim();
                    final String name = mName.getText().toString().trim();

                    if(TextUtils.isEmpty(email)){
                        mEmail.setError("This is compulsory");
                        return;
                    }
                    if(TextUtils.isEmpty(password)){
                        mPassword.setError("This is required");
                        return;
                    }

                    if(password.length()<6){
                        mPassword.setError("This is required");
                        return;
                    }
                    sp = getSharedPreferences("Profile", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("Username",name);
                    editor.putString("Email",email);
                    editor.commit();

                    progressBar.setVisibility(View.VISIBLE);

                    fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Signup.this,  "Registration Done" , LENGTH_LONG).show();
                                userID = fAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fstore.collection("users").document(userID);
                                Map<String, Object> user = new HashMap<>();
                                user.put("Name Of User", name);
                                user.put("Email Address",email);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG,"onSuccess: User Profile is created for "+ userID);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailure: " + e.toString());
                                    }
                                });
                                startActivity(new Intent(getApplicationContext(), Home.class));

                            }else{
                                Toast.makeText(Signup.this,  "Registration not Done" + task.getException().getMessage() , LENGTH_LONG).show();
                            }
                        }
                    });
                }
            });
    }
}


