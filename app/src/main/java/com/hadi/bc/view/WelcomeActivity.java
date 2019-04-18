package com.hadi.bc.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.google.firebase.auth.FirebaseAuth;
import com.hadi.bc.R;
import com.hadi.bc.WelcomeItemStaticData;
import com.hadi.bc.adapter.MainViewPagerAdapter;
import com.hadi.bc.databinding.ActivityWelcomeBinding;
import com.rd.animation.type.AnimationType;

public class WelcomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private ActivityWelcomeBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (FirebaseAuth.getInstance().getCurrentUser()!= null) {
            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            finish();
        }
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);


        mainBinding.viewPager.addOnPageChangeListener(this);
        mainBinding.viewPager.setAdapter(new MainViewPagerAdapter(WelcomeItemStaticData.getWelcomeItems()));

        mainBinding.pageIndicatorView.setAnimationType(AnimationType.WORM);
        mainBinding.pageIndicatorView.setCount(WelcomeItemStaticData.getWelcomeItems().size());

        mainBinding.startBtn.setOnClickListener(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mainBinding.pageIndicatorView.setSelection(position);
        mainBinding.startBtn.setVisibility(position == WelcomeItemStaticData.getWelcomeItems().size() - 1 ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void onPageSelected(int position) {
        //
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
    }

}
