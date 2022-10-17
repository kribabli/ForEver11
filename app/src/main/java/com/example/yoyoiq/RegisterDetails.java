package com.example.yoyoiq;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yoyoiq.KYC.KYCActivity;
import com.example.yoyoiq.LoginPojo.LoginResponse;
import com.example.yoyoiq.LoginPojo.RegistrationResponse;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterDetails extends AppCompatActivity {
    TextView backPress, registerUser;
    DatabaseReference databaseReference;
    DatabaseConnectivity databaseConnectivity = new DatabaseConnectivity();
    TextView userName;
    TextView mobileNo;
    TextView emailId;
    TextView password;
    ProgressDialog progressDialog;
    SharedPrefManager sharedPrefManager;
    SessionManager sessionManager;
    String check;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    List<userLoginData> list;
    ProgressBar progress_circular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_details);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        initMethod();
        setAction();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Data Uploading..");
        check=getIntent().getStringExtra("check");
        sessionManager = new SessionManager(getApplicationContext());

    }

    private void initMethod() {
        backPress = findViewById(R.id.backPress);
        registerUser = findViewById(R.id.registerUserDetails);
        userName = findViewById(R.id.userName);
        mobileNo = findViewById(R.id.mobileNo);
        emailId = findViewById(R.id.emailId);
        password = findViewById(R.id.password);
        progress_circular = findViewById(R.id.progress_circular);
    }

    private void setAction() {
        backPress.setOnClickListener(view -> onBackPressed());

        registerUser.setOnClickListener(view -> validation());
    }

    private boolean validation() {
        boolean isValid = true;
        try {
            if (userName.getText().toString().trim().length() == 0) {
                userName.setError("Please enter user name");
                userName.requestFocus();
                isValid = false;
            } else if (mobileNo.getText().toString().trim().length() == 0) {
                mobileNo.setError("Please enter mobile number");
                mobileNo.requestFocus();
                isValid = false;
            } else if (emailId.getText().toString().trim().length() == 0) {
                emailId.setError("Please enter email");
                emailId.requestFocus();
                isValid = false;
            } else if (password.getText().toString().trim().length() == 0) {
                password.setError("Please enter password");
                password.requestFocus();
                isValid = false;
            } else {
                send_user_Data_onServer();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }




    private void send_user_Data_onServer() {
        registerUser.setVisibility(View.GONE);
        progress_circular.setVisibility(View.VISIBLE);
        Log.d("Amit","Value "+mobileNo.getText().toString()+" "+userName.getText().toString()+""+emailId.getText().toString()+" "+password.getText().toString());
        Call<RegistrationResponse> call = ApiClient.getInstance().getApi().
                SendUserDetails_server(userName.getText().toString(), mobileNo.getText().toString(), password.getText().toString(), emailId.getText().toString());

        call.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                Log.d("Amit","Value 1"+response);
                RegistrationResponse registrationResponse= response.body();
                String Data=new Gson().toJson(registrationResponse.getResponse());
                Boolean data1= Boolean.valueOf(new Gson().toJson(registrationResponse.isStatus()));
                Log.d("Amit","Value "+data1);
                if (response.isSuccessful()) {
                    if(registrationResponse.getResponse().equalsIgnoreCase("Mobile already exist")){
                        registerUser.setVisibility(View.VISIBLE);
                        progress_circular.setVisibility(View.GONE);
                        showDialog(""+registrationResponse.getResponse(), false);

                    }
                    else if(registrationResponse.getResponse().equalsIgnoreCase("Registration Successfully")){
                        registerUser.setVisibility(View.VISIBLE);
                        progress_circular.setVisibility(View.GONE);
                        showDialog("User Register Successfully.", true);
                    }
                    else{
                        registerUser.setVisibility(View.VISIBLE);
                        progress_circular.setVisibility(View.GONE);
                        showDialog(""+registrationResponse.getResponse(), false);

                    }

                }
            }
            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                Log.d("Amit","Value error "+t.toString());
                registerUser.setVisibility(View.VISIBLE);
                progress_circular.setVisibility(View.GONE);
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