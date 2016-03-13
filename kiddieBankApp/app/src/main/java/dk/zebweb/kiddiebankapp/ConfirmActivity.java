package dk.zebweb.kiddiebankapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.util.UUID;

public class ConfirmActivity extends AppCompatActivity {

    private final String TAG = ConfirmActivity.class.getSimpleName();

    private TextView confirmText;
    private Button confirmButton;

    private SessionManager sessionManager;

    private String child_id;
    private String child_name;
    private String item_id;
    private String item_name;
    private int item_price;
    private int item_balance;
    private int amount;

    Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        sessionManager = new SessionManager(this);

        Intent intent = getIntent();

        child_id = intent.getStringExtra(AppConstants.CHILD_ID);
        child_name = intent.getStringExtra(AppConstants.CHILD_NAME);
        item_id = intent.getStringExtra(AppConstants.ITEM_ID);
        item_name = intent.getStringExtra(AppConstants.ITEM_NAME);
        item_price = intent.getIntExtra(AppConstants.ITEM_PRICE, 0);
        item_balance = intent.getIntExtra(AppConstants.ITEM_BALANCE, 0);
        int amount = intent.getIntExtra(AppConstants.AMOUNT, 0);


        confirmText = (TextView) findViewById(R.id.activity_confirm_text);
        confirmButton = (Button) findViewById(R.id.activity_confirm_button);

        ref = new Firebase(getResources().getString(R.string.firebase_url));

        if(!child_id.equals("free")){
            ref.child("wishes").child(child_id).child(item_id).child("balance").setValue(item_balance + amount);
        }




        //TODO: Send some request to hardware.


        confirmText.setText("Du har overført " + amount + " kr., så " + child_name + " snart kan få en ny " + item_name);
        confirmButton.setOnClickListener(confirmListener());
    }



    private View.OnClickListener confirmListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Transaction was successful. User now returning to main screen.");
                String uuid = UUID.randomUUID().toString();
                ref.child("gift").child(child_id).child(uuid).setValue(new Gift(
                        uuid,
                        amount,
                        item_id,
                        item_name,
                        sessionManager.getUid(),
                        sessionManager.getName(),
                        (item_balance + amount > item_price)
                ));
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }
}
