package com.example.pr_pro.newbloodapplication.ui.fragment.welcomescreen;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pr_pro.newbloodapplication.R;
import com.example.pr_pro.newbloodapplication.helper.HelpeFragmentMethod;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {

    private final int SPLASH_DISPLAY_LENGTH = 3000;

    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_splash, container, false);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                InformationFragment informationFragment = new InformationFragment();

              HelpeFragmentMethod.replaceFrag(informationFragment,getActivity().getSupportFragmentManager(),R.id.frame_splash);
            }
        }, SPLASH_DISPLAY_LENGTH);

        return view;
    }

}
