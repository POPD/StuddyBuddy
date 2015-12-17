package com.cs616.studybuddy_mockup.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cs616.studybuddy_mockup.CreateActivity;
import com.cs616.studybuddy_mockup.LoginActivity;

/**
 * Created by 1062886 on 2015-12-10.
 */
public class TabLoginAdapter extends FragmentPagerAdapter {

    public TabLoginAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new LoginActivity();
            case 1:
                // Games fragment activity
                return new CreateActivity();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }

}
