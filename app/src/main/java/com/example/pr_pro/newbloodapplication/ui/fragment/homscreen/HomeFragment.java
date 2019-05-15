package com.example.pr_pro.newbloodapplication.ui.fragment.homscreen;


//import android.os.Bundle;
//import android.support.design.widget.TabLayout;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

//import android.support.v4.view.ViewPager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import com.example.pr_pro.newbloodapplication.adapter.SplashViewPagerAdapter;
//
//import com.example.pr_pro.newbloodapplication.R;





/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends FragmentPagerAdapter {

    public HomeFragment(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch (pos) {
            case 0:
                return new ArticalPostsFragment();
            case 1:
                return new DonationRequestFragment();
            default:
                return null;


        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "المقالات";
            case 1:
                return "طلبات التبرع";
        }
        return super.getPageTitle(position);
    }








//  TabLayout tabLayout;
//  ViewPager viewPager;
//
//
//    public HomeFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_home, container, false);
//
//        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_id_hom);
//        viewPager = (ViewPager)view.findViewById(R.id.view_pager_id_home);
//
//     SplashViewPagerAdapter adapter = new SplashViewPagerAdapter(getFragmentManager());
//        adapter.addFragment(new ArticalPostsFragment(),"المقالات");
//        adapter.addFragment(new DonationRequestFragment(),"طلبات التبرع");
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
//
//        return view;
//    }

}
