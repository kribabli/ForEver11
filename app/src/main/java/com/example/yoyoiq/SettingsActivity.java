package com.example.yoyoiq;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yoyoiq.KYC.ViewKycResponse;
import com.example.yoyoiq.Retrofit.ApiClient;
import com.example.yoyoiq.common.HelperData;
import com.example.yoyoiq.common.SessionManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsActivity extends AppCompatActivity {
    TextView backPress, userName, email, mobileNo, Country, logOut, city;
    SessionManager sessionManager;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    boolean status = false;
    String dob, address;
    Button DOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initMethod();
        setAction();
        getUserDetailFromAPI();
        upDateProfile();
    }

    private void initMethod() {
        backPress = findViewById(R.id.backPress);
        userName = findViewById(R.id.userName);
        email = findViewById(R.id.email);
        mobileNo = findViewById(R.id.mobileNo);
        Country = findViewById(R.id.Country);
        city = findViewById(R.id.city);
        DOB = findViewById(R.id.date_of_birth);
        logOut = findViewById(R.id.logOut);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        sessionManager = new SessionManager(getApplicationContext());
    }

    private void setAction() {
        backPress.setOnClickListener(view -> onBackPressed());

        logOut.setOnClickListener(v -> userLogout());
    }

    private void getUserDetailFromAPI() {
        Call<ViewKycResponse> call = ApiClient.getInstance().getApi().getkycDetails(sessionManager.getUserData().getUser_id());
        call.enqueue(new Callback<ViewKycResponse>() {
            @Override
            public void onResponse(Call<ViewKycResponse> call, Response<ViewKycResponse> response) {
                ViewKycResponse viewKycResponse = response.body();
                if (response.isSuccessful()) {
                    String data = new Gson().toJson(viewKycResponse.isStatus());
                    status = Boolean.parseBoolean(data);
                    JSONArray jsonArray1 = null;
                    if (status != false) {
                        String data2 = new Gson().toJson(viewKycResponse.getKycDetails());
                        try {
                            jsonArray1 = new JSONArray(data2);
                            for (int i = 0; i < jsonArray1.length(); i++) {
                                JSONObject jsonObject = jsonArray1.getJSONObject(i);
                                dob = jsonObject.getString("dob");
                                address = jsonObject.getString("address");
                                String status = jsonObject.getString("status");
                            }
                            city.setText(address);
                            DOB.setText(dob);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ViewKycResponse> call, Throwable t) {
            }
        });
    }

    private void upDateProfile() {
        userName.setText(sessionManager.getUserData().getUserName());
        email.setText(sessionManager.getUserData().getEmailId());
        mobileNo.setText(sessionManager.getUserData().getMobileNo());
        Country.setText("India");
    }

    private void userLogout() {
        gsc.signOut();
        sessionManager.logout();
        HelperData.UserId = "";
        HelperData.UserName = "";
        HelperData.Usermobile = "";
        HelperData.UserEmail = "";
        Intent intent = new Intent(SettingsActivity.this, FrontActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Toast.makeText(this, "Logout Successfully", Toast.LENGTH_SHORT).show();
    }
}