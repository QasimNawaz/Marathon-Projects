package com.example.qasimnawaz.campusrecruitmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminLogin extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
    private String adminEmail;
    private String adminPassword;

    FirebaseAuth.AuthStateListener mAuthListener;

    private EditText email, password;
    private Button signIn;
    private Button signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        adminEmail = "qasim@gmail.com";
        adminPassword = "1234567";
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();
        signIn = (Button) findViewById(R.id.adminButtonSignIn);
        signUp = (Button) findViewById(R.id.adminButtonSignUp);

        email = (EditText) findViewById(R.id.adminEmailSignIn);
        password = (EditText) findViewById(R.id.adminPasswordSignIn);
//        login = (Button) findViewById(R.id.adminButtonSignIn);
//        signup = (Button) findViewById(R.id.adminButtonSignUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLogin.this, AdminSignUp.class);
                startActivity(intent);
            }
        });
//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AdminLogin.this, AdminSignUp.class);
//                startActivity(intent);
//            }
//        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString();
                String pass = password.getText().toString();
//                if (mail == adminEmail){
                    mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(AdminLogin.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(AdminLogin.this, "Hurrrey", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(AdminLogin.this, AdminPanel.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(AdminLogin.this, ""+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
//                }else {
//                    Toast.makeText(AdminLogin.this, "User not found", Toast.LENGTH_LONG).show();
//                }
            }
        });
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String mail = email.getText().toString();
//                String pass = password.getText().toString();
//                if (adminEmail == mail) {
//
//                    mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(AdminLogin.this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                Intent intent = new Intent(AdminLogin.this, AdminPanel.class);
//                                startActivity(intent);
//                            } else {
//                                Toast.makeText(AdminLogin.this, "Data Conflict", Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//                    });
//                }else {
//                    Toast.makeText(AdminLogin.this, "User not found", Toast.LENGTH_LONG);
//                }
//            }
//        });

    }


}
