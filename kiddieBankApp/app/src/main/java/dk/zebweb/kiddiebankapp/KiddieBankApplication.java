package dk.zebweb.kiddiebankapp;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Lunding on 12/03/16.
 */
public class KiddieBankApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
