package com.aristocrat.ahmed.ateeq.ateeqbackendless;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aristocrat.ahmed.ateeq.ateeqbackendless.library.DatabaseHandler;
import com.aristocrat.ahmed.ateeq.ateeqbackendless.library.UserFunctions;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                   fragmentMain.OnFragmentInteractionListener,
                   fragmentLogin.OnFragmentInteractionListener,
                   fragmentBriyani.OnFragmentInteractionListener,
                   fragmentNoodles.OnFragmentInteractionListener,
                   fragmentQuantity.OnFragmentInteractionListener,
                   fragmentOrder.OnFragmentInteractionListener,
                   fragmentNav.OnFragmentInteractionListener,
                   fragmentRegister.OnFragmentInteractionListener
                    {
                        static String dish;
                        static String quantity;
                        static String cuname ;
                        static String cuemailid ;
                        static String pno;
                        static String ruser;
                        static String rpass;
                        static String rname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Fragment navview = new fragmentNav();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.nav_view, navview);
        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        UserFunctions userFunctions = new UserFunctions();
        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if(userFunctions.isUserLoggedIn(getBaseContext()))
        {

            navigationView.getMenu().setGroupVisible(R.id.logingroup, false);
            navigationView.getMenu().setGroupVisible(R.id.logoutgroup, true);
            HashMap<Integer,String> userdata = new HashMap<Integer,String>();
            userdata = db.getUserDetails();
            MainActivity.cuname = userdata.get(1);
            MainActivity.cuemailid = userdata.get(2);

        }
        else{

            navigationView.getMenu().setGroupVisible(R.id.logingroup, true);
            navigationView.getMenu().setGroupVisible(R.id.logoutgroup, false);
            MainActivity.cuname = "Guest";
            MainActivity.cuemailid = "guest@domain.com";

        }

        navigationView.setNavigationItemSelectedListener(this);

        Fragment main =  new fragmentMain();
        FragmentTransaction ft1 = getFragmentManager().beginTransaction();
        ft1.replace(R.id.mainFrame,main);
        ft1.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.login) {
            Fragment login = new fragmentLogin();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame,login);
            ft.commit();

        }
        else if (id == R.id.logout) {

            UserFunctions userFunctions = new UserFunctions();
            userFunctions.logoutUser(getBaseContext());

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.getMenu().setGroupVisible(R.id.logingroup, true);
            navigationView.getMenu().setGroupVisible(R.id.logoutgroup, false);
            MainActivity.cuname = "Guest";
            MainActivity.cuemailid = "guest@domain.com";

            Toast.makeText(getBaseContext(),"Logged Out!",Toast.LENGTH_LONG).show();

            Fragment nav = new fragmentNav();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.nav_view, nav);
            ft.commit();

            Fragment main = new fragmentLogin();
            FragmentTransaction ft1 = getFragmentManager().beginTransaction();
            ft1.replace(R.id.mainFrame, main);
            ft1.commit();


        }

        else if (id == R.id.briyani) {
            Fragment briyani = new fragmentBriyani();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame,briyani);
            ft.commit();

        } else if (id == R.id.noodles) {
            Fragment noodles = new fragmentNoodles();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame,noodles);
            ft.commit();

        } else if (id == R.id.visitusga || id == R.id.visitusgb) {
            startActivity(new Intent(MainActivity.this, activityMaps.class));

        }

        else if (id == R.id.fmain) {
            Fragment main =  new fragmentMain();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame,main);
            ft.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
