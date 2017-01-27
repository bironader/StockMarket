//package com.example.biro.stockmarket.Adapters;
//
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//
//import java.util.ArrayList;
//
///**
// * Created by biro on 11/18/2016.
// */
//
//public class ViewPagerAdapter extends FragmentPagerAdapter {
//
//    private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
//    private final ArrayList<String> mFragmentTitleList = new ArrayList<>();
//
//
//    public ViewPagerAdapter(FragmentManager fm) {
//        super(fm);
//
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//        return mFragmentList.get(position);
//    }
//
//    @Override
//    public int getCount() {
//        return mFragmentList.size();
//    }
//
//
//    public void addFragment(Fragment fragment, String title) {
//        mFragmentList.add(fragment);
//        mFragmentTitleList.add(title);
//    }
//
//    public void replaceFragment(Fragment fragment, String title) {
//        mFragmentList.clear();
//        mFragmentTitleList.clear();
//        mFragmentList.add(fragment);
//        mFragmentTitleList.add(title);
//    }
//
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mFragmentTitleList.get(position);
//    }
//
//
//}
