package com.hadi.bc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.hadi.bc.databinding.BusinessCardItemBinding;
import com.hadi.bc.model.UserCard;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChooseCardAdapter extends RecyclerView.Adapter<ChooseCardAdapter.CardViewHolder> {
    private LayoutInflater inflater;
    private List<UserCard> cardItems;
    private OnCardClickListener cardClickListener;

    public ChooseCardAdapter(OnCardClickListener cardClickListener) {
        this.cardClickListener = cardClickListener;
    }

    public void setCardItems(List<UserCard> cardItems) {
        this.cardItems = cardItems;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        UserCard item = cardItems.get(position);
        holder.setCardItem(item);
        holder.setCardItemClickListener(cardClickListener);
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        return new CardViewHolder(BusinessCardItemBinding.inflate(inflater, parent, false));
    }

    public interface OnCardClickListener {
        void onCardClick(View view, UserCard cardItem);
    }

    @Override
    public int getItemCount() {
        if (cardItems == null) {
            return 0;
        } else {
            return cardItems.size();
        }
    }

    @BindingAdapter("cardDesignUrl")
    public static void setCardImage(ImageView imageView, String cardUrl) {
        Picasso.get().load(cardUrl).into(imageView);
    }

    class CardViewHolder extends RecyclerView.ViewHolder {
        private BusinessCardItemBinding cardItemBinding;

        CardViewHolder(@NonNull BusinessCardItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.cardItemBinding = itemBinding;
        }

        void setCardItem(UserCard cardItem) {
            cardItemBinding.setBusinessCardInfo(cardItem);
        }

        void setCardItemClickListener(OnCardClickListener cardItemClickListener) {
            this.cardItemBinding.setCardClickListener(cardItemClickListener);
        }
    }
}
