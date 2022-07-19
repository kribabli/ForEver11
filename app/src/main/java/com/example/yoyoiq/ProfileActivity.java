package com.example.yoyoiq;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yoyoiq.common.SharedPrefManager;

public class ProfileActivity extends AppCompatActivity {
    TextView backPress;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        backPress = findViewById(R.id.backPress);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        Log.d("Amit","Value "+ sharedPrefManager.getUserData().getEmailId());
        Log.d("Amit","Value "+ sharedPrefManager.getUserData().getUserName());
        Log.d("Amit","Value "+ sharedPrefManager.getUserData().getMobileNo());
        Log.d("Amit","Value "+ sharedPrefManager.getUserData().getPassword());



        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}