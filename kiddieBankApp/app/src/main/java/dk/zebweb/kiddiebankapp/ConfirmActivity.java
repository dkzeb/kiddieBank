package dk.zebweb.kiddiebankapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmActivity extends AppCompatActivity {

    private final String TAG = ConfirmActivity.class.getSimpleName();

    private TextView confirmText;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        Intent intent = getIntent();

        String child_id = intent.getStringExtra(AppConstants.CHILD_ID);
        String item_id = intent.getStringExtra(AppConstants.ITEM_ID);
        int amount = intent.getIntExtra(AppConstants.AMOUNT, 0);


        confirmText = (TextView) findViewById(R.id.activity_confirm_text);
        confirmButton = (Button) findViewById(R.id.activity_confirm_button);


        confirmText.setText("Du har overført " + amount + ", så " + child_id + " snart kan få en ny " + item_id);
        confirmButton.setOnClickListener(confirmListener());

    }

    private View.OnClickListener confirmListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Transaction was successful. User now returning to main screen.");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }
}
