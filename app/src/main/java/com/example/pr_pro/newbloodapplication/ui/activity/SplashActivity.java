package com.example.pr_pro.newbloodapplication.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pr_pro.newbloodapplication.R;
import com.example.pr_pro.newbloodapplication.ui.fragment.welcomescreen.SplashFragment;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SplashFragment splashFragment = new SplashFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.frame_splash,splashFragment).commit();

    }
}
