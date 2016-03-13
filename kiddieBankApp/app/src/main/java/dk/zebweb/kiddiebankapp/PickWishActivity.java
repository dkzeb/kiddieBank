package dk.zebweb.kiddiebankapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class PickWishActivity extends AppCompatActivity {

    private final String TAG = PickWishActivity.class.getSimpleName();

    private String child_id = "";
    private String child_name = "";

    private PickWishListAdapter pickWishListAdapter;

    private Wish wishes[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_wish);

        Intent intent = getIntent();
        child_id = intent.getStringExtra(AppConstants.CHILD_ID);
        child_name = intent.getStringExtra(AppConstants.CHILD_NAME);


        TextView textView = (TextView) findViewById(R.id.activity_pick_wish_text);
        textView.setText("Vælg hvad " + child_name + " skal have:");

        final Firebase ref = new Firebase(getResources().getString(R.string.firebase_url));
        ref.child("wishes").child(child_id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, dataSnapshot.toString());
                ArrayList<Wish> wishList = new ArrayList<>();
                wishList.add(new Wish("free", "free", "free", 0, 0, "free"));
                for (DataSnapshot wishSnapshot : dataSnapshot.getChildren()) {
                    Log.d(TAG, wishSnapshot.toString());
                    Wish wish = wishSnapshot.getValue(Wish.class);
                    Log.d(TAG, wish.toString());
                    wishList.add(wish);
                }
                wishes = wishList.toArray(new Wish[wishList.size()]);
                populateWishListView();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d(TAG, "Error during data-retrieve attempt. Code: " + firebaseError.getCode() + " Message:" + firebaseError.getMessage() + "\nDetails: " + firebaseError.getDetails());
                Toast.makeText(getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void populateWishListView(){
        GridView gridview = (GridView) findViewById(R.id.activity_pick_wish_gridview);
        pickWishListAdapter = new PickWishListAdapter(this, wishes);
        gridview.setAdapter(pickWishListAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
                Wish wish = (Wish) pickWishListAdapter.getItem(position);
                String item_id = wish.getId();
                Intent intent = new Intent(getApplicationContext(), ChooseAmountActivity.class);
                intent.putExtra(AppConstants.CHILD_ID, child_id);
                intent.putExtra(AppConstants.CHILD_NAME, child_name);
                intent.putExtra(AppConstants.ITEM_ID, item_id);
                startActivity(intent);
            }
        });
    }
}
