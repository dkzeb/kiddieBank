package dk.zebweb.kiddiebankapp;

import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText password;
    private EditText passwordVerify;
    private Button registerButton;
    private TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.register_activity_edit_name);
        email = (EditText) findViewById(R.id.register_activity_edit_email);
        password = (EditText) findViewById(R.id.register_activity_edit_password);
        passwordVerify = (EditText) findViewById(R.id.register_activity_edit_password_verify);
        registerButton = (Button) findViewById(R.id.register_activity_button_register);
        loginText = (TextView) findViewById(R.id.register_activity_text_login);

        loginText.setOnClickListener(loginListener(this));
        registerButton.setOnClickListener(registerListener(this));


    }

    private View.OnClickListener loginListener(final Activity activity){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(activity);
            }
        };
    }


    private View.OnClickListener registerListener(final Activity activity){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nameString = name.getText().toString();
                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();
                String passwordVerifyString = passwordVerify.getText().toString();

                if (!passwordString.equals(passwordVerifyString)){
                    Toast.makeText(getApplicationContext(), "Password doesn't match!", Toast.LENGTH_SHORT).show();
                    return;
                }

                final Firebase ref = new Firebase(getResources().getString(R.string.firebase_url));
                ref.createUser(emailString, passwordString, new Firebase.ValueResultHandler<Map<String, Object>>() {

                    @Override
                    public void onSuccess(Map<String, Object> result) {
                        Toast.makeText(getApplicationContext(), "Account created!", Toast.LENGTH_SHORT).show();
                        NavUtils.navigateUpFromSameTask(activity);
                        ref.child("users").child(result.get("uid")+"").setValue(new User(nameString, (int) (Math.random()*10000)));
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Toast.makeText(getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
    }

}
