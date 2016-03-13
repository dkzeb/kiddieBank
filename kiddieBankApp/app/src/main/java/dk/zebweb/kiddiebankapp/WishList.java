package dk.zebweb.kiddiebankapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class WishList extends AppCompatActivity {


    final ArrayList<Wish> w = new ArrayList<Wish>();
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        progress = new ProgressDialog(this);
        progress.setTitle("Henter ønsker");
        progress.setMessage("Vent venligst...");
        progress.show();

        SharedPreferences prefs = getSharedPreferences("kiddiebank", 0);
        String child_id = prefs.getString("uid", null);//
        final Firebase ref = new Firebase(getResources().getString(R.string.firebase_url));
        ref.child("wishes").child(child_id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(this.getClass().getSimpleName(), dataSnapshot.toString());
                //ArrayList<Wish> wishList = new ArrayList<>();
                //wishList.add(new Wish("free", "free", "free", 0, 0, "free"));
                for (DataSnapshot wishSnapshot : dataSnapshot.getChildren()) {
                    Log.d(this.getClass().getSimpleName(), wishSnapshot.toString());
                    Wish wish = wishSnapshot.getValue(Wish.class);
                    Log.d(this.getClass().getSimpleName(), wish.toString());
                    // wishList.add(wish);
                    w.add(wish);
                }

                System.out.println(w);
                //w = wishList.toArray(new Wish[wishList.size()]);
                populateWishList();
                progress.dismiss();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d(this.getClass().getSimpleName(), "Error during data-retrieve attempt. Code: " + firebaseError.getCode() + " Message:" + firebaseError.getMessage() + "\nDetails: " + firebaseError.getDetails());
                Toast.makeText(getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void populateWishList(){

        final GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new WishListAdapter(this, w.toArray(new Wish[w.size()])));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Wish w = (Wish) gridview.getItemAtPosition(position);

                Gift free = (Gift) getIntent().getSerializableExtra(AppConstants.GIFT_OBJECT);
                System.out.println("GIFT: "+free);

                System.out.println("Wish ID: "+w.getId());
                Intent i = new Intent(getApplicationContext(), DesignateMoney.class);
                //i.putExtra("wishID", w.getId());
                i.putExtra("gift", free);

                i.putExtra("wish", w);
                startActivity(i);
            }
        });
    }
}
