package com.example.qasimnawaz.campusrecruitmentsystem.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.qasimnawaz.campusrecruitmentsystem.Fragments.CompanyFragment;
import com.example.qasimnawaz.campusrecruitmentsystem.Fragments.StudentFragment;

/**
 * Created by Qasim Nawaz on 1/23/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                CompanyFragment tab1 = new CompanyFragment();
                return tab1;
            case 1:
                StudentFragment tab2 = new StudentFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
