package com.example.authenticationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    ImageView imageView;
    EditText name,email,phno,password;
    Button signup_btn;
    FirebaseAuth mAuth;
    DAOUser dao = new DAOUser();
    int auth_flag=0,record_flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        imageView=findViewById(R.id.image);
        String path = "android.resource://"+getPackageName()+"/"+R.raw.logo_small;
        Uri uri =Uri.parse(path);
        imageView.setImageURI(uri);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        phno=findViewById(R.id.phno);
        password=findViewById(R.id.password);
        signup_btn=findViewById(R.id.signup_btn);
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_SHORT).show();
                            auth_flag=1;
                            User user = new User(name.getText().toString(),email.getText().toString(),phno.getText().toString(),password.getText().toString());
                            dao.add(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext(),"Record Added. Continue to Login!",Toast.LENGTH_SHORT).show();
                                    record_flag=1;
                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(),"Record Creation Failed",Toast.LENGTH_SHORT).show();
                                    Log.e("Authentication", "Exception", e);
                                    return;
                                }
                            });
                            //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            //startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Retry", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
            }
        });
    }
}