package com.example.qasimnawaz.campusrecruitmentsystem;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.qasimnawaz.campusrecruitmentsystem.Adapter.CompanyListAdapter;
import com.example.qasimnawaz.campusrecruitmentsystem.Modeules.CompanyModule;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CompanyListActivity extends Activity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    FirebaseAuth.AuthStateListener mAuthListener;
    ArrayList<CompanyModule> dataModels;
    ListView listView;
    private static CompanyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();

        listView = (ListView) findViewById(R.id.list);
        dataModels = new ArrayList<>();
        myRef.child("CompaniesList").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CompanyModule module = dataSnapshot.getValue(CompanyModule.class);
                dataModels.add(new CompanyModule(module.getUuid(), module.getCmpName(), module.getSmpEstablish(),
                        module.getCmpEmail(), module.getCmpContact(), module.getCmpHR(), module.getUsrName()));
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

        adapter = new CompanyListAdapter(dataModels, CompanyListActivity.this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CompanyModule dataModel = dataModels.get(position);
                Snackbar.make(view, dataModel.getCmpName() + " " + dataModel.getSmpEstablish() + " / " + dataModel.getCmpEmail() + "\n" + dataModel.getCmpHR(), Snackbar.LENGTH_LONG)
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
