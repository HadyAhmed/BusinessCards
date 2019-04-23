package com.hadi.bc.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
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
    private static final String TAG = "CardDetailActivityTag";
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
            Toast.makeText(this, "Something wrong happened", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void initUi(UserCard cardItem) {
        cardDetailBinding.setBusinessCardInfo(cardItem);

        cardDetailBinding.cardNameEt.setText(cardItem.getCardTitle());
        cardDetailBinding.cardNameHolderEt.setText(cardItem.getCardNameHolder());
        cardDetailBinding.occupationEt.setText(cardItem.getCardOccupation());
        cardDetailBinding.phoneNumberEt.setText(cardItem.getCardPhoneNumber());
        cardDetailBinding.emailEt.setText(cardItem.getCardEmail());
        cardDetailBinding.addressEt.setText(cardItem.getCardAddress());
        cardDetailBinding.webSiteEt.setText(cardItem.getCardWebsite());
        cardDetailBinding.sloganEt.setText(cardItem.getCardSlogan());

        cardDetailBinding.cardNameHolderEt.addTextChangedListener(setTextViewListener(cardDetailBinding.cardNameHolderTv));
        cardDetailBinding.occupationEt.addTextChangedListener(setTextViewListener(cardDetailBinding.occupationTv));
        cardDetailBinding.phoneNumberEt.addTextChangedListener(setTextViewListener(cardDetailBinding.phoneNumberTv));
        cardDetailBinding.emailEt.addTextChangedListener(setTextViewListener(cardDetailBinding.emailTv));
        cardDetailBinding.addressEt.addTextChangedListener(setTextViewListener(cardDetailBinding.addressTv));
        cardDetailBinding.webSiteEt.addTextChangedListener(setTextViewListener(cardDetailBinding.websiteTv));
        cardDetailBinding.sloganEt.addTextChangedListener(setTextViewListener(cardDetailBinding.sloganTv));
    }

    private TextWatcher setTextViewListener(final TextView triggeredTv) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                triggeredTv.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
            }
        };
    }

}
