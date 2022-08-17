package com.example.yoyoiq.KYC;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.yoyoiq.R;
import com.example.yoyoiq.common.DatabaseConnectivity;
import com.example.yoyoiq.common.HelperData;
import com.example.yoyoiq.common.SharedPrefManager;
import com.github.drjacky.imagepicker.ImagePicker;
import com.github.drjacky.imagepicker.constant.ImageProvider;
import com.github.drjacky.imagepicker.util.FileUriUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public class KYCActivity extends AppCompatActivity {
    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 101;
    TextView backPress, camera1;
    EditText fullName, accountNo, retypeAccount, bankName, ifscCode, panCard, aadharNo;
    Button submit;
    DatabaseConnectivity databaseConnectivity = new DatabaseConnectivity();
    SharedPrefManager sharedPrefManager;
    String loggedInUserNumber;
    ImageView imageViewPan;
    Bitmap bitmap;
    String fileName = "";
    String pan_image_path = "";
    String code = "";

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
        imageViewPan = findViewById(R.id.pancard_img);
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
        backPress.setOnClickListener(view -> onBackPressed());

        camera1.setOnClickListener(view -> {
            callCamera();

        });

        submit.setOnClickListener(view -> buttonValidation());
    }

    private void callCamera() {
        code = "panImage";
        ImagePicker.Companion.with(KYCActivity.this).crop().cropFreeStyle().
                maxResultSize(1080,1080,true).provider(ImageProvider.BOTH).createIntentFromDialog((Function1)(new Function1(){
            public Object invoke(Object var1) {
                this.invoke((Intent) var1);
                return Unit.INSTANCE;
            }

            public final void invoke(@NotNull Intent it) {
                Intrinsics.checkNotNullParameter(it, "it");
                launcher.launch(it);
            }
        }));


    }

    ActivityResultLauncher<Intent> launcher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (ActivityResult result) -> {
                if (result.getResultCode() == RESULT_OK) {
                    Uri uri = result.getData().getData();
                    pan_image_path = FileUriUtils.INSTANCE.getRealPath(this, uri);
                    InputStream inputStream = null;
                    try {
                        inputStream = getContentResolver().openInputStream(result.getData().getData());
                        bitmap = BitmapFactory.decodeStream(inputStream);
                        imageViewPan.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                } else if (result.getResultCode() == ImagePicker.RESULT_ERROR) {

                }
            });


    private void imageUpload() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference uploader = storage.getReference("KYCDetailsImage/" + loggedInUserNumber + "/" + fileName);
        ByteArrayOutputStream toUpload = new ByteArrayOutputStream();
        Bitmap.createScaledBitmap(bitmap, 400, 600, false)
                .compress(Bitmap.CompressFormat.JPEG, 100, toUpload);
        byte[] result = toUpload.toByteArray();
        uploader.putBytes(result).addOnSuccessListener(taskSnapshot -> uploader.getDownloadUrl().addOnSuccessListener(uri -> {
        }));
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
            }
            else if (accountNo.getText().toString().trim().length() <=10) {
                accountNo.setError("Please enter correct account no.");
                accountNo.requestFocus();
                isValid = false;
            }

            else if (retypeAccount.getText().toString().trim().length() == 0
                    || retypeAccount.getText().toString().trim() == accountNo.getText().toString().trim()) {
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
            }
            else if (ifscCode.getText().toString().trim().length() <= 10) {
                ifscCode.setError("Please enter correct IFSC Code");
                ifscCode.requestFocus();
                isValid = false;
            }
            else if (panCard.getText().toString().trim().length() == 0) {
                panCard.setError("Please enter PANCard No.");
                panCard.requestFocus();
                isValid = false;
            }
            else if (panCard.getText().toString().trim().length() <10) {
                panCard.setError("Please enter correct PANCard No.");
                panCard.requestFocus();
                isValid = false;
            }
            else if (imageViewPan.getDrawable() == null) {
                databaseConnectivity.showAlertDialog("Alert", "Click Picture of PANCard", false, this);
                isValid = false;
            } else if (aadharNo.getText().toString().trim().length() == 0) {
                aadharNo.setError("Please enter Aadhar");
                aadharNo.requestFocus();
                isValid = false;
            }
            else if (aadharNo.getText().toString().trim().length() >12) {
                aadharNo.setError("Please enter correct Aadhar");
                aadharNo.requestFocus();
                isValid = false;
            }
            else {
                insertKYCDetails();
                send_kyc_Details_OnServer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }

    private void send_kyc_Details_OnServer() {
        Log.d("Amit","Value "+pan_image_path);
        HelperData.uploadFile(KYCActivity.this,HelperData.UserId,fullName.getText().toString(),accountNo.getText().toString(),
                ifscCode.getText().toString(),bankName.getText().toString(),aadharNo.getText().toString(),panCard.getText().toString(),fileName);
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
        data.put("status", 1);

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