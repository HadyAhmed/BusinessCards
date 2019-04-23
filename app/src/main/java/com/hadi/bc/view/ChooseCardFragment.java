package com.hadi.bc.view;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hadi.bc.adapter.ChooseCardAdapter;
import com.hadi.bc.databinding.FragmentChooseCardBinding;
import com.hadi.bc.model.UserCard;

import java.util.ArrayList;
import java.util.List;


public class ChooseCardFragment extends Fragment implements ChooseCardAdapter.OnCardClickListener {
    private static final String TAG = "ChooseCardFragmentTag";
    private ChooseCardAdapter cardAdapter = new ChooseCardAdapter(this);
    private Context context;
    private FragmentChooseCardBinding chooseCardBinding;
    private List<UserCard> cardItemList = new ArrayList<>();

    private DatabaseReference userDatabaseReference;
    private OnCardClickListenerChoice clickListenerChoice;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        try {
            this.clickListenerChoice = (OnCardClickListenerChoice) context;
        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: " + e.getMessage());
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        chooseCardBinding = FragmentChooseCardBinding.inflate(inflater, container, false);

        if (getArguments() != null) {
            UserCard cardItem = getArguments().getParcelable("cardItem");
            initialUi(cardItem);
        }


        return chooseCardBinding.getRoot();
    }

    public ChooseCardFragment() {
        // Required empty public constructor
    }

    private void initialUi(final UserCard card) {
        String uid = FirebaseAuth.getInstance().getUid();
        if (uid != null) {
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference frontCardsReference = firebaseDatabase.getReference().child("cards").child("front");

            userDatabaseReference = firebaseDatabase.getReference().child("user_cards").child(uid);

            chooseCardBinding.cloudCardsRv.setLayoutManager(new LinearLayoutManager(context));
            chooseCardBinding.cloudCardsRv.setAdapter(cardAdapter);
            chooseCardBinding.cloudCardsRv.setItemViewCacheSize(10);

            ChildEventListener childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                    cardItemList.add(dataSnapshot.getValue(UserCard.class));
                    for (UserCard cards : cardItemList) {
                        cards.setCardAddress(card.getCardAddress());
                        cards.setCardSlogan(card.getCardSlogan());
                        cards.setCardEmail(card.getCardEmail());
                        cards.setCardNameHolder(card.getCardNameHolder());
                        cards.setCardOccupation(card.getCardOccupation());
                        cards.setCardPhoneNumber(card.getCardPhoneNumber());
                        cards.setCardWebsite(card.getCardWebsite());
                        cards.setCardTitle(card.getCardTitle());
                    }
                    cardAdapter.setCardItems(cardItemList);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {
                    //
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    //
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {
                    //
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    //
                }
            };

            frontCardsReference.addChildEventListener(childEventListener);

        } else {
            Toast.makeText(context, "Account Authentication was failed", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onCardClick(View v, UserCard cardItem) {
        userDatabaseReference.push().setValue(cardItem);
        clickListenerChoice.moveToMainActivity();
    }

    public interface OnCardClickListenerChoice {
        void moveToMainActivity();
    }
}
