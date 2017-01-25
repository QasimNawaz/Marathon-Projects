package com.example.qasimnawaz.campusrecruitmentsystem.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qasimnawaz.campusrecruitmentsystem.Modeules.CompanyModule;
import com.example.qasimnawaz.campusrecruitmentsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Qasim Nawaz on 1/24/2017.
 */

public class CompanyAdapter extends ArrayAdapter<CompanyModule> implements View.OnClickListener {

    private ArrayList<CompanyModule> dataSet;
    Context mContext;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    FirebaseAuth.AuthStateListener mAuthListener;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtDescription;
        ImageView approve;
        ImageView remove;
    }

    public CompanyAdapter(ArrayList<CompanyModule> data, Context context) {
        super(context, R.layout.list_item, data);
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public void onClick(View v) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();

        int position = (Integer) v.getTag();
        CompanyModule dataModel = getItem(position);
//        StudentModule dataModel=(StudentModule) object;

        switch (v.getId()) {
            case R.id.list_item_done:
                Snackbar.make(v, dataModel.getCmpName() + " " + dataModel.getSmpEstablish() + " has been approved", Snackbar.LENGTH_LONG)
                        .setAction("No Action", null).show();

                String uid = dataModel.getUuid().toString();
                myRef.child("CompaniesList").child(uid).setValue(dataModel);
                myRef.child("AdminDashboard").child("CompanyPending").child(uid).removeValue();
                int deletePos = Integer.parseInt(v.getTag().toString());
                dataSet.remove(deletePos);
                break;
            case R.id.list_item_delete:
                Snackbar.make(v, dataModel.getCmpName() + " " + dataModel.getSmpEstablish() + " has been deleted", Snackbar.LENGTH_LONG)
                        .setAction("No Action", null).show();
                String uid2 = dataModel.getUuid().toString();
                myRef.child("AdminDashboard").child("CompanyPending").child(uid2).removeValue();
                int deletPos2 = Integer.parseInt(v.getTag().toString());
                dataSet.remove(deletPos2);
                break;
        }
    }

    private int lastPosition = -1;

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CompanyModule dataModel = getItem(position);
        ViewHolder viewHolder;
        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.list_item_name);
            viewHolder.txtDescription = (TextView) convertView.findViewById(R.id.list_item_type);
            viewHolder.approve = (ImageView) convertView.findViewById(R.id.list_item_done);
            viewHolder.remove = (ImageView) convertView.findViewById(R.id.list_item_delete);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtName.setText(dataModel.getCmpName());
        viewHolder.txtDescription.setText(dataModel.getCmpEmail());
        viewHolder.approve.setOnClickListener(this);
        viewHolder.approve.setTag(position);
        viewHolder.remove.setOnClickListener(this);
        viewHolder.remove.setTag(position);
        return convertView;
    }
}
