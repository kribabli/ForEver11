package com.example.yoyoiq.WalletPackage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.yoyoiq.KYC.KYCActivity;
import com.example.yoyoiq.KYC.ShowKYCDetails;
import com.example.yoyoiq.KYC.ViewKycResponse;
import com.example.yoyoiq.NotificationActivity;
import com.example.yoyoiq.R;
import com.example.yoyoiq.Retrofit.ApiClient;
import com.example.yoyoiq.common.DatabaseConnectivity;
import com.example.yoyoiq.common.HelperData;
import com.example.yoyoiq.common.SessionManager;
import com.example.yoyoiq.common.SharedPrefManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCash extends AppCompatActivity implements PaymentResultListener {
    TextView addCash, backPress, myRecentPay, KYCDetails, notification, amountTv, bonusTv, withdrawTv, winningsTV;
    EditText amount;
    DatabaseConnectivity common = DatabaseConnectivity.getInstance();
    String loggedInUserNumber;
    SharedPrefManager sharedPrefManager;
    SessionManager sessionManager;
    SwipeRefreshLayout swipeRefreshLayout;
    String mobilenumber="";
    String emailAddress="";
    String staus1="";
    String Pancard="";
    String BankAccount="";
    boolean status=false;
    List<String> checkId = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cash2);
        Checkout.preload(getApplicationContext());
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        sessionManager = new SessionManager(getApplicationContext());
        swipeRefreshLayout = new SwipeRefreshLayout(AddCash.this);
        LoadKycData();
        initMethod();
        loadBalanceDataFromServer();
        loggedInUserNumber = sharedPrefManager.getUserData().getMobileNo();
        setAction();

    }

    private void LoadKycData() {
        Call<ViewKycResponse> call=ApiClient.getInstance().getApi().getkycDetails(sessionManager.getUserData().getUser_id());
        call.enqueue(new Callback<ViewKycResponse>() {
            @Override
            public void onResponse(Call<ViewKycResponse> call, Response<ViewKycResponse> response) {
                ViewKycResponse viewKycResponse= response.body();
                if(response.isSuccessful()){
                    String data=new Gson().toJson(viewKycResponse.isStatus());
                    status= Boolean.parseBoolean(data);
                    JSONArray jsonArray1 = null;
                    if(status!=false){
                        String data2=new Gson().toJson(viewKycResponse.getKycDetails());
                        try {
                            jsonArray1 = new JSONArray(data2);
                            Log.d("Amit","Value Check "+jsonArray1);
                            for(int i=0;i<jsonArray1.length(); i++){
                                JSONObject jsonObject = jsonArray1.getJSONObject(i);
                                Pancard=jsonObject.getString("pancard_no");
                                BankAccount=jsonObject.getString("account_no");
                                staus1=jsonObject.getString("status");
                            }
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

    private void loadBalanceDataFromServer() {
        Call<ViewBalanceResponse> call = ApiClient.getInstance().getApi().getBalanceDetails(sessionManager.getUserData().getUser_id());

        call.enqueue(new Callback<ViewBalanceResponse>() {
            @Override
            public void onResponse(Call<ViewBalanceResponse> call, Response<ViewBalanceResponse> response) {
                ViewBalanceResponse viewBalanceResponse = response.body();
                if (response.isSuccessful()) {
                    String balanceData = new Gson().toJson(viewBalanceResponse.getBalance());
                    JSONArray jsonArray = null;
                    String balance = null;
                    String bouns_cash = null;
                    try {
                        jsonArray = new JSONArray(balanceData);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            balance = jsonObject.getString("balance");
                            bouns_cash = jsonObject.getString("add_bonus");
                        }
                        amountTv.setText(" " + balance);
                        bonusTv.setText(" " + bouns_cash);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ViewBalanceResponse> call, Throwable t) {

            }
        });
    }

    private void initMethod() {
        addCash = findViewById(R.id.addCash);
        amountTv = findViewById(R.id.amountTv);
        withdrawTv = findViewById(R.id.withdrawTv);
        winningsTV = findViewById(R.id.winningsTV);
        bonusTv = findViewById(R.id.bonusTv);
        amount = findViewById(R.id.amount);
        notification = findViewById(R.id.notification);
        backPress = findViewById(R.id.backPress);
        myRecentPay = findViewById(R.id.myRecentPay);
        KYCDetails = findViewById(R.id.KYCDetails);
    }

    private void setAction() {
        myRecentPay.setOnClickListener(view -> {
            Intent intent = new Intent(AddCash.this, RecentTransactions.class);
            startActivity(intent);
        });

        backPress.setOnClickListener(view -> onBackPressed());

        notification.setOnClickListener(view -> {
            Intent intent = new Intent(AddCash.this, NotificationActivity.class);
            startActivity(intent);
        });

        addCash.setOnClickListener(view -> addAmountValidation());
        KYCDetails.setOnClickListener(view -> {
            if (status!=false) {
                int validationStatus= Integer.parseInt(staus1);
                if(validationStatus==3){
                    common.showAlertDialog("Alert!","Please Upload Your Kyc Data Again",false,AddCash.this);
                    Intent intent1 = new Intent(AddCash.this, KYCActivity.class);
                    startActivity(intent1);
                    finish();
                }
                else{
                    Intent intent = new Intent(AddCash.this, ShowKYCDetails.class);
                    intent.putExtra("Pancard",Pancard);
                    intent.putExtra("BankAccount",BankAccount);
                    intent.putExtra("status",staus1);
                    startActivity(intent);
                    finish();
                }

            } else {
                Intent intent1 = new Intent(AddCash.this, KYCActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }

    private boolean addAmountValidation() {
        boolean isValid = true;
        try {
            if (amount.getText().toString().trim().length() == 0
                    || amount.getText().toString().startsWith("0")
                    || amount.getText().toString().startsWith("00")) {
                amount.setError("Please enter amount");
                amount.requestFocus();
                isValid = false;
            } else {
                paymentMethod();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }

    private void paymentMethod() {
        String sAmount = amount.getText().toString();
        int amount = Math.round(Float.parseFloat(sAmount) * 100);

        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_jBetacTbV0YhJN");
        checkout.setImage(R.drawable.cricket_player);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", "YoYoIq");
            jsonObject.put("description", "Test Payment");
            jsonObject.put("theme.color", "#0093DD");
            jsonObject.put("currency", "INR");
            jsonObject.put("amount", amount);
            jsonObject.put("contact", "xxxxxxxx32");
            jsonObject.put("meratemplate", "mtteam.suraj@gmail.com");
            checkout.open(AddCash.this, jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void sendBalanceServer(String s) {
        Call<PostBalanceResponse> call = ApiClient.getInstance().getApi().sendBalanceData(HelperData.UserId, amount.getText().toString(), s);
        call.enqueue(new Callback<PostBalanceResponse>() {
            @Override
            public void onResponse(Call<PostBalanceResponse> call, Response<PostBalanceResponse> response) {
                PostBalanceResponse postBalanceResponse = response.body();
                if (response.isSuccessful()) {
                    if (postBalanceResponse.getResponse().toString().equalsIgnoreCase("successfully added")) {
                        loadBalanceDataFromServer();
                        amount.setText("");
                    }
                }
            }

            @Override
            public void onFailure(Call<PostBalanceResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Payment Successfully added..");
        builder.setMessage(s);
        builder.show();
        sendBalanceServer(s);
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}