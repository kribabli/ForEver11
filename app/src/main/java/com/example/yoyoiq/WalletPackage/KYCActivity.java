package com.example.yoyoiq.WalletPackage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yoyoiq.Modal.SharedPrefManager;
import com.example.yoyoiq.R;
import com.example.yoyoiq.common.DatabaseConnectivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;

public class KYCActivity extends AppCompatActivity {
    TextView backPress, camera1, camera2;
    EditText fullName, accountNo, retypeAccount, bankName, ifscCode, panCard, aadharNo;
    Button submit;
    DatabaseConnectivity databaseConnectivity = new DatabaseConnectivity();
    SharedPrefManager sharedPrefManager;
    String loggedInUserNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kycactivity);
        initMethod();
        setAction();
        loggedInUserNumber = sharedPrefManager.getUserData().getMobileNo();
    }

    private void initMethod() {
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        backPress = findViewById(R.id.backPress);
        camera1 = findViewById(R.id.camera1);
        camera2 = findViewById(R.id.camera2);
        fullName = findViewById(R.id.fullNameEt);
        accountNo = findViewById(R.id.accountNoEt);
        retypeAccount = findViewById(R.id.accountNoEt1);
        bankName = findViewById(R.id.bankName);
        ifscCode = findViewById(R.id.ifscCode);
        panCard = findViewById(R.id.panCardNo);
        aadharNo = findViewById(R.id.aadharNo);
        submit = findViewById(R.id.submitBtn);
    }

    private void setAction() {
        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonValidation();
            }
        });
    }

    private boolean buttonValidation() {
        boolean isValid = true;
        try {
            if (fullName.getText().toString().trim().length() == 0) {
                fullName.setError("Please enter Full Name");
                fullName.requestFocus();
                isValid = false;
            } else if (accountNo.getText().toString().trim().length() == 0) {
                accountNo.setError("Please enter account no.");
                accountNo.requestFocus();
                isValid = false;
            } else if (retypeAccount.getText().toString().trim().length() == 0
                    || accountNo.getText().toString().trim().equals(retypeAccount.getText().toString().trim())) {
                retypeAccount.setError("Re-Enter account no.");
                retypeAccount.requestFocus();
                isValid = false;
            } else if (bankName.getText().toString().trim().length() == 0) {
                bankName.setError("Please enter Bank Name");
                bankName.requestFocus();
                isValid = false;
            } else if (ifscCode.getText().toString().trim().length() == 0) {
                ifscCode.setError("Please enter IFSC Code");
                ifscCode.requestFocus();
                isValid = false;
            } else if (panCard.getText().toString().trim().length() == 0) {
                panCard.setError("Please enter PANCard No.");
                panCard.requestFocus();
                isValid = false;
            } else if (aadharNo.getText().toString().trim().length() == 0) {
                    aadharNo.setError("Please enter Aadhar");
                    aadharNo.requestFocus();
                    isValid = false;
            } else {
                insertKYCDetails();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }

    private void insertKYCDetails() {
        String fullName1 = fullName.getText().toString().trim();
        String accountNo1 = accountNo.getText().toString().trim();
        String retypeAccount1 = retypeAccount.getText().toString().trim();
        String bankName1 = bankName.getText().toString().trim();
        String ifscCode1 = ifscCode.getText().toString().trim();
        String panCard1 = panCard.getText().toString().trim();
        String aadharNo1 = aadharNo.getText().toString().trim();

        HashMap<String, Object> data = new HashMap<>();
        data.put("fullName", fullName1);
        data.put("accountNo", accountNo1);
        data.put("bankName", bankName1);
        data.put("ifscCode", ifscCode1);
        data.put("panCard", panCard1);
        data.put("aadharNo", aadharNo1);

        databaseConnectivity.getDatabasePath(this).child("KYCDetails").child(loggedInUserNumber)
                .setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        showDialog("Details Saved..", true);
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