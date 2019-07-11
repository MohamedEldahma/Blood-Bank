package com.example.pr_pro.newbloodapplication.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pr_pro.newbloodapplication.R;

import com.example.pr_pro.newbloodapplication.helper.HelpeFragmentMethod;
import com.example.pr_pro.newbloodapplication.ui.fragment.loginscreen.LoginFragment;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment loginFragment = new LoginFragment();

        HelpeFragmentMethod.replaceFrag(loginFragment,getSupportFragmentManager(),R.id.fram_login_activity);


    }
}
