package com.example.yoyoiq;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.yoyoiq.Adapter.AllMatchAdapter;
import com.example.yoyoiq.Adapter.BannerAdapter;
import com.example.yoyoiq.LoginPojo.RegistrationResponse;
import com.example.yoyoiq.Model.The_Slide_Items_Model_Class;
import com.example.yoyoiq.Model.TotalHomeData;
import com.example.yoyoiq.PrivacyPolicy.AboutUsActivity;
import com.example.yoyoiq.Retrofit.ApiClient;
import com.example.yoyoiq.UpcommingReq.UpcommingResponse;
import com.example.yoyoiq.WalletPackage.AddCash;
import com.example.yoyoiq.common.DatabaseConnectivity;
import com.example.yoyoiq.common.HelperData;
import com.example.yoyoiq.common.SessionManager;
import com.example.yoyoiq.common.SharedPrefManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Fragment selectedFragment = null;
    Fragment fragment = new Fragment();
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    LinearLayout mainActivityLayout;
    TextView textView, notification, profileView;
    ViewPager view_bannerItem;
    private List<The_Slide_Items_Model_Class> listItems;
    DatabaseConnectivity databaseConnectivity;
    SharedPreferences pathSharedPreferences;
    SharedPrefManager sharedPrefManager;
    SessionManager sessionManager;
    RecyclerView recyclerView;
    AllMatchAdapter allMatchAdapter;
    ArrayList<TotalHomeData> list = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAllMatches();
        recyclerView = findViewById(R.id.recyclerViewMatchList);
        sessionManager = new SessionManager(getApplicationContext());

        swipeRefreshLayout = findViewById(R.id.swiper);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllMatches();
            }
        });

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

        profileView = findViewById(R.id.profileView);
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
        setAction();
        navigationView.setNavigationItemSelectedListener(this);
        googleSignIn();
    }

    private void googleSignIn() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (googleSignInAccount != null) {
            String userName = googleSignInAccount.getDisplayName();
            String userEmail = googleSignInAccount.getEmail();
            Uri photoUrl = googleSignInAccount.getPhotoUrl();
            String id = googleSignInAccount.getId();
            Log.d("TAG", "onCreate: " + id + "  " + userName + "  " + userEmail + "  " + photoUrl);

            Call<RegistrationResponse> call = ApiClient.getInstance().getApi().
                    SendUserDetails_server(id, userName, userEmail, String.valueOf(photoUrl));
            call.enqueue(new Callback<RegistrationResponse>() {
                @Override
                public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                }

                @Override
                public void onFailure(Call<RegistrationResponse> call, Throwable t) {

                }
            });
        }
    }

    private void setAction() {
        profileView.setOnClickListener(v -> {
            if (!drawerLayout.isDrawerOpen(GravityCompat.START))
                drawerLayout.openDrawer(GravityCompat.START);
            else drawerLayout.closeDrawer(GravityCompat.END);
        });
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
                    }
                    listItems = new ArrayList<>();
                    listItems.add(new The_Slide_Items_Model_Class(R.drawable.group1));
                    listItems.add(new The_Slide_Items_Model_Class(R.drawable.group2));
                    listItems.add(new The_Slide_Items_Model_Class(R.drawable.group3));
                    listItems.add(new The_Slide_Items_Model_Class(R.drawable.group4));
                    listItems.add(new The_Slide_Items_Model_Class(R.drawable.group5));
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
            MainActivity.this.runOnUiThread(() -> {
                if (view_bannerItem.getCurrentItem() < listItems.size() - 1) {
                    view_bannerItem.setCurrentItem(view_bannerItem.getCurrentItem() + 1);
                } else
                    view_bannerItem.setCurrentItem(0);
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
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
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
                Intent intent6 = new Intent(MainActivity.this, HowToPlay.class);
                startActivity(intent6);
                break;
            case R.id.infoSettings:
                Intent intent1 = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent1);
                break;
            case R.id.aboutUs:
                Intent intent5 = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent5);
                break;
            case R.id.termsAndConditions:
                Intent intent4 = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent4);
                break;
            case R.id.logOut:
                userLogout();
                break;
        }
        return true;
    }

    private void userLogout() {
        gsc.signOut();
        sessionManager.logout();
        HelperData.UserId = "";
        HelperData.UserName = "";
        HelperData.Usermobile = "";
        HelperData.UserEmail = "";
        Intent intent = new Intent(MainActivity.this, FrontActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Toast.makeText(this, "Logout Successfully", Toast.LENGTH_SHORT).show();
    }

    private void getAllMatches() {
        list.clear();
        Call<UpcommingResponse> call = ApiClient
                .getInstance()
                .getApi()
                .getMatch();

        call.enqueue(new Callback<UpcommingResponse>() {
            @Override
            public void onResponse(Call<UpcommingResponse> call, Response<UpcommingResponse> response) {
                UpcommingResponse status = response.body();
                if (response.isSuccessful()) {
                    String jsonArray = new Gson().toJson(status.getResponse().getItems());
                    JSONArray jsonArray1 = null;
                    try {
                        jsonArray1 = new JSONArray(jsonArray);
                        for (int i = 0; i < jsonArray1.length(); i++) {
                            JSONObject jsonObject = jsonArray1.getJSONObject(i);
                            String title = jsonObject.getString("title");
                            String match_id = jsonObject.getString("match_id");
                            String date_start = jsonObject.getString("date_start_ist");
                            String date_end = jsonObject.getString("date_end_ist");

                            JSONArray teama1 = jsonObject.getJSONArray("teama");
                            JSONArray teamb1 = jsonObject.getJSONArray("teamb");

                            JSONObject jsonObject11 = teama1.getJSONObject(0);
                            JSONObject jsonObject22 = teamb1.getJSONObject(0);

                            String logo_url_a = jsonObject11.getString("logo_url");
                            String name_a = jsonObject11.getString("name");
                            String short_name_a = jsonObject11.getString("short_name");
//                            int teamIda = Integer.parseInt(jsonObject11.getString("team_id"));

                            String logo_url_b = jsonObject22.getString("logo_url");
                            String name_b = jsonObject22.getString("name");
                            String short_name_b = jsonObject22.getString("short_name");
//                            int teamIdb = Integer.parseInt(jsonObject22.getString("team_id"));

                            TotalHomeData totalHomeData = new TotalHomeData(title, match_id, logo_url_a, name_a, short_name_a, logo_url_b, name_b, short_name_b, date_start, date_end);
                            list.add(totalHomeData);

                            allMatchAdapter = new AllMatchAdapter(getApplicationContext(), list);
                            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            recyclerView.setAdapter(allMatchAdapter);
                            allMatchAdapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    } catch (JSONException e) {
                        swipeRefreshLayout.setRefreshing(false);
                        e.printStackTrace();
                    }
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<UpcommingResponse> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}