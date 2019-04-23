package com.hadi.bc.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hadi.bc.R;
import com.hadi.bc.databinding.ActivityAddCardBinding;

public class AddCardActivityChoice extends AppCompatActivity implements ChooseCardFragment.OnCardClickListenerChoice {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddCardBinding addCardBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_card);

        addCardBinding.addCardToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void moveToMainActivity() {
        startActivity(new Intent(AddCardActivityChoice.this, MainActivity.class));
        finish();
    }
}
