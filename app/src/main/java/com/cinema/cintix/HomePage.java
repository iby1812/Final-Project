package com.cinema.cintix;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.cinema.cintix.bottom_navigation.QuickOrder;
import com.cinema.cintix.bottom_navigation.RegularOrder;
import com.cinema.cintix.bottom_navigation.SmartOrder;
import com.cinema.cintix.data.Movie;
import com.cinema.cintix.data.UserData;
import com.cinema.cintix.fetch_movies_data.NetworkUtils;
import com.cinema.cintix.starting_app.LoginActivity;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TextView user;
    private DrawerLayout drawerLayout;
    private BottomNavigationView mnav;
    private FrameLayout frameLayout;
    private CircleImageView profilePictureView;
    private QuickOrder quickOrder = new QuickOrder();
    private RegularOrder regularOrder = new RegularOrder();
    private SmartOrder smartOrder = new SmartOrder();
    private ProgressBar progressBar;
    private  String popularMoviesURL;
    private static ArrayList<Movie> popularList;
    private static int page=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_activity);
        progressBar=findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);
        SetToolbar();
        SetBottomNavigator();
        SetFragment(regularOrder);
        new FetchMovies().execute();
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        closeDrawer();
        switch (menuItem.getItemId()) {
            case R.id.action1:
                //add new activity
                break;
            case R.id.action2:
                //add new activity
                break;
            case R.id.action3:
                //add new activity
                break;
            case R.id.log_out:
                LoginManager.getInstance().logOut();
                Intent login = new Intent(this, LoginActivity.class);
                startActivity(login);
                finish();
                break;
        }
        return true;
    }

    private void closeDrawer() {
        drawerLayout.closeDrawer(Gravity.RIGHT);
        frameLayout.setVisibility(View.VISIBLE);
    }

    private void openDrawer() {
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            closeDrawer();
        } else super.onBackPressed();
    }

    private void SetToolbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar);
        NavigationView navigationView = findViewById(R.id.navigation);
        drawerLayout = findViewById(R.id.drawer_layout);
        setSupportActionBar(mToolbar);
        navigationView.setNavigationItemSelectedListener(this);
        final ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    frameLayout.setVisibility(View.VISIBLE);
                } else {
                    frameLayout.setVisibility(View.GONE);
                }
                super.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                frameLayout.setVisibility(View.VISIBLE);
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                frameLayout.setVisibility(View.GONE);
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        View header = navigationView.getHeaderView(0);
        user = header.findViewById(R.id.info);
        profilePictureView = header.findViewById(R.id.user_image);
        UserData userData = getIntent().getParcelableExtra("user");
        user.setText(user.getText() + "  " + userData.getName() + "\n");
        String imageUri = userData.getImage();
        Picasso.get().load(imageUri).into(profilePictureView);
    }

    private void SetBottomNavigator() {
        mnav = findViewById(R.id.bottom_nav);
        frameLayout = findViewById(R.id.frame);
        mnav.getMenu().getItem(1).setChecked(true);
        mnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.quick_order:
                        SetFragment(quickOrder);
                        return true;
                    case R.id.regular_order:
                        SetFragment(regularOrder);
                        return true;
                    case R.id.smart_order:
                        SetFragment(smartOrder);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    private void SetFragment(Fragment frag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, frag);
        fragmentTransaction.commit();
    }
    //AsyncTask
    public class FetchMovies extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            page++;
            popularMoviesURL = "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=bffac436c406ffac0c7b2bbc005cfc16&page="+page;
            popularList = new ArrayList<>();
            try {
                if(NetworkUtils.networkStatus(HomePage.this)){
                        popularList.addAll(NetworkUtils.fetchData(popularMoviesURL));
                    }
            } catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void  s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}

