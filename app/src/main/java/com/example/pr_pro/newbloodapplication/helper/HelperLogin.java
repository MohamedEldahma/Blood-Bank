package com.example.pr_pro.newbloodapplication.helper;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.example.pr_pro.newbloodapplication.R;

public class HelperLogin {

    private static final String TAG = HelperLogin.class.getSimpleName();

    public static void verbose(String message) {
        Log.v(TAG, message);

    }

    public static void debug(String message) {
        Log.d(TAG, message);

    }

    public static void error(String message) {
        Log.v(TAG, message);

    }

    public static boolean isNetworkConnected(Context context, View view) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isConnected()) {
            return true;
        } else {
            Snackbar.make(view, context.getString(R.string.no_internet), Snackbar.LENGTH_LONG).show();
            return false;

        }
    }





}
