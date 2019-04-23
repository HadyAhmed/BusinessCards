package com.hadi.bc.view;


import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.hadi.bc.R;
import com.hadi.bc.databinding.FragmentAddCardBinding;
import com.hadi.bc.model.UserCard;


public class AddCardFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    private FragmentAddCardBinding cardInformationBinding;

    public AddCardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        cardInformationBinding = FragmentAddCardBinding.inflate(inflater, container, false);

        cardInformationBinding.cardNameEt.addTextChangedListener(setTextViewListener(cardInformationBinding.cardTitle));
        cardInformationBinding.cardNameHolderEt.addTextChangedListener(setTextViewListener(cardInformationBinding.cardNameHolderTv));
        cardInformationBinding.occupationEt.addTextChangedListener(setTextViewListener(cardInformationBinding.occupationTv));
        cardInformationBinding.phoneNumberEt.addTextChangedListener(setTextViewListener(cardInformationBinding.phoneNumberTv));
        cardInformationBinding.emailEt.addTextChangedListener(setTextViewListener(cardInformationBinding.emailTv));
        cardInformationBinding.addressEt.addTextChangedListener(setTextViewListener(cardInformationBinding.addressTv));
        cardInformationBinding.webSiteEt.addTextChangedListener(setTextViewListener(cardInformationBinding.websiteTv));
        cardInformationBinding.sloganEt.addTextChangedListener(setTextViewListener(cardInformationBinding.sloganTv));

        cardInformationBinding.nextBtn.setOnClickListener(this);
        return cardInformationBinding.getRoot();
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.nextBtn) {
            UserCard cardItem = new UserCard(
                    cardInformationBinding.addressTv.getText().toString(),
                    cardInformationBinding.emailTv.getText().toString(),
                    cardInformationBinding.cardNameHolderTv.getText().toString(),
                    cardInformationBinding.occupationTv.getText().toString(),
                    cardInformationBinding.phoneNumberTv.getText().toString(),
                    cardInformationBinding.sloganTv.getText().toString(),
                    cardInformationBinding.cardTitle.getText().toString(),
                    cardInformationBinding.websiteTv.getText().toString()
            );
            Bundle cardInfoBundle = new Bundle();
            cardInfoBundle.putParcelable("cardItem", cardItem);
            Navigation.findNavController(v).navigate(R.id.chooseCardFragment, cardInfoBundle);
        }
    }
}
