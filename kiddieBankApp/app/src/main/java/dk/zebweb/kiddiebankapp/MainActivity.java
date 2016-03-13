package dk.zebweb.kiddiebankapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.cengalabs.flatui.FlatUI;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import static android.support.v7.app.NotificationCompat.*;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    public static final int notififyID = 23520;

    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionManager = new SessionManager(getApplicationContext());
        // FlatUI
        FlatUI.initDefaultValues(this);
        FlatUI.setDefaultTheme(FlatUI.BLOOD);
        getSupportActionBar().setBackgroundDrawable(FlatUI.getActionBarDrawable(this, FlatUI.BLOOD, false));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);

        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.menu_layout, null);

        actionBar.setCustomView(v);

        Button barnBtn = (Button) findViewById(R.id.barnButton);
        barnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), youGotMoney.class);
                startActivity(i);
                //finish(); Så virker tilbage ikke ;)
            }
        });

        Button loginBtn = (Button) findViewById(R.id.loginButton);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        Button parentBtn = (Button) findViewById(R.id.parentButton);
        parentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TransferMoneyActivity.class);
                startActivity(i);
            }
        });

        Button notifyBtn = (Button) findViewById(R.id.notifyButton);
        notifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postNotif(new Gift("Fake id", 100, "Fake id", "Fake name", "Fake sender", "Fake name", false));
            }
        });


        Button WishBtn = (Button) findViewById(R.id.wishButton);
        WishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CreateWishActivity.class);
                startActivity(i);
            }
        });

        if(sessionManager.isLoggedIn()){
            Log.d(TAG, "SETTING UP FIREBASE LISTENER!!!");
            Firebase firebase = new Firebase(getResources().getString(R.string.firebase_url)).child("gift").child(sessionManager.getUid());
            firebase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d(TAG, "You have got some new money!!!");
                    for (DataSnapshot giftSnapshot : dataSnapshot.getChildren()){
                        Log.d(TAG, giftSnapshot.toString());
                        postNotif(giftSnapshot.getValue(Gift.class));
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }

    }

    private void postNotif(Gift gift) {
        if (gift.getGiftId().equals("free")){
            mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.coinstack)
                            .setContentTitle("Du har fået " + gift.getAmount() + " dk")
                            .setContentText("Hvad skal de bruges på!? Mvh " + gift.getSenderName() + " :)");
        } else {
            mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.coinstack)
                            .setContentTitle("Du har fået " + gift.getAmount() + " dk")
                            .setContentText("De går til dit ønske om en " + gift.getGiftName() + ". Fra " + gift.getSenderName() + " :)");
        }


        // Creates an explicit intent for an Activity in your app
        Intent intent = new Intent(this, youGotMoney.class);
        intent.putExtra(AppConstants.GIFT_OBJECT, gift);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(youGotMoney.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        //mNotificationManager.notify(notififyID, mBuilder.build());
        mNotificationManager.notify(AppConstants.getNotififyID(gift.getId()), mBuilder.build());
    }
}
