package com.example.yoyoiq;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.yoyoiq.common.HelperData;
import com.example.yoyoiq.common.SharedPrefManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    TextView backPress, userName, skillScore, share;
    SharedPrefManager sharedPrefManager;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    CircleImageView userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initMethod();
        setAction();
        setProfileDetails();
    }

    private void initMethod() {
        backPress = findViewById(R.id.backPress);
        userName = findViewById(R.id.userName);
        skillScore = findViewById(R.id.skillScore);
        userProfile = findViewById(R.id.profilePic);
        share = findViewById(R.id.share);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
    }

    private void setAction() {
        backPress.setOnClickListener(view -> onBackPressed());

        share.setOnClickListener(v -> share());
    }

    private void share() {

    }

    private void setProfileDetails() {
        userName.setText(HelperData.UserName);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (googleSignInAccount != null) {
            String UserName = googleSignInAccount.getDisplayName();
            String userEmail = googleSignInAccount.getEmail();
            Uri photoUrl = googleSignInAccount.getPhotoUrl();
            String id = googleSignInAccount.getId();
            userName.setText(UserName);

            Glide.with(this)
                    .load(photoUrl)
                    .into(userProfile);
        }
    }
}