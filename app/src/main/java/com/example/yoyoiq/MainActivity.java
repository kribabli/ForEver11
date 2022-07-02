package com.example.yoyoiq;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import androidx.viewpager.widget.ViewPager;

import com.example.yoyoiq.Adapter.BannerAdapter;
import com.example.yoyoiq.Modal.SharedPrefManager;
import com.example.yoyoiq.Modal.The_Slide_Items_Model_Class;
import com.example.yoyoiq.WalletPackage.AddCash;
import com.example.yoyoiq.common.DatabaseConnectivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Fragment selectedFragment = null;
    Fragment fragment = new Fragment();
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    LinearLayout mainActivityLayout;
    TextView textView, notification;
    ViewPager view_bannerItem;
    private List<The_Slide_Items_Model_Class> listItems;
    DatabaseConnectivity databaseConnectivity;
    SharedPreferences pathSharedPreferences;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.walletTV);
        pathSharedPreferences = getSharedPreferences("YoyoIq", MODE_PRIVATE);
        notification = findViewById(R.id.notification);
        view_bannerItem = findViewById(R.id.view_bannerItem);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        textView.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddCash.class);
            startActivity(intent);
        });
        notification.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
            startActivity(intent);
        });
        selectedFragment = fragment;
        new Thread(this::mBottomNavigationBar).start();
        mainActivityLayout = findViewById(R.id.mainActivityLayout);
        databaseConnectivity = new DatabaseConnectivity();
        setAutoSliderBanner();

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.NavigationView);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setAutoSliderBanner() {
        databaseConnectivity.getDatabaseStorage(this).child("BannerImages/BannerImageUri.json").getMetadata().addOnSuccessListener(storageMetadata -> {
            long fileCreationTime = storageMetadata.getCreationTimeMillis();
            long fileDownloadTime = pathSharedPreferences.getLong("bannerImageDownloadTime", 0);
            databaseConnectivity.getDatabaseStorage(this).child("BannerImages/BannerImageUri.json").getBytes(10000000).addOnSuccessListener(taskSnapshot -> {
                String str = new String(taskSnapshot, StandardCharsets.UTF_8);
                pathSharedPreferences.edit().putString("bannerImagesDetails", str).apply();
                pathSharedPreferences.edit().putLong("bannerImageDownloadTime", fileCreationTime).apply();
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(str);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        Log.d("Amit ", "Value 111 " + jsonObject.getString("bannerImageUri"));
                    }
                    listItems = new ArrayList<>();
                    listItems.add(new The_Slide_Items_Model_Class(R.drawable.banner2));
                    listItems.add(new The_Slide_Items_Model_Class(R.drawable.banner3));
                    listItems.add(new The_Slide_Items_Model_Class(R.drawable.banner4));
                    listItems.add(new The_Slide_Items_Model_Class(R.drawable.banner5));
                    listItems.add(new The_Slide_Items_Model_Class(R.drawable.banner6));
                    BannerAdapter bannerAdapter = new BannerAdapter(this, listItems);
                    java.util.Timer timer = new java.util.Timer();
                    timer.scheduleAtFixedRate(new The_slide_timer(), 1000, 2000);
                    view_bannerItem.setAdapter(bannerAdapter);
                    bannerAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
        });
    }

    public class The_slide_timer extends TimerTask {
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (view_bannerItem.getCurrentItem() < listItems.size() - 1) {
                        view_bannerItem.setCurrentItem(view_bannerItem.getCurrentItem() + 1);
                    } else
                        view_bannerItem.setCurrentItem(0);
                }
            });
        }
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
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.wallet_item:
                Intent intent3 = new Intent(MainActivity.this, AddCash.class);
                startActivity(intent3);
                break;
            case R.id.search:
                Intent intent2 = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent2);
                break;
            case R.id.howToPlay:
                Toast.makeText(this, "How To Play", Toast.LENGTH_SHORT).show();
                break;
            case R.id.infoSettings:
                Intent intent1 = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent1);
                break;
            case R.id.aboutUs:
                Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.termsAndConditions:
                Toast.makeText(this, "Terms & Conditions", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logOut:
                userLogout();
                break;
        }
        return true;
    }

    private void userLogout() {
        sharedPrefManager.logout();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Toast.makeText(this, "Logout Successfully", Toast.LENGTH_SHORT).show();
    }
}