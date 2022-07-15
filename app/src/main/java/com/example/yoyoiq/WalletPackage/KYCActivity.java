package com.example.yoyoiq.WalletPackage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yoyoiq.Modal.SharedPrefManager;
import com.example.yoyoiq.R;
import com.example.yoyoiq.common.DatabaseConnectivity;
import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class KYCActivity extends AppCompatActivity {
    private static final int PICK_FROM_CAMERA = 1;
    TextView backPress, camera1, camera2;
    EditText fullName, accountNo, retypeAccount, bankName, ifscCode, panCard, aadharNo;
    Button submit;
    DatabaseConnectivity databaseConnectivity = new DatabaseConnectivity();
    SharedPrefManager sharedPrefManager;
    String loggedInUserNumber;
    ImageView imageViewAadhar, imageViewPan;
    Bitmap bitmap;
    String outPutfileUri;

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
        imageViewAadhar = findViewById(R.id.aadharImage);
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
        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        camera1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callCamera();
            }
        });

        camera2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callCamera();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonValidation();
            }
        });
    }

    private void callCamera() {
        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(), "MyPhoto.jpg");
        outPutfileUri = String.valueOf(Uri.fromFile(file));
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri);
        startActivityForResult(captureIntent, PICK_FROM_CAMERA);
    }

    private void imageUpload(String imageName) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference uploader = storage.getReference("KYCDetailsImage/" + loggedInUserNumber + "/" + imageName);
        ByteArrayOutputStream toUpload = new ByteArrayOutputStream();
        Bitmap.createScaledBitmap(bitmap, 400, 600, false)
                .compress(Bitmap.CompressFormat.JPEG, 100, toUpload);
        byte[] result = toUpload.toByteArray();
        uploader.putBytes(result).addOnSuccessListener(taskSnapshot -> uploader.getDownloadUrl().addOnSuccessListener(uri -> {
        }));
    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (ActivityResult result) -> {
        if (result.getResultCode() == RESULT_OK) {
            String[] file = result.getData().getData().getPath().split("/");
            InputStream inputStream = null;
            try {
                inputStream = getContentResolver().openInputStream(result.getData().getData());
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewPan.setImageBitmap(bitmap);
                imageViewAadhar.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
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
            } else if (imageViewPan.getDrawable() == null) {
                databaseConnectivity.showAlertDialog("Alert", "Click Picture of PANCard", false, this);
                isValid = false;
            } else if (aadharNo.getText().toString().trim().length() == 0) {
                aadharNo.setError("Please enter Aadhar");
                aadharNo.requestFocus();
                isValid = false;
            } else if (imageViewAadhar.getDrawable() == null) {
                databaseConnectivity.showAlertDialog("Alert", "Click Picture of Aadhar", false, this);
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
                        imageUpload(imageViewAadhar.getTransitionName());
                        imageUpload(imageViewPan.getTransitionName());
                        showDialog("Details Saved..", true);
                    }
                });
    }

    private void upLoadToStorage(JSONObject expenseList) throws IOException {
        File output = File.createTempFile(String.valueOf(loggedInUserNumber), ".json");
        try (FileWriter writer = new FileWriter(output)) {
            writer.write(expenseList.toString());
            Uri uri = Uri.fromFile(output);
            StorageMetadata metadata = new StorageMetadata.Builder().setContentType("json").build();
            FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
            firebaseStorage.getReference().child("Common/Expenses").child(loggedInUserNumber + ".json").putFile(uri, metadata).
                    addOnSuccessListener(task -> {
                        if (task.getTask().isSuccessful()) {
//                            showDialog(" Entry Successfully...", true);

                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
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