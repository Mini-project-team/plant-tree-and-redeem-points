package com.example.paid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity  {

    TextView register;
    TextView reset;
    Button login;
    EditText username , password;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Signup.class));
            }
        });

        login = (Button) findViewById(R.id.logbutton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userlogin();
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        username = (EditText) findViewById(R.id.loginID);
        password = (EditText) findViewById(R.id.loginpassword);

        progressBar = findViewById(R.id.progressBar2);
        mAuth = FirebaseAuth.getInstance();

        reset = (TextView) findViewById(R.id.forgot);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login.this, "You have clicked on Reset password", Toast.LENGTH_SHORT).show();
                final EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password");
                passwordResetDialog.setMessage("Enter your mail to receive the reset link:");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail = resetMail.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this,"Reset Link sent to Mail", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this,"Reset Link not sent to Mail"+ e.getMessage() , Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
            }
        });
    }

    private void userlogin() {
        String mail = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if(mail.isEmpty()){
            username.setError("This is compulsory");
            username.requestFocus();
            return;
        }

        if(pass.isEmpty()){
            password.setError("This is compulsory");
            password.requestFocus();
            return;
        }

        if(pass.length()<8){
            password.setError("Enter correct password");
            password.requestFocus();
            return;
        }

        sp = getSharedPreferences("Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("EmailIDofUser",mail);
        editor.apply();

        mAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Login.this,"Logged In successfully" , Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Login.this,Home.class));
                }else{
                    System.out.println("Login Failed");
                }
            }
        });
    }

}