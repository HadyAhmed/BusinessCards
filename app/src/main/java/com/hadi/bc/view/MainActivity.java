package com.hadi.bc.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hadi.bc.R;
import com.hadi.bc.adapter.ChooseCardAdapter;
import com.hadi.bc.databinding.ActivityMainBinding;
import com.hadi.bc.model.UserCard;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, ChooseCardAdapter.OnCardClickListener {
    private static final String TAG = "MainActivityTag";
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;


    private FirebaseDatabase database;
    private List<UserCard> cards = new ArrayList<>();

    private ChooseCardAdapter mAdapter = new ChooseCardAdapter(this);
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainBinding.mainToolbar.inflateMenu(R.menu.main_menu);
        mainBinding.mainToolbar.setOnMenuItemClickListener(this);

        new CheckForInternetConnection().execute();

        database = FirebaseDatabase.getInstance();

        mainBinding.cardRv.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.cardRv.setAdapter(mAdapter);


        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                } else {
                    initialOnSIgnInSuccessfully(user);
                }
            }
        };

        mainBinding.addCardFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddCardActivity.class));
            }
        });

    }

    private void initialOnSIgnInSuccessfully(FirebaseUser user) {
        DatabaseReference databaseReference = database.getReference().child("user_cards").child(user.getUid());

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    cards.add(snapshot.getValue(UserCard.class));
                }
                mAdapter.setCardItems(cards);
                mainBinding.emptyListIndication.setVisibility(cards.isEmpty() ? View.VISIBLE : View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
        databaseReference.addValueEventListener(valueEventListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (authStateListener != null) {
            firebaseAuth.addAuthStateListener(authStateListener);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
            authStateListener = null;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onCardClick(View view, UserCard cardItem) {
        startActivity(new Intent(MainActivity.this, CardDetailActivity.class)
                .putExtra(CardDetailActivity.CARD_EXTRA, cardItem));
    }

    private static class CheckForInternetConnection extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                URL url = new URL("https://google.com");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(10000);
                connection.connect();
                return connection.getResponseCode() == 200;
            } catch (IOException e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            Log.d(TAG, "is app connected " + aBoolean);
            super.onPostExecute(aBoolean);
        }
    }

}
