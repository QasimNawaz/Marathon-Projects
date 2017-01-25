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

import com.example.qasimnawaz.campusrecruitmentsystem.Modeules.StudentModule;
import com.example.qasimnawaz.campusrecruitmentsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Qasim Nawaz on 1/23/2017.
 */

public class StudentAdapter extends ArrayAdapter<StudentModule> implements View.OnClickListener {

    private ArrayList<StudentModule> dataSet;
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

    public StudentAdapter(ArrayList<StudentModule> data, Context context) {
        super(context, R.layout.list_item, data);
        this.dataSet = data;
        this.mContext = context;

    }

//    @Override
//    public void remove(StudentModule object) {
//        super.remove(object);
//        notifyDataSetChanged();
//    }

    @Override
    public void onClick(View v) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();

        int position = (Integer) v.getTag();
        StudentModule dataModel = getItem(position);
//        StudentModule dataModel=(StudentModule) object;

        switch (v.getId()) {
            case R.id.list_item_done:
                Snackbar.make(v, dataModel.getFirstName() + " " + dataModel.getLastName() + " has been approved", Snackbar.LENGTH_LONG)
                        .setAction("No Action", null).show();

                String uid = dataModel.getUuid().toString();
                myRef.child("StudentsList").child(uid).setValue(dataModel);
                myRef.child("AdminDashboard").child("StudentPending").child(uid).removeValue();
                int deletePos = Integer.parseInt(v.getTag().toString());
                dataSet.remove(deletePos);
                break;
            case R.id.list_item_delete:
                Snackbar.make(v, dataModel.getFirstName()+" "+dataModel.getLastName()+" has been deleted", Snackbar.LENGTH_LONG)
                        .setAction("No Action", null).show();
                String uid2 = dataModel.getUuid().toString();
                myRef.child("AdminDashboard").child("StudentPending").child(uid2).removeValue();
                int deletPos2 = Integer.parseInt(v.getTag().toString());
                dataSet.remove(deletPos2);
                break;
        }
    }

    private int lastPosition = -1;


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StudentModule dataModel = getItem(position);
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

        viewHolder.txtName.setText(dataModel.getFirstName() + dataModel.getLastName());
        viewHolder.txtDescription.setText(dataModel.getDepartment());
        viewHolder.approve.setOnClickListener(this);
        viewHolder.approve.setTag(position);
        viewHolder.remove.setOnClickListener(this);
        viewHolder.remove.setTag(position);
        return convertView;
    }


}
