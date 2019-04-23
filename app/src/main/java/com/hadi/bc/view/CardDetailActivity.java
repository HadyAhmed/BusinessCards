package com.hadi.bc.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.databinding.DataBindingUtil;

import com.hadi.bc.R;
import com.hadi.bc.databinding.ActivityCardDetailBinding;
import com.hadi.bc.model.UserCard;

public class CardDetailActivity extends AppCompatActivity {
    public static final String CARD_EXTRA = "card_item";
    private ActivityCardDetailBinding cardDetailBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cardDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_card_detail);

        cardDetailBinding.detailToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(CardDetailActivity.this);
            }
        });

        UserCard cardItem = getIntent().getParcelableExtra(CARD_EXTRA);
        if (cardItem != null) {
            initUi(cardItem);
        } else {
            Toast.makeText(this, getResources().getString(R.string.wrong_card_msg), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void initUi(UserCard cardItem) {
        cardDetailBinding.setBusinessCardInfo(cardItem);

    }

}
