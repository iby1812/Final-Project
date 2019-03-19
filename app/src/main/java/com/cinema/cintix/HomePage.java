package com.cinema.cintix;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.cinema.cintix.AppStarts.LoginActivity;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView info;
    TextView user;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mToolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        user = findViewById(R.id.user_name);
        info = findViewById(R.id.info);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this,drawerLayout,mToolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        String item =(String) menuItem.getTitle();
        info.setText(item);
        if(getIntent().getExtras() != null && getIntent().getExtras().getString("user")!= null) {
            user.setText(getIntent().getExtras().getString("user"));
        }
        closeDrawer();
        switch(menuItem.getItemId()){
            case R.id.action1:
                //add new activity
                break;
            case R.id.action2:
                //add new activity
                break;
            case R.id.action3:
                //add new activity
                break;
        }
        return true;
    }

    private void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void openDrawer(){
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            closeDrawer();
        }else super.onBackPressed();
    }
}
