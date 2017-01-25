package com.example.qasimnawaz.campusrecruitmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qasimnawaz.campusrecruitmentsystem.Modeules.CompanyModule;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Company_SignUp extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    FirebaseAuth.AuthStateListener mAuthListener;

    EditText cmpName, cmpEstablish, cmpEmail, cmpContact, cmpHr, cmpUser, cmpPass, cmpCunPass;
    CompanyModule module;
    Button signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company__sign_up);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();

        cmpName = (EditText) findViewById(R.id.cmp_name);
        cmpEstablish = (EditText) findViewById(R.id.cmp_established);
        cmpEmail = (EditText) findViewById(R.id.cmp_email);
        cmpContact = (EditText) findViewById(R.id.cmp_contact);
        cmpHr = (EditText) findViewById(R.id.cmp_HR);
        cmpUser = (EditText) findViewById(R.id.cmp_userName);
        cmpPass = (EditText) findViewById(R.id.cmp_password);
        cmpCunPass = (EditText) findViewById(R.id.cmp_cunform_password);
        signUp = (Button) findViewById(R.id.cmp_signUp_button2);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = cmpName.getText().toString();
                final String establish = cmpEstablish.getText().toString();
                final String email = cmpEmail.getText().toString();
                final String contact = cmpContact.getText().toString();
                final String hr = cmpHr.getText().toString();
                final String user = cmpUser.getText().toString();
                String pass = cmpPass.getText().toString();
                String cunPass = cmpCunPass.getText().toString();
//                if (!pass.equals(cunPass)){
//                    cmpCunPass.setError("Plz cunform Password");
//                }

                if (pass.equals(cunPass)) {
                    mAuth.createUserWithEmailAndPassword(email, cunPass).addOnCompleteListener(Company_SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Company_SignUp.this, "Successfull", Toast.LENGTH_SHORT).show();
                                String uid = mAuth.getCurrentUser().getUid().toString();
                                module = new CompanyModule(uid,name,establish,email,contact,hr,user);
                                myRef.child("AdminDashboard").child("CompanyPending").child(uid).setValue(module);
                                Intent intent = new Intent(Company_SignUp.this, Company_Login.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Company_SignUp.this, "Data Conflict", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(Company_SignUp.this, "password didn't match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
