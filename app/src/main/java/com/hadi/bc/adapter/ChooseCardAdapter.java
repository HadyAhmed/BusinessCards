package com.hadi.bc.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.hadi.bc.databinding.BusinessCardItemBinding;
import com.hadi.bc.model.CardItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChooseCardAdapter extends RecyclerView.Adapter<ChooseCardAdapter.CardViewHolder> {
    private LayoutInflater inflater;
    private List<CardItem> cardItems;
    private OnCardClickListener cardClickListener;

    public ChooseCardAdapter(OnCardClickListener cardClickListener) {
        this.cardClickListener = cardClickListener;
    }

    public interface OnCardClickListener {
        void onCardClick(CardItem cardItem);
    }

    public void setCardItems(List<CardItem> cardItems) {
        this.cardItems = cardItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        return new CardViewHolder(BusinessCardItemBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardItem item = cardItems.get(position);
        holder.setCardItem(item);
        holder.setCardItemClickListener(cardClickListener);
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

        void setCardItem(CardItem cardItem) {
            cardItemBinding.setBusinessCardInfo(cardItem);
        }

        void setCardItemClickListener(OnCardClickListener cardItemClickListener) {
            this.cardItemBinding.setCardClickListener(cardItemClickListener);
        }
    }
}
