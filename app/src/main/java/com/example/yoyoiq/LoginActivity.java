package com.example.yoyoiq;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yoyoiq.LoginPojo.LoginResponse;
import com.example.yoyoiq.LoginPojo.userLoginData;
import com.example.yoyoiq.Model.UserData;
import com.example.yoyoiq.Retrofit.ApiClient;
import com.example.yoyoiq.common.DatabaseConnectivity;
import com.example.yoyoiq.common.HelperData;
import com.example.yoyoiq.common.SessionManager;
import com.example.yoyoiq.common.SharedPrefManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button sign_In_FB;
    SignInButton sign_in_button;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
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
        sessionManager = new SessionManager(getApplicationContext());

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        initMethod();
        setAction();
    }

    private void initMethod() {
        sign_in_button = findViewById(R.id.sign_in_button);
        sign_In_FB = findViewById(R.id.button_facebook);
        mobileNo = findViewById(R.id.mobileNo);
        userPassword = findViewById(R.id.userPassword);
        login = findViewById(R.id.login);
        backPress = findViewById(R.id.backPress);
    }

    private void setAction() {
        login.setOnClickListener(view -> dataValidation());

        backPress.setOnClickListener(view -> onBackPressed());

        sign_in_button.setOnClickListener(v -> signIn());
    }

    private void signIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(this, "Something went wrong !", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void navigateToSecondActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("checkData",true);
        startActivity(intent);
        finish();
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
//                LoginValidation();
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
        Call<LoginResponse> call = ApiClient.getInstance().getApi().getUserLoginData(mobile, password1);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if (response.isSuccessful()) {
                    if (loginResponse.getData().trim().toString().equalsIgnoreCase("Login successful")) {
                        list = loginResponse.getUserLoginDataArrayList();
                        String totalData = new Gson().toJson(loginResponse.getUserLoginDataArrayList());
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(totalData);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String email_id = jsonObject.getString("email_id");
                                String mobile_no = jsonObject.getString("mobile_no");
                                String user_id = jsonObject.getString("user_id");
                                String username = jsonObject.getString("username");
                                HelperData.UserId = user_id;
                                HelperData.UserName = username;
                                HelperData.Usermobile = mobile_no;
                                HelperData.UserEmail = email_id;
                                UserData userData = new UserData(username, mobile_no, email_id, user_id);
                                sessionManager.saveUser(userData);
                                Toast.makeText(LoginActivity.this, "Login Successfully..", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else if (loginResponse.getData().trim().toString().equalsIgnoreCase("Username or password something went wrong")) {
                        showDialog("Invalid Mobile or Password", false);
                    } else {
                        showDialog("Please Register YourSelf", false);
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
            }
        });
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