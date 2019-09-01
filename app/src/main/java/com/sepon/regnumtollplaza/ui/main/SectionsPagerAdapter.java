package com.sepon.regnumtollplaza.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.sepon.regnumtollplaza.R;
import com.sepon.regnumtollplaza.fragment.Previous_fragment;
import com.sepon.regnumtollplaza.fragment.Today_Fragment;
import com.sepon.regnumtollplaza.fragment.chittagong.CtrlR_fragment;
import com.sepon.regnumtollplaza.fragment.chittagong.Regular_fragment;
import com.sepon.regnumtollplaza.fragment.chittagong.Today_Chittagong_fragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.cht1, R.string.cht2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position) {
            case 0:
                Today_Chittagong_fragment tab3 = new Today_Chittagong_fragment();
                return tab3;
            case 1:
                Regular_fragment tab1 = new Regular_fragment();
                return tab1;
            case 2:
                CtrlR_fragment tab2 = new CtrlR_fragment();
                return tab2;
            default:
                return null;
        }
        // return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}