package dk.zebweb.kiddiebankapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import static android.support.v7.app.NotificationCompat.*;

public class MainActivity extends AppCompatActivity {

    static final int notififyID = 23520;

    NotificationManager mNotificationManager;
    NotificationCompat.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                mNotificationManager.notify(notififyID, mBuilder.build());
            }
        });


        mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.coinstack)
                        .setContentTitle("Du har fået penge!")
                        .setContentText("Hvad skal de bruges på!? :)");
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, youGotMoney.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(youGotMoney.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
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

        Button WishBtn = (Button) findViewById(R.id.wishButton);
        WishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CreateWishActivity.class);
                startActivity(i);
            }
        });
    }
}
