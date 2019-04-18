package com.hadi.bc.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hadi.bc.R;
import com.hadi.bc.databinding.ActivitySettingsBinding;
import com.squareup.picasso.Picasso;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySettingsBinding settingsBinding = DataBindingUtil.setContentView(this, R.layout.activity_settings);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        settingsBinding.settingsToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        settingsBinding.setFirebaseUser(user);
        settingsBinding.signOuBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder signOutDialog = new AlertDialog.Builder(SettingsActivity.this);
        signOutDialog.setTitle("Sign Out!")
                .setMessage("Are your sure!?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AuthUI.getInstance().signOut(SettingsActivity.this);
                        startActivity(new Intent(SettingsActivity.this, WelcomeActivity.class));
                        finish();
                    }
                })
                .setNeutralButton("Cancel", null)
                .show();
    }

    @BindingAdapter("userImageSrc")
    public static void setUserImage(ImageView image, Uri userImageUrl) {
        Log.d("userImageUri", "setUserImage: " + userImageUrl);
        if (userImageUrl != null) {
            Picasso.get().load(userImageUrl).into(image);
        }
    }
}
