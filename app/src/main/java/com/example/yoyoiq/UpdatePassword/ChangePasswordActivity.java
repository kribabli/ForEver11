package com.example.yoyoiq.UpdatePassword;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yoyoiq.LoginPojo.LoginResponse;
import com.example.yoyoiq.Model.UserData;
import com.example.yoyoiq.R;
import com.example.yoyoiq.Retrofit.ApiClient;
import com.example.yoyoiq.UpdatePassword.UpdatePassword;
import com.example.yoyoiq.common.HelperData;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText yourMobileNo, PasswordNew;
    TextView update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initMethod();
        setAction();
    }

    private void initMethod() {
        yourMobileNo = findViewById(R.id.yourMobileNo);
        PasswordNew = findViewById(R.id.newPassword);
        update = findViewById(R.id.update);
    }

    private void setAction() {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });
    }

    private boolean validation() {
        boolean isValid = true;
        try {
            if (yourMobileNo.getText().toString().trim().length() == 0) {
                yourMobileNo.setError("Please enter mobile number");
                yourMobileNo.requestFocus();
                isValid = false;
            } else if (PasswordNew.getText().toString().trim().length() == 0) {
                PasswordNew.setError("Please enter new password");
                PasswordNew.requestFocus();
                isValid = false;
            } else {
                changePassword();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }

    private void changePassword() {
        String mobile = yourMobileNo.getText().toString().trim();
        String password = PasswordNew.getText().toString().trim();
        Call<UpdatePassword> call = ApiClient.getInstance().getApi().upDatePassword(mobile, password);
        call.enqueue(new Callback<UpdatePassword>() {
            @Override
            public void onResponse(Call<UpdatePassword> call, Response<UpdatePassword> response) {
                UpdatePassword updatePassword = response.body();
                if (response.isSuccessful()) {
                    if (updatePassword.isStatus() == true) {
                        showDialog("" + updatePassword.getResponse(), true);
                    } else if (updatePassword.isStatus() == false) {
                        showDialog("" + updatePassword.getResponse(), false);
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<UpdatePassword> call, Throwable t) {
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