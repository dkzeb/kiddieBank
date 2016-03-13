package dk.zebweb.kiddiebankapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.firebase.client.Firebase;

import java.util.UUID;

public class CreateWishActivity extends AppCompatActivity {

    private final String TAG = CreateWishActivity.class.getSimpleName();

    private EditText image;
    private EditText name;
    private EditText price;
    private EditText balance;
    private Spinner color;
    private Button createButton;


    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_wish);

        sessionManager = new SessionManager(this);

        image = (EditText) findViewById(R.id.create_wish_activity_image);
        name = (EditText) findViewById(R.id.create_wish_activity_name);
        price = (EditText) findViewById(R.id.create_wish_activity_price);
        balance = (EditText) findViewById(R.id.create_wish_activity_balance);
        color = (Spinner) findViewById(R.id.create_wish_activity_color);
        createButton = (Button) findViewById(R.id.create_wish_activity_button_register);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.color_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        color.setAdapter(adapter);
        createButton.setOnClickListener(createItemListener());
    }

    private View.OnClickListener createItemListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "A new item is now created");
                Firebase ref = new Firebase(getResources().getString(R.string.firebase_url)).child("wishes").child(sessionManager.getUid());
                ref.push().setValue(new Wish(
                        UUID.randomUUID().toString(),
                        name.getText().toString(),
                        image.getText().toString(),
                        Integer.parseInt(price.getText().toString()),
                        Integer.parseInt(balance.getText().toString()),
                        color.getSelectedItem().toString()
                ));

                image.setText("");
                name.setText("");
                price.setText("");
                balance.setText("");
            }
        };
    }
}
