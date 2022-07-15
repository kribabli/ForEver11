package com.example.yoyoiq.WalletPackage;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.yoyoiq.Modal.SharedPrefManager;
import com.example.yoyoiq.R;
import com.example.yoyoiq.common.DatabaseConnectivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KYCActivity extends AppCompatActivity {
    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 101;
    TextView backPress, camera1;
    EditText fullName, accountNo, retypeAccount, bankName, ifscCode, panCard, aadharNo;
    Button submit;
    DatabaseConnectivity databaseConnectivity = new DatabaseConnectivity();
    SharedPrefManager sharedPrefManager;
    String loggedInUserNumber;
    ImageView imageViewPan;
    Bitmap  bitmap;
    String fileName = "";

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
        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        camera1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkAndRequestPermissions(KYCActivity.this)) {
                    chooseImage(KYCActivity.this);
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonValidation();
            }
        });
    }

    private boolean checkAndRequestPermissions(final Activity context) {
        int WExtstorePermission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int cameraPermission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (WExtstorePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded
                    .add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(context, listPermissionsNeeded
                            .toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    private void chooseImage(Context context) {
        // create a menuOption Array
        final CharSequence[] optionsMenu = {"Take Photo", "Choose from Gallery", "Exit"};
        // create a dialog for showing the optionsMenu
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // set the items in builder
        builder.setItems(optionsMenu, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (optionsMenu[i].equals("Take Photo")) {
                    // Open the camera and get the photo
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);
                } else if (optionsMenu[i].equals("Choose from Gallery")) {
                    // choose from  external storage
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);
                } else if (optionsMenu[i].equals("Exit")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS:
                if (ContextCompat.checkSelfPermission(KYCActivity.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),
                                    "FlagUp Requires Access to Camara.", Toast.LENGTH_SHORT)
                            .show();
                } else if (ContextCompat.checkSelfPermission(KYCActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),
                            "FlagUp Requires Access to Your Storage.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    chooseImage(KYCActivity.this);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        bitmap = (Bitmap) data.getExtras().get("data");
                        fileName="KycPanImage.jpg";
                        imageViewPan.setImageBitmap(bitmap);
                        imageUpload();
                    }
                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();
                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                String[] file =picturePath.toString().split("/");
                                fileName = file[file.length - 1];
                                imageViewPan.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                                InputStream inputStream = null;
                                try {
                                    inputStream = getContentResolver().openInputStream(selectedImage);
                                    bitmap = BitmapFactory.decodeStream(inputStream);
                                    imageUpload();

                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    break;
            }
        }
    }

    private void imageUpload() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference uploader = storage.getReference("KYCDetailsImage/" + loggedInUserNumber + "/"+fileName);
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