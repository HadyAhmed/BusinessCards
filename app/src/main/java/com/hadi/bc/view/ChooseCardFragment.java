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

import com.hadi.bc.adapter.ChooseCardAdapter;
import com.hadi.bc.databinding.FragmentChooseCardBinding;
import com.hadi.bc.model.CardItem;


public class ChooseCardFragment extends Fragment implements ChooseCardAdapter.OnCardClickListener {
    private ChooseCardAdapter cardAdapter = new ChooseCardAdapter(this);
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public ChooseCardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentChooseCardBinding chooseCardBinding = FragmentChooseCardBinding.inflate(inflater, container, false);

        if (getArguments() != null) {
            CardItem cardItem = getArguments().getParcelable("cardItem");
            if (cardItem != null) {
                Log.d("cardItem", "onCreateView: " + cardItem.toString());
            }
        }

        chooseCardBinding.cloudCardsRv.setLayoutManager(new LinearLayoutManager(context));
        chooseCardBinding.cloudCardsRv.setAdapter(cardAdapter);
        chooseCardBinding.cloudCardsRv.setItemViewCacheSize(10);

        return chooseCardBinding.getRoot();
    }

    @Override
    public void onCardClick(CardItem cardItem) {
        Toast.makeText(context, cardItem.getCardNameHolder(), Toast.LENGTH_SHORT).show();
    }
}
