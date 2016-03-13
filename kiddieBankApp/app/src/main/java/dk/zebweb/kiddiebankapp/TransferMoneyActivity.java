package dk.zebweb.kiddiebankapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class TransferMoneyActivity extends AppCompatActivity {


    private static final String TAG = TransferMoneyActivity.class.getSimpleName();

    private GridView kidsList;
    private KidsListAdapter kidsAdapter;
    //private ArrayAdapter<User> kidsAdapter;
    private ArrayList<User> children;

    private SessionManager sessionManager;

    private ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_money);
        sessionManager = new SessionManager(this);

        progress = new ProgressDialog(this);
        progress.setTitle("Henter børn");
        progress.setMessage("Vent venligst...");
        progress.show();

        children = new ArrayList<>();

        kidsList = (GridView) findViewById(R.id.kids_list);
        final Firebase ref = new Firebase(getResources().getString(R.string.firebase_url));

        ref.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, dataSnapshot.toString());
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                    Log.d(TAG, userSnapshot.toString());
                    User user = userSnapshot.getValue(User.class);
                    Log.d(TAG, user.toString());
                    children.add(user);
                }
                populateChildrenListView();
                progress.dismiss();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d(TAG, "Error during data-retrieve attempt. Code: " + firebaseError.getCode() + " Message:" + firebaseError.getMessage() + "\nDetails: " + firebaseError.getDetails());
                Toast.makeText(getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateChildrenListView(){
        //kidsAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_2, children);
        kidsAdapter = new KidsListAdapter(getApplicationContext(), children);

        kidsList.setAdapter(kidsAdapter);
        kidsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(TransferMoneyActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                User child = (User) kidsAdapter.getItem(position);
                String child_id = child.getId();
                String child_name = child.getName();
                Intent intent = new Intent(getApplicationContext(), PickWishActivity.class);
                intent.putExtra(AppConstants.CHILD_ID, child_id);
                intent.putExtra(AppConstants.CHILD_NAME, child_name);
                startActivity(intent);
            }
        });
    }
}
