package com.example.yoyoiq.KYC;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
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
import com.example.yoyoiq.common.SessionManager;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class KYCActivity extends AppCompatActivity   {
    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 101;
    TextView backPress, camera1,date_of_birth;
    EditText fullName, accountNo, retypeAccount, bankName, ifscCode, panCard, aadharNo,address_ed;
    Button submit;
    DatabaseConnectivity common=DatabaseConnectivity.getInstance();
    SharedPrefManager sharedPrefManager;
    String loggedInUserNumber;
    ImageView imageViewPan;
    Bitmap bitmap;
    String fileName = "";
    String pan_image_path = "";
    String code = "";
    SessionManager sessionManager;
    private DatePickerDialog datePickerDialog;
    private Boolean clickable = true;
    String date="",year="",month="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kycactivity);
        initMethod();
        setAction();
        loggedInUserNumber = sharedPrefManager.getUserData().getMobile_no();
        sessionManager=new SessionManager(getApplicationContext());

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
        address_ed = findViewById(R.id.address_ed);
        date_of_birth = findViewById(R.id.date_of_birth);
    }

    private void setAction() {
        backPress.setOnClickListener(view -> onBackPressed());

        camera1.setOnClickListener(view -> {
            callCamera();

        });

        submit.setOnClickListener(view -> buttonValidation());

        date_of_birth.setOnClickListener(view -> {
            if(clickable){
                clickable=false;
                final Calendar calender = Calendar.getInstance();
                int years = calender.get(Calendar.YEAR);
                int monthInt = calender.get(Calendar.MONTH);
                int day = calender.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(KYCActivity.this, (view1, years1, monthsOfYear, dayOfMonths) -> {

                    year = "" + years1;
                    date = (dayOfMonths + "-" + (monthsOfYear + 1) + "-" + year);
                    try {
                        date = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        month = new SimpleDateFormat("MMMM").format(new SimpleDateFormat("yyyy-MM-dd").parse(date));

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    date_of_birth.setText(date);
                    clickable = true;
                }, years, monthInt, day);
                datePickerDialog.show();


            }


        });
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
                    pan_image_path= String.valueOf(uri);
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
            else if(date_of_birth.getText().toString().length()==0){
                date_of_birth.setError("Please enter correct account no.");
                date_of_birth.requestFocus();
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
            }
            else if (address_ed.getText().toString().trim().length() == 0) {
                address_ed.setError("Please enter address ");
                address_ed.requestFocus();
                isValid = false;
            }

            else if (ifscCode.getText().toString().trim().length() == 0) {
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
                common.showAlertDialog("Alert", "Click Picture of PANCard", false, this);
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
                common.setProgressDialog("","Loading..",KYCActivity.this,KYCActivity.this);
                send_kyc_Details_OnServer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }

    private void send_kyc_Details_OnServer() {
        HelperData.uploadFile(KYCActivity.this,sessionManager.getUserData().getUser_id(),fullName.getText().toString(),accountNo.getText().toString(),
                ifscCode.getText().toString(),bankName.getText().toString(),date_of_birth.getText().toString(),address_ed.getText().toString(),aadharNo.getText().toString(),panCard.getText().toString(),pan_image_path);
        common.closeDialog(KYCActivity.this);
        showDialog("Details Saved..", true);
        fullName.setText("");
        accountNo.setText("");
        retypeAccount.setText("");
        bankName.setText("");
        ifscCode.setText("");
        panCard.setText("");
        aadharNo.setText("");

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