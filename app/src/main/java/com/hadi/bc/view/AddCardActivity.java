package com.hadi.bc.view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.hadi.bc.R;
import com.hadi.bc.databinding.ActivityAddCardBinding;

public class AddCardActivity extends AppCompatActivity {
    private ActivityAddCardBinding addCardBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addCardBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_card);

        addCardBinding.addCardToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
