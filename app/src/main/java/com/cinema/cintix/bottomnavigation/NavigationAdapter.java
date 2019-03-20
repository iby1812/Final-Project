package com.cinema.cintix.bottomnavigation;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class NavigationAdapter  extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public NavigationAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                QuickOrder quickOrder = new QuickOrder();
                return quickOrder;
            case 1:
                RegularOrder regularOrder = new RegularOrder();
                return regularOrder;
            case 2:
                SmartOrder smartOrder = new SmartOrder();
                return smartOrder;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
