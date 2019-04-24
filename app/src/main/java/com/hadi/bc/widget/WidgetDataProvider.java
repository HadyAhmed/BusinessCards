package com.hadi.bc.widget;

import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hadi.bc.R;
import com.hadi.bc.model.CardList;

import java.util.ArrayList;
import java.util.List;

public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {
    private static final String TAG = "WidgetDataProvider";
    private List<CardList> cardLists = new ArrayList<>();
    private Context context;


    public WidgetDataProvider(Context context) {
        this.context = context;
    }

    private void initializeData() {
        try {
            cardLists.clear();
            FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseUser user = auth.getCurrentUser();
            if (user != null) {
                DatabaseReference mainNode = FirebaseDatabase.getInstance().getReference().child("user_cards").child(user.getUid());
                mainNode.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            cardLists.add(snapshot.getValue(CardList.class));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

        } catch (NullPointerException e) {
            Log.e(TAG, "initializeData: " + e.getMessage());
        }
    }

    @Override
    public void onCreate() {
        initializeData();

    }

    @Override
    public void onDataSetChanged() {
        initializeData();

    }

    @Override
    public void onDestroy() {
        //
    }

    @Override
    public int getCount() {
        Log.d(TAG, "Total count is " + cardLists.size());
        return cardLists.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_card_item);
        remoteViews.setTextViewText(R.id.widget_enrolled, cardLists.get(i).getCardTitle());

        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}