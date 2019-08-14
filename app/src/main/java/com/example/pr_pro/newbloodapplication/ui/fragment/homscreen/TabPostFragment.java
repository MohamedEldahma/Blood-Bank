package com.example.pr_pro.newbloodapplication.ui.fragment.homscreen;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pr_pro.newbloodapplication.R;
import com.example.pr_pro.newbloodapplication.ui.fragment.homscreen.HomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabPostFragment extends Fragment {


    @BindView(R.id.lay_tab_out)
    TabLayout layTabOut;
    @BindView(R.id.view_pager_id_home)
    ViewPager viewPagerIdHome;
    Unbinder unbinder;

    public TabPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_post, container, false);
        unbinder = ButterKnife.bind(this, view);
        tabHomFragment();


        return view;
    }
    private void tabHomFragment() {
       HomeFragment homeFragment = new HomeFragment(getFragmentManager());
        viewPagerIdHome.setAdapter(homeFragment);
        layTabOut.setupWithViewPager(viewPagerIdHome);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
