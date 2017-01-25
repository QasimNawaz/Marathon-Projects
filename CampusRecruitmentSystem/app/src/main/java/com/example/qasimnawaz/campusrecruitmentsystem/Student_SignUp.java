package com.example.qasimnawaz.campusrecruitmentsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.qasimnawaz.campusrecruitmentsystem.Modeules.StudentModule;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Student_SignUp extends AppCompatActivity {

//    FirebaseDatabase database;
//    DatabaseReference myRef;
//    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    FirebaseAuth.AuthStateListener mAuthListener;

    StudentModule module;
    EditText fname, lname, email, cntactNo, address, ssc, fsc, university, password, cunPass;
    Button std_signUp;
    CheckBox mchkBX, fchkBX;

    String gend;
    String sscYear;
    String fcsYear;
    String departString;

    Spinner departmentSpinner, sscSpinner, fscSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__sign_up);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();

        fname = (EditText) findViewById(R.id.std_first_name);
        lname = (EditText) findViewById(R.id.std_last_name);
        email = (EditText) findViewById(R.id.std_get_email);
        cntactNo = (EditText) findViewById(R.id.std_get_no);
        address = (EditText) findViewById(R.id.std_get_address);
        ssc = (EditText) findViewById(R.id.std_get_SSC);
        fsc = (EditText) findViewById(R.id.std_get_FSC);
        university = (EditText) findViewById(R.id.std_get_uni);
        password = (EditText) findViewById(R.id.std_get_password);
        cunPass = (EditText) findViewById(R.id.std_get_cunformPassword);
        std_signUp = (Button) findViewById(R.id.student_signUp_button);
        mchkBX = (CheckBox) findViewById(R.id.maleChkBox);
        fchkBX = (CheckBox) findViewById(R.id.femaleChkBox);

        std_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String stdFname = fname.getText().toString();
                final String stdLname = lname.getText().toString();
                final String stdEmail = email.getText().toString();
                final String stdContact = cntactNo.getText().toString();
                final String stdAddress = address.getText().toString();
                final String stdSSC = ssc.getText().toString();
                final String stdFSC = fsc.getText().toString();
                final String stdUNI = university.getText().toString();
                String pass = password.getText().toString();
                String cunPassword = cunPass.getText().toString();
                if (mchkBX.isChecked()){
                    gend = "Male";
                }else {
                    gend = "Female";
                }

                if (pass.equals(cunPassword)){
                    mAuth.createUserWithEmailAndPassword(stdEmail, pass).addOnCompleteListener(Student_SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Student_SignUp.this, "Successfull", Toast.LENGTH_SHORT).show();
                                String uid = mAuth.getCurrentUser().getUid().toString();
//                                module = new StudentModule(uid, stdFname, stdLname, gend, stdEmail, stdContact, stdAddress, stdFSC, fcsYear, stdSSC, sscYear, stdUNI, departString);
                                module = new StudentModule(uid,stdFname, stdLname,gend,stdEmail,stdContact,stdAddress,stdFSC, fcsYear, stdSSC, sscYear, stdUNI, departString);

                                myRef.child("AdminDashboard").child("StudentPending").child(uid).setValue(module);
                                Intent intent = new Intent(Student_SignUp.this, Student_Login.class);
                                startActivity(intent);

                            }else {
                                Toast.makeText(Student_SignUp.this, "Data Conflict", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(Student_SignUp.this, "password didn't match", Toast.LENGTH_SHORT).show();
                }

            }
        });

        departmentSpinner = (Spinner) findViewById(R.id.dptSpinner);
        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG", "Depart"+parent.getItemAtPosition(position).toString());
                departString = parent.getItemAtPosition(position).toString();
//                departString = String.valueOf(parent.getItemIdAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sscSpinner = (Spinner) findViewById(R.id.sscSpinner);
        sscSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG", "SSC"+parent.getItemAtPosition(position).toString());
                sscYear = String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fscSpinner = (Spinner) findViewById(R.id.fscSpinner);
        fscSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG", "FSC"+parent.getItemAtPosition(position).toString());
                fcsYear = String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
