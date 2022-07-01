package com.example.yoyoiq;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Fragment selectedFragment = null;
    Fragment fragment = new Fragment();
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    LinearLayout mainActivityLayout;
    TextView textView,notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.walletTV);
        notification=findViewById(R.id.notification);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddCash.class);
                startActivity(intent);
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,NotificationActivity.class);
                startActivity(intent);
            }
        });

        selectedFragment = fragment;
        new Thread(this::mBottomNavigationBar).start();
        mainActivityLayout = findViewById(R.id.mainActivityLayout);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.NavigationView);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    private void mBottomNavigationBar() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            boolean bool = false;
            if (bottomNavigationView.getSelectedItemId() != item.getItemId()) {
                switch (item.getItemId()) {
                    case R.id.home:
                        bool = true;
                        selectedFragment = new HomeFragment();
                        mainActivityLayout.setVisibility(View.GONE);
                        break;
                    case R.id.my_matches:
                        bool = true;
                        selectedFragment = new MyMatchesFragment();
                        mainActivityLayout.setVisibility(View.GONE);
                        break;
                    case R.id.winners:
                        bool = true;
                        selectedFragment = new WinnersFragment();
                        mainActivityLayout.setVisibility(View.GONE);
                        break;
                    case R.id.chat:
                        bool = true;
                        selectedFragment = new ChatFragment();
                        mainActivityLayout.setVisibility(View.GONE);
                        break;
                    case R.id.rewards:
                        bool = true;
                        selectedFragment = new RewardsFragment();
                        mainActivityLayout.setVisibility(View.GONE);
                        break;
                }
            }
            if (selectedFragment != null) {
                MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            }
            return bool;
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.wallet_item:
                Toast.makeText(this, "Wallet", Toast.LENGTH_SHORT).show();
                break;
            case R.id.search:
                Toast.makeText(this, "Find People", Toast.LENGTH_SHORT).show();
                break;
            case R.id.howToPlay:
                Toast.makeText(this, "How To Play", Toast.LENGTH_SHORT).show();
                break;
            case R.id.infoSettings:
                Intent intent1=new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(intent1);
                break;
            case R.id.aboutUs:
                Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.termsAndConditions:
                Toast.makeText(this, "Terms & Conditions", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logOut:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

}