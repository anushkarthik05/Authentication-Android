package com.example.authenticationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    TextView signup_msg;
    EditText email,password;
    Button login_btn;
    FirebaseAuth mAuth;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        imageView=findViewById(R.id.image);
        String path = "android.resource://"+getPackageName()+"/"+R.raw.logo;
        Uri uri =Uri.parse(path);
        imageView.setImageURI(uri);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        signup_msg=findViewById(R.id.signup);
        signup_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Signup.class);
                startActivity(intent);
            }
        });
        login_btn=findViewById(R.id.login);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().length()==0||password.getText().toString().length()==0){
                    Toast.makeText(getApplicationContext(),"Fill all the fields.",Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            SharedPreferences sf=getSharedPreferences("myfile", Context.MODE_PRIVATE);
                            SharedPreferences.Editor edit=sf.edit();
                            edit.putString("email",email.getText().toString());
                            edit.apply();
                            Toast.makeText(getApplicationContext(), "Login successful!!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), HomePage.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                });
            }
        });

    }
}
