package com.example.qasimnawaz.campusrecruitmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qasimnawaz.campusrecruitmentsystem.Activities.StudentListActivity;
import com.example.qasimnawaz.campusrecruitmentsystem.Modeules.CompanyModule;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Company_Login extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
    String uid;

    FirebaseAuth.AuthStateListener mAuthListener;
    TextView getEmail, getPassword;
    Button loginButton, signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company__login);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();

        signUpButton = (Button) findViewById(R.id.cmp_signUp_button);
        loginButton = (Button) findViewById(R.id.cmp_signIn_button);
        getEmail = (TextView) findViewById(R.id.cmp_email_textView);
        getPassword = (TextView) findViewById(R.id.cmp_password_textView);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Company_Login.this, Company_SignUp.class);
                startActivity(intent);
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mail = getEmail.getText().toString();
                final String pass = getPassword.getText().toString();
//                uid = mAuth.getCurrentUser().getUid();


                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                myRef.child("CompaniesList").child(uid).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        if (dataSnapshot != null){
//                            mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(Company_Login.this, new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<AuthResult> task) {
////                                    Log.d("TAG", "Current User: "+dataSnapshot);
//                                    Intent intent = new Intent(Company_Login.this, StudentListActivity.class);
//                                    startActivity(intent);
//                                }
//                            });
//                        }else {
//                            Toast.makeText(Company_Login.this, "User not found OR not verified", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });

                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(Company_Login.this, new OnCompleteListener<AuthResult>() {
//
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        FirebaseUser user = task.getResult().getUser();
//                        String uid2 = user.getUid();
//                        if (task.isSuccessful()){
//                            myRef.child("CompaniesList").child(uid2).addListenerForSingleValueEvent(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(DataSnapshot dataSnapshot) {
//                                    if (dataSnapshot != null){
//                                        Toast.makeText(Company_Login.this, "Successfull",Toast.LENGTH_SHORT).show();
//                                    }else {
//                                        Toast.makeText(Company_Login.this, "User not found", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//
//                                @Override
//                                public void onCancelled(DatabaseError databaseError) {
//                                    Toast.makeText(Company_Login.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        }
//                        else {
//                            Toast.makeText(Company_Login.this, "Failed to create user: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });


                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(Company_Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            FirebaseUser user = task.getResult().getUser();
                            String uid2 = user.getUid();
                            myRef.child("CompaniesList").child(uid2).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    CompanyModule value = dataSnapshot.getValue(CompanyModule.class);
                                    if (value != null) {
                                        Intent intent = new Intent(Company_Login.this, StudentListActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(Company_Login.this, "User not found or verified", Toast.LENGTH_SHORT).show();
                                    }
//
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Toast.makeText(Company_Login.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Toast.makeText(Company_Login.this, "" + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                }).addOnFailureListener(Company_Login.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Company_Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
