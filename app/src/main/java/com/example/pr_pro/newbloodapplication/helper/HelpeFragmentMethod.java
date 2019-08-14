package com.example.pr_pro.newbloodapplication.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;
import android.widget.Toast;


public class HelpeFragmentMethod {
    public static void replaceFrag(Fragment fragment, FragmentManager supportFragmentManager, int id) {
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void replaceFragId(Fragment fragment, FragmentManager supportFragmentManager, int id,String v) {
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.replace(id, fragment,v);
        transaction.addToBackStack(null);
        transaction.commit();
    }




    public static void tosatShow(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
}

    public static void intentWithPhone(Context context, String phone) {
        Intent in = new Intent(Intent.ACTION_DIAL);
        in.setData(Uri.parse("tel:" + phone));
        context.startActivity(in);

    }

    public static void intentWithExtra(Context context, Class<?> cls, String extraKey, String extraValue) {
        Intent in = new Intent(context, cls);
        in.putExtra(extraKey, extraValue);
        context.startActivity(in);

    }



}
