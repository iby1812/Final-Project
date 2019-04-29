package com.cinema.cintix.home_screen;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cinema.cintix.R;
import com.cinema.cintix.adapter.MovieAdapter;
import com.cinema.cintix.fetch_movies_data.Movie;
import com.cinema.cintix.data.UserData;
import com.cinema.cintix.fetch_movies_data.MoviesRepository;
import com.cinema.cintix.fetch_movies_data.OnGetMoviesCallback;
import com.cinema.cintix.starting_app.LoginActivity;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

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
    static MovieAdapter adapter;
    ProgressBar progressBar;
    static List<Movie> moviesList = new ArrayList<>();
    private MoviesRepository moviesRepository;
    private boolean first=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_activity);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        SetToolbar();
        SetBottomNavigator();
        getMovies();
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
        mToolbar.setTitle("");
        NavigationView navigationView = findViewById(R.id.navigation);
        drawerLayout = findViewById(R.id.drawer_layout);
        setSupportActionBar(mToolbar);
        navigationView.setNavigationItemSelectedListener(this);
        final ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        //set drawer color
        navigationView.setBackgroundResource(R.drawable.border_set);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerLayout.bringToFront();
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

    private void getMovies() {
        moviesRepository = MoviesRepository.getInstance();
            moviesRepository.getMovies(new OnGetMoviesCallback() {
                @Override
                public void onSuccess(List<Movie> movies) {
                    progressBar.setVisibility(View.INVISIBLE);
                    moviesList.addAll(movies);
                    if (first) {
                        first = false;
                        SetFragment(regularOrder);
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getMovies();
                            //TO DO notify function
                            RegularOrder.notifypager();
                        }
                    },2000);
                }

                @Override
                public void onError() {

                }
            });

    }
}

