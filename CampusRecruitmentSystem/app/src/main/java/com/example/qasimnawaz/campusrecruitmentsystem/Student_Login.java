package com.example.qasimnawaz.campusrecruitmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qasimnawaz.campusrecruitmentsystem.Activities.CompanyListActivity;
import com.example.qasimnawaz.campusrecruitmentsystem.Modeules.StudentModule;
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

public class Student_Login extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    FirebaseAuth.AuthStateListener mAuthListener;
    TextView getEmail, getPassword;
    Button loginButton, signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__login);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();

        signUpButton = (Button) findViewById(R.id.std_signUp_button);
        loginButton = (Button) findViewById(R.id.std_login_button);
        getEmail = (TextView) findViewById(R.id.std_email_textView);
        getPassword = (TextView) findViewById(R.id.std_password_textView);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Student_Login.this, Student_SignUp.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = getEmail.getText().toString();
                String pass = getPassword.getText().toString();
                mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(Student_Login.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = task.getResult().getUser();
                            String uid2 = user.getUid();
                            myRef.child("StudentsList").child(uid2).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Log.d("TAG", "ABC" + dataSnapshot);
                                    StudentModule value = dataSnapshot.getValue(StudentModule.class);
                                    if (value != null) {
                                        Intent intent = new Intent(Student_Login.this, CompanyListActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(Student_Login.this, "User not found or verified", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Toast.makeText(Student_Login.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Toast.makeText(Student_Login.this, "" + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                }).addOnFailureListener(Student_Login.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Student_Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
