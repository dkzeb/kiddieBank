package dk.zebweb.kiddiebankapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChooseAmountActivity extends AppCompatActivity {

    private final String TAG = ChooseAmountActivity.class.getSimpleName();

    private EditText amountField;
    private Button confirmButton;
    private Button returnButton;

    private String child_id;
    private String child_name;
    private String item_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_amount);

        Intent intent = getIntent();
        child_id = intent.getStringExtra(AppConstants.CHILD_ID);
        child_name = intent.getStringExtra(AppConstants.CHILD_NAME);
        item_id = intent.getStringExtra(AppConstants.ITEM_ID);


        amountField = (EditText) findViewById(R.id.activity_choose_amount_amount);
        confirmButton = (Button) findViewById(R.id.activity_choose_amount_confirm_button);
        returnButton = (Button) findViewById(R.id.activity_choose_amount_return_button);

        confirmButton.setOnClickListener(confirmListener());
        returnButton.setOnClickListener(returnListener());
    }


    private View.OnClickListener confirmListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "An amount of " + amountField.getText() + ", was picked.");
                Intent intent = new Intent(getApplicationContext(), ConfirmActivity.class);
                intent.putExtra(AppConstants.CHILD_ID, child_id);
                intent.putExtra(AppConstants.ITEM_ID, item_id);
                intent.putExtra(AppConstants.AMOUNT, Integer.parseInt(amountField.getText().toString()));
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener returnListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "return.");
                finish();
            }
        };
    }
}
