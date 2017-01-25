package com.example.qasimnawaz.campusrecruitmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qasimnawaz.campusrecruitmentsystem.Modeules.AdminModule;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminSignUp extends AppCompatActivity {


//    FirebaseDatabase database;
//    DatabaseReference myRef;
//    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    FirebaseAuth.AuthStateListener mAuthListener;
    private EditText name, position, email, password, cunformPassword;
    private Button signUp;
    AdminModule module;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_up);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();
        name = (EditText) findViewById(R.id.admin_name_editText);
        position = (EditText) findViewById(R.id.admin_position_editText);
        email = (EditText) findViewById(R.id.admin_email_editText);
        password = (EditText) findViewById(R.id.admin_password_editText);
        cunformPassword = (EditText) findViewById(R.id.admin_cunform_editText);
        signUp = (Button) findViewById(R.id.admin_signUp_Button);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String adminName = name.getText().toString();
                final String adminPosition = position.getText().toString();
                final String adminEmail = email.getText().toString();
                String pass = password.getText().toString();
                String cunPass = cunformPassword.getText().toString();
                if (pass.equals(cunPass)){
                    mAuth.createUserWithEmailAndPassword(adminEmail, pass).addOnCompleteListener(AdminSignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(AdminSignUp.this, "Successfull", Toast.LENGTH_SHORT).show();
                                module = new AdminModule(adminName, adminPosition, adminEmail);
//                                String uid = mAuth.getCurrentUser().getUid().toString();
//                                myRef.child("AdminDashboard").child("AdminPanel").child(uid).setValue(module);
                                String uid = mAuth.getCurrentUser().getUid().toString();
                                myRef.child("AdminDashboard").child("AdminPanel").child(uid).setValue(module);
                                Intent intent = new Intent(AdminSignUp.this, AdminPanel.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(AdminSignUp.this, "Data Conflict", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(AdminSignUp.this, "password didn't match", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
