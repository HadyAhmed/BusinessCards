package com.hadi.bc.view;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.hadi.bc.R;
import com.hadi.bc.databinding.FragmentAddCardBinding;
import com.hadi.bc.model.CardItem;
import com.squareup.picasso.Picasso;


public class AddCardFragment extends Fragment implements View.OnClickListener {
    private static final int PICK_IMAGE_RC = 2006;
    private static final int READ_EXTERNAL_STORAGE_RC = 2007;
    private Context context;
    private String imageUri;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
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

        cardInformationBinding.cardNameEt.addTextChangedListener(setTextViewListener(cardInformationBinding.cardName));
        cardInformationBinding.cardNameHolderEt.addTextChangedListener(setTextViewListener(cardInformationBinding.cardNameHolderTv));
        cardInformationBinding.occupationEt.addTextChangedListener(setTextViewListener(cardInformationBinding.occupationTv));
        cardInformationBinding.phoneNumberEt.addTextChangedListener(setTextViewListener(cardInformationBinding.phoneNumberTv));
        cardInformationBinding.emailEt.addTextChangedListener(setTextViewListener(cardInformationBinding.emailTv));
        cardInformationBinding.addressEt.addTextChangedListener(setTextViewListener(cardInformationBinding.addressTv));
        cardInformationBinding.webSiteEt.addTextChangedListener(setTextViewListener(cardInformationBinding.websiteTv));
        cardInformationBinding.sloganEt.addTextChangedListener(setTextViewListener(cardInformationBinding.sloganTv));

        cardInformationBinding.nextBtn.setOnClickListener(this);
        cardInformationBinding.pickUpImageBtn.setOnClickListener(this);
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
            CardItem cardItem = new CardItem(cardInformationBinding.cardName.getText().toString(),
                    cardInformationBinding.cardNameHolderTv.getText().toString(),
                    cardInformationBinding.occupationTv.getText().toString(),
                    cardInformationBinding.phoneNumberTv.getText().toString(),
                    cardInformationBinding.emailTv.getText().toString(),
                    cardInformationBinding.addressTv.getText().toString(),
                    cardInformationBinding.websiteTv.getText().toString(),
                    cardInformationBinding.sloganTv.getText().toString(),
                    imageUri
            );
            Bundle cardInfoBundle = new Bundle();
            cardInfoBundle.putParcelable("cardItem", cardItem);
            Navigation.findNavController(v).navigate(R.id.chooseCardFragment, cardInfoBundle);
        } else if (v.getId() == R.id.pickUpImageBtn) {
            int externalPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
            if (externalPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_RC);
            } else {
                chooseImage();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_RC) {
            if (data != null) {
                Picasso.get().load(data.getData()).into(cardInformationBinding.companyLogo);
            } else {
                Toast.makeText(context, "Error picking image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_EXTERNAL_STORAGE_RC) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                chooseImage();
            } else {
                Toast.makeText(context, "Permission was canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*").setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Pick up image"), PICK_IMAGE_RC);
    }
}
