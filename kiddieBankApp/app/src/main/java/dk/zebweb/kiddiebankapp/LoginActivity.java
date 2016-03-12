package dk.zebweb.kiddiebankapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = LoginActivity.class.getSimpleName();

    private EditText email;
    private EditText password;
    private Button loginButton;
    private TextView registerText;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(this);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.login_activity_edit_email);
        password = (EditText) findViewById(R.id.login_activity_edit_password);
        loginButton = (Button) findViewById(R.id.login_activity_button_login);
        registerText = (TextView) findViewById(R.id.login_activity_text_register);

        registerText.setOnClickListener(registerListener());
        loginButton.setOnClickListener(loginListener(this));
    }

    private View.OnClickListener registerListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener loginListener(final Activity activity){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Firebase ref = new Firebase(getResources().getString(R.string.firebase_url));
                final String emailString = email.getText().toString();
                String passwordString = password.getText().toString();

                if (emailString.length() < 3 || passwordString.length() < 3){
                    Toast.makeText(getApplicationContext(), "Credentials too short", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d(TAG, "Attempting to log in");
                ref.authWithPassword(emailString, passwordString, new Firebase.AuthResultHandler() {

                    @Override
                    public void onAuthenticated(AuthData authData) {
                        Log.d(TAG, "Logged in successful");
                        sessionManager.setLoginDetails(authData.getToken(), authData.getUid(), authData.getProvider());
                        ref.child("users").child(authData.getUid()).child("name").setValue(emailString);
                        ref.child("users").child(authData.getUid()).child("account").setValue(10000);
                        Toast.makeText(getApplicationContext(), "You are now logged in!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        Log.d(TAG, "Error during login attempt. Code: " + firebaseError.getCode() + " Message:" + firebaseError.getMessage() + "\nDetails: " + firebaseError.getDetails());
                        sessionManager.removeLoginDetails();
                        Toast.makeText(getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
    }
}
