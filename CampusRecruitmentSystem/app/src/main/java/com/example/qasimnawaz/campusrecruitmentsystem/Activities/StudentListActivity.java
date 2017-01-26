package com.example.qasimnawaz.campusrecruitmentsystem.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.qasimnawaz.campusrecruitmentsystem.Adapter.StudentListAdapter;
import com.example.qasimnawaz.campusrecruitmentsystem.Modeules.StudentModule;
import com.example.qasimnawaz.campusrecruitmentsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class StudentListActivity extends Activity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    FirebaseAuth.AuthStateListener mAuthListener;
    ArrayList<StudentModule> dataModels;
    ListView listView;
    private static StudentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();

        listView = (ListView) findViewById(R.id.list);
        dataModels = new ArrayList<>();
        myRef.child("StudentsList").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                StudentModule module = dataSnapshot.getValue(StudentModule.class);
                dataModels.add(new StudentModule(module.getUuid(), module.getFirstName(), module.getLastName(),
                        module.getGender(), module.getEmail(), module.getContactNo(), module.getAddress(),
                        module.getFscPercent(), module.getFscYear(), module.getSscPercent(), module.getSscYear(),
                        module.getUniversity(), module.getDepartment()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        adapter = new StudentListAdapter(dataModels, StudentListActivity.this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudentModule dataModel = dataModels.get(position);
                Snackbar.make(view, dataModel.getFirstName() + " " + dataModel.getLastName() + " / " + dataModel.getDepartment() + "\n" + dataModel.getEmail(), Snackbar.LENGTH_LONG)
                        .setAction("No Action", null).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        adapter.notifyDataSetChanged();
    }
}
