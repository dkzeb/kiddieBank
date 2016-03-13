package dk.zebweb.kiddiebankapp;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cengalabs.flatui.FlatUI;
import com.cengalabs.flatui.views.FlatButton;
import com.firebase.client.Firebase;

public class youGotMoney extends AppCompatActivity {

    private final String TAG = youGotMoney.class.getSimpleName();
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_got_money);
        sessionManager = new SessionManager(this);

        Intent intent = getIntent();
        final Gift gift = (Gift) intent.getSerializableExtra(AppConstants.GIFT_OBJECT);

        Log.d(TAG, "You got " + gift.getAmount() + ", from: " + gift.getSenderName());

        // FlatUI
        FlatUI.initDefaultValues(this);
        FlatUI.setDefaultTheme(FlatUI.BLOOD);
        getSupportActionBar().setBackgroundDrawable(FlatUI.getActionBarDrawable(this, FlatUI.BLOOD, false));
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(AppConstants.getNotififyID(gift.getId())); //Delete notification

        final Firebase firebase = new Firebase(getResources().getString(R.string.firebase_url)).child("gift").child(sessionManager.getUid()).child(gift.getId());
        TextView headlineText = (TextView) findViewById(R.id.moneyValTxt);
        FlatButton nextBtn = (FlatButton) findViewById(R.id.nxtBtn);
        if (gift.getGiftId().equals("free")){
            headlineText.setText("Hej " + sessionManager.getName() + "," + " brug pengene fornuftigt.\n- " + gift.getSenderName());
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    firebase.removeValue();
                    Intent i = new Intent(getApplicationContext(), WishList.class);
                    i.putExtra(AppConstants.GIFT_OBJECT, gift);
                    startActivity(i);
                }
            });
        } else {
            headlineText.setText("Hej " + sessionManager.getName() + "," + " du har fået " + gift.getAmount() + " til en " + gift.getGiftName() + ".\n- " + gift.getSenderName());
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    firebase.removeValue();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }
            });
        }

    }
}
