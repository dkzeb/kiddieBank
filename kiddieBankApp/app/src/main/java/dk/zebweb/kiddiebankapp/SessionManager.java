package dk.zebweb.kiddiebankapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Lunding on 12/03/16.
 */
public class SessionManager {

    //Constants
    public static final String TAG = SessionManager.class.getSimpleName();
    private static final String PREF_NAME = "kiddiebank";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_TOKEN= "token";
    private static final String KEY_UID = "uid";
    private static final String KEY_PROVIDER = "provider";
    private static final int PRIVATE_MODE = 0;

    //Fields
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;


    public SessionManager(Context context){
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }


    public void setLoginDetails(String token, String uid, String provider){
        editor.putString(KEY_TOKEN, token);
        editor.putString(KEY_UID, uid);
        editor.putString(KEY_PROVIDER, provider);
        editor.putBoolean(KEY_IS_LOGGEDIN, true);
        editor.commit();
        Log.d(TAG, "User login session modified. Token updated");
    }

    public void removeLoginDetails(){
        editor.putString(KEY_TOKEN, "");
        editor.putString(KEY_UID, "");
        editor.putBoolean(KEY_IS_LOGGEDIN, false);
        editor.commit();
        Log.d(TAG, "User login session modified. Token removed");
    }

    public String getUid(){
        return preferences.getString(KEY_UID, null);
    }

    public String getProvider(){
        return preferences.getString(KEY_PROVIDER, null);
    }

    public String getToken(){
        return preferences.getString(KEY_TOKEN, null);
    }

    public boolean isLoggedIn(){
        return preferences.getBoolean(KEY_IS_LOGGEDIN, false);
    }

}
