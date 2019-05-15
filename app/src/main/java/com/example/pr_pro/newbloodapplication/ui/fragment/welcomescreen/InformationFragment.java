package com.example.pr_pro.newbloodapplication.ui.fragment.welcomescreen;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chabbal.slidingdotsplash.SlidingSplashView;
import com.example.pr_pro.newbloodapplication.R;
import com.example.pr_pro.newbloodapplication.ui.activity.LoginActivity;


public class InformationFragment extends Fragment  implements ViewPager.OnPageChangeListener {

Button button;
    public InformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_information, container, false);


        SlidingSplashView splashView = (SlidingSplashView) view.findViewById(R.id.splash_Sliding);
        splashView.addOnPageChangeListener(this);
        button = (Button)view.findViewById(R.id.Skeep_id_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
       return view;
    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
