package com.example.pr_pro.newbloodapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.pr_pro.newbloodapplication.R;
import com.example.pr_pro.newbloodapplication.ui.fragment.homscreen.TabPostFragment;
import com.example.pr_pro.newbloodapplication.helper.HelpeFragmentMethod;
import com.example.pr_pro.newbloodapplication.ui.fragment.donation.CreatRequestDonation;
import com.example.pr_pro.newbloodapplication.ui.fragment.homscreen.homnavigation.GetNotificationlistFragment;
import com.example.pr_pro.newbloodapplication.ui.fragment.homscreen.homnavigation.InformationNavigationFragment;
import com.example.pr_pro.newbloodapplication.ui.fragment.homscreen.homnavigation.AboutAppFragment;
import com.example.pr_pro.newbloodapplication.ui.fragment.homscreen.homnavigation.ContactUsFragment;
import com.example.pr_pro.newbloodapplication.ui.fragment.homscreen.homnavigation.FavoritFragment;
import com.example.pr_pro.newbloodapplication.ui.fragment.homscreen.homnavigation.InstractUserFragment;
import com.example.pr_pro.newbloodapplication.ui.fragment.homscreen.homnavigation.NotificationSetingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.pr_pro.newbloodapplication.helper.SharedPreferencesManger.SaveData;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.REMEMBER_USER;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.fram_home)
    FrameLayout framHome;
    @BindView(R.id.fab)
    FloatingActionButton fab;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton  fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreatRequestDonation creatRequestDonation =new CreatRequestDonation();
                HelpeFragmentMethod.replaceFrag(creatRequestDonation,getSupportFragmentManager(),R.id.fram_home);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//
        TabPostFragment tabPostFragment = new TabPostFragment();
        HelpeFragmentMethod.replaceFrag(tabPostFragment, getSupportFragmentManager(), R.id.fram_home);
        toolbarTitle.setText("الرئيسيه");

    }

//    private void tabHomFragment() {
//        HomeFragment homeAdapter = new HomeFragment(getSupportFragmentManager());
//        viewPagerIdHome.setAdapter(homeAdapter);
//        layTabOut.setupWithViewPager(viewPagerIdHome);
//
//
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_information) {
            InformationNavigationFragment informationNavigationFragment = new InformationNavigationFragment();
            HelpeFragmentMethod.replaceFrag(informationNavigationFragment, getSupportFragmentManager(), R.id.fram_home);
            toolbarTitle.setText("تعديل المعلومات");
            // Handle the camera action
        } else if (id == R.id.nav_siting_notifcation) {
//            NotificationSetingFragment notificationSetingFragment = new NotificationSetingFragment();
//            HelpeFragmentMethod.replaceFrag(notificationSetingFragment,getSupportFragmentManager(),R.id.fram_home);
            HelpeFragmentMethod.replaceFrag(new GetNotificationlistFragment(),getSupportFragmentManager(),R.id.fram_home);
            toolbarTitle.setText("اعدادت الاشعارات");

        } else if (id == R.id.nav_favorit) {
            FavoritFragment favoritFragment = new FavoritFragment();
            HelpeFragmentMethod.replaceFrag(favoritFragment,getSupportFragmentManager(),R.id.fram_home);
            toolbarTitle.setText("المفضله");
        } else if (id == R.id.nav_main) {
            TabPostFragment tabPostFragment = new TabPostFragment();
            HelpeFragmentMethod.replaceFrag(tabPostFragment,getSupportFragmentManager(),R.id.fram_home);
            toolbarTitle.setText("ألرئيسيه");

        } else if (id == R.id.nav_instruc_user) {
            InstractUserFragment instractUserFragment = new InstractUserFragment();
            HelpeFragmentMethod.replaceFrag(instractUserFragment,getSupportFragmentManager(),R.id.fram_home);
            toolbarTitle.setText("تعليمات الاستخدام");

        } else if (id == R.id.nav_contact_us) {
            ContactUsFragment contactUsFragment = new ContactUsFragment();
            HelpeFragmentMethod.replaceFrag(contactUsFragment,getSupportFragmentManager(),R.id.fram_home);
            toolbarTitle.setText("تواصل معنا");

        } else if (id == R.id.nav_about_app) {
            AboutAppFragment aboutAppFragment = new AboutAppFragment();
            HelpeFragmentMethod.replaceFrag(aboutAppFragment,getSupportFragmentManager(),R.id.fram_home);
            toolbarTitle.setText("عن التطبيق");

        } else if (id == R.id.nav_evluat_app) {



        } else if (id == R.id.nav_sgin_out) {
       SaveData(this, REMEMBER_USER, 0);
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
