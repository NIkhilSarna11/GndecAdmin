package com.birjot.gndec_sports_admin.Activities;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.os.Handler;

import com.birjot.gndec_sports_admin.Activities.AddNews;
import com.birjot.gndec_sports_admin.Fragments.Games;
import com.birjot.gndec_sports_admin.Fragments.GraphFragment;
import com.birjot.gndec_sports_admin.Fragments.Records;
import com.birjot.gndec_sports_admin.Fragments.intro1;
import com.birjot.gndec_sports_admin.Fragments.introduction;
import com.birjot.gndec_sports_admin.Fragments.lookforLastestnews;
import com.birjot.gndec_sports_admin.Fragments.posts;
import com.birjot.gndec_sports_admin.R;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;

public class HomeActivity extends Progressdialog
        implements NavigationView.OnNavigationItemSelectedListener {



    boolean doubleBackToExitPressedOnce = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle("Menu 1");
        setSupportActionBar(toolbar);

        displaySelectedScreen(R.id.introduction);
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {

        finish();
        System.exit(0);
        super.onDestroy();

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
       /* if (id == R.id.action_settings) {
            return true;
        }

        else*/ if (id == R.id.action_logout) {


            showProgressDialog();
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(HomeActivity.this, SigninActivity.class));
            return true;
        }

        displaySelectedScreensecond(item.getItemId());
        return super.onOptionsItemSelected(item);
    }



    private void displaySelectedScreensecond(int itemId) {

        //https://www.simplifiedcoding.net/android-navigation-drawer-example-using-fragments/
        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.addpost:
                fragment = new intro1();
                break;
            case R.id.addnews:
                Intent intent = new Intent(HomeActivity.this, AddNews.class);
                startActivity(intent);
                break;
          /*  case R.id.nav_menu3:
                fragment = new Menu3();
                break;*/
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

    }





    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       /* if (id == R.id.intro) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else*/ if (id == R.id.nav_share) {

            /*item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {*/
                    ApplicationInfo applicationInfo = getApplicationContext().getApplicationInfo();
                    String apkPath = applicationInfo.sourceDir;
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("application/vnd.android.package-archieve");
                    intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkPath)));
                    startActivity(Intent.createChooser(intent, "Share App Using"));

                 /*   return false;
                }*/
            /*});*/

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
        else if(id==R.id.nav_map){
            String url = "https://lab.gdy.club/#map=19/8444713.27/3614567.83/0";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
            return true;
        }


        else {  //calling the method displayselectedscreen and passing the id of selected menu
            displaySelectedScreen(item.getItemId());
            //make this method blank
            return true;}

        /*else if (id == R.id.nav_send) {

        }*/
    }


    private void displaySelectedScreen(int itemId) {

        //https://www.simplifiedcoding.net/android-navigation-drawer-example-using-fragments/
        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.introduction:
                fragment = new introduction();
                break;
            case R.id.viewposts:
                fragment = new posts();
                break;
            case R.id.nav_facilities:
                fragment = new Games();
                break;
            case R.id.nav_records:
                fragment = new Records();
                break;
            case R.id.latestnews:
                fragment = new lookforLastestnews();
                break;
            case R.id.nav_madeby:
                Intent intent = new Intent(HomeActivity.this,Developers.class);
                startActivity(intent);
                break;

            case R.id.nav_graph:
                fragment = new GraphFragment();
               /* Intent intent = new Intent(HomeActivity.this,Graphs.class);
                startActivity(intent);*/
                break;


        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

}