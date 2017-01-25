package com.example.qasimnawaz.campusrecruitmentsystem.Adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.qasimnawaz.campusrecruitmentsystem.Modeules.StudentModule;
import com.example.qasimnawaz.campusrecruitmentsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Qasim Nawaz on 1/24/2017.
 */

public class StudentListAdapter extends ArrayAdapter<StudentModule> implements View.OnClickListener{
    private ArrayList<StudentModule> dataSet;
    Context mContext;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    FirebaseAuth.AuthStateListener mAuthListener;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtEmail;
        TextView txtType;
        TextView txtVersion;
    }

    public StudentListAdapter(ArrayList<StudentModule> data, Context context) {
        super(context, R.layout.row_items, data);
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public void onClick(View v) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        mAuth = FirebaseAuth.getInstance();

        int position=(Integer) v.getTag();
        StudentModule dataModel= getItem(position);

        switch (v.getId())
        {
            case R.id.row_item_info:
                Snackbar.make(v, dataModel.getFirstName(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;

        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        StudentModule dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_items, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.row_item_name);
            viewHolder.txtEmail = (TextView) convertView.findViewById(R.id.row_item_email);
            viewHolder.txtType = (TextView) convertView.findViewById(R.id.row_item_info);
            viewHolder.txtVersion = (TextView) convertView.findViewById(R.id.row_item_version_heading);
//            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
//            viewHolder.txtType = (TextView) convertView.findViewById(R.id.type);
//            viewHolder.txtVersion = (TextView) convertView.findViewById(R.id.version_number);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

//        viewHolder.txtName.setText(dataModel.getName());
//        viewHolder.txtType.setText(dataModel.getType());
//        viewHolder.txtVersion.setText(dataModel.getVersion_number());
//        viewHolder.info.setOnClickListener(this);
//        viewHolder.info.setTag(position);
        viewHolder.txtName.setText(dataModel.getFirstName() + " " + dataModel.getLastName());
        viewHolder.txtEmail.setText(dataModel.getEmail());
        viewHolder.txtVersion.setText(dataModel.getGender());
        viewHolder.txtType.setText(dataModel.getDepartment());
        // Return the completed view to render on screen
        return convertView;
    }
}
