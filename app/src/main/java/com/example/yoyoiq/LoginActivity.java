package com.example.yoyoiq;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yoyoiq.LoginPojo.LoginResponse;
import com.example.yoyoiq.LoginPojo.userLoginData;
import com.example.yoyoiq.Model.UserData;
import com.example.yoyoiq.Retrofit.ApiClient;
import com.example.yoyoiq.common.DatabaseConnectivity;
import com.example.yoyoiq.common.SessionManager;
import com.example.yoyoiq.common.SharedPrefManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button sign_In_FB;
    EditText mobileNo, userPassword;
    TextView login, backPress;
    DatabaseConnectivity databaseConnectivity = new DatabaseConnectivity();
    String password, alreadyRegisterMobile;
    SharedPrefManager sharedPrefManager;
    SessionManager sessionManager;
    ArrayList<String> allPhoneNumber = new ArrayList<>();
    ArrayList<String> allPassword = new ArrayList<>();
    List<userLoginData> list;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        sharedPreferences = getSharedPreferences("path", MODE_PRIVATE);
        initMethod();
        setAction();
        DownloadData();
    }

    private void initMethod() {
        sign_In_FB = findViewById(R.id.button_facebook);
        mobileNo = findViewById(R.id.mobileNo);
        userPassword = findViewById(R.id.userPassword);
        login = findViewById(R.id.login);
        backPress = findViewById(R.id.backPress);
    }

    private void setAction() {
        login.setOnClickListener(view -> dataValidation());

        backPress.setOnClickListener(view -> onBackPressed());
    }

    private void DownloadData() {
        databaseConnectivity.getDatabasePath(this).child("RegisterDetails").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        alreadyRegisterMobile = dataSnapshot.child("mobileNo").getValue().toString();
                        allPhoneNumber.add(alreadyRegisterMobile);
                        password = dataSnapshot.child("password").getValue().toString();
                        allPassword.add(password);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private boolean dataValidation() {
        boolean isValid = true;
        try {
            if (mobileNo.getText().toString().trim().length() == 0) {
                mobileNo.setError("Please enter mobile number");
                mobileNo.requestFocus();
                isValid = false;
            } else if (userPassword.getText().toString().trim().length() == 0) {
                userPassword.setError("Please enter password");
                userPassword.requestFocus();
                isValid = false;
            } else {
                LoginValidation();
                LoginValidationFromServer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isValid;
    }

    private void LoginValidationFromServer() {
        String mobile = mobileNo.getText().toString().trim();
        String password1 = userPassword.getText().toString().trim();

        Call<LoginResponse>call= ApiClient.getInstance().getApi().getUserLoginData(mobile,password1);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse=response.body();
                if(response.isSuccessful()){
                    Log.d("Amit","Value check 1"+loginResponse.getData());
                    if(loginResponse.getData()=="Login successful"){


                    }
                    else if(loginResponse.getData()=="Username or password something went wrong"){
                        showDialog("Invalid Mobile or Password", false);
                    }

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

    }

    private void LoginValidation() {
        String mobile = mobileNo.getText().toString().trim();
        String password1 = userPassword.getText().toString().trim();
        String userName = "";
        String emailId = "";
        UserData userData = new UserData(userName, mobile, emailId, password1);
        sharedPrefManager.saveUser(userData);
        if (allPhoneNumber.contains(mobile)) {
            if (allPassword.contains(password1)) {

                //here we get particular user data for profileActivity.
                databaseConnectivity.getDatabasePath(this).child("RegisterDetails").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.getValue() != null) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                String mobile1 = dataSnapshot.child("mobileNo").getValue().toString();
                                String password11 = dataSnapshot.child("password").getValue().toString();
                                if (mobile1.contains(mobile) && password11.contains(password1)) {
                                    String userName1 = dataSnapshot.child("userName").getValue().toString();
                                    String emailId1 = dataSnapshot.child("emailId").getValue().toString();
                                    sharedPreferences.edit().putString("userName", userName1).apply();
                                    sharedPreferences.edit().putString("emailId", emailId1).apply();
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        } else {
            showDialog("Invalid Mobile or Password", false);
        }
    }

    public void showDialog(String message, Boolean isFinish) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", (dialog, id) -> {
            dialog.dismiss();
            if (isFinish) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}