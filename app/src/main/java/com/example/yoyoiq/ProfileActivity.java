package com.example.yoyoiq;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
    GridView gridView;
    String[] wordPositionTv = {"Skill Score"};
    int[] positionIv = {R.drawable.ic_baseline_perm_identity_24};


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

        //Profile Share-----------------------------------------
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "YoYoIQ");
                    String shareMessage = "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

//        gridView = findViewById(R.id.gridView);
//        ProfileAdapter adapter = new ProfileAdapter(ProfileActivity.this, wordPositionTv, positionIv);
//        gridView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.myInfo:
                return true;
            case R.id.searchProfile:
                return true;
        }
        return super.onOptionsItemSelected(item);
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