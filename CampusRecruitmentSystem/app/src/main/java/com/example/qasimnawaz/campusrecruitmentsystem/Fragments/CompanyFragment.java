package com.example.qasimnawaz.campusrecruitmentsystem.Fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.qasimnawaz.campusrecruitmentsystem.Adapter.CompanyAdapter;
import com.example.qasimnawaz.campusrecruitmentsystem.Modeules.CompanyModule;
import com.example.qasimnawaz.campusrecruitmentsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    FirebaseAuth.AuthStateListener mAuthListener;
    ArrayList<CompanyModule> dataModels;
    ListView listView;
    private static CompanyAdapter adapter;

    public CompanyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();

        listView = (ListView) view.findViewById(R.id.list);
        dataModels = new ArrayList<>();

        myRef.child("AdminDashboard").child("CompanyPending").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CompanyModule module = dataSnapshot.getValue(CompanyModule.class);
                dataModels.add(new CompanyModule(module.getUuid(), module.getCmpName(), module.getSmpEstablish(),
                        module.getCmpEmail(), module.getCmpContact(), module.getCmpHR(), module.getUsrName()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        adapter = new CompanyAdapter(dataModels, getContext());
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
        return view;
    }

}
