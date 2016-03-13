package dk.zebweb.kiddiebankapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by zeb on 12-03-16.
 */
public class WishListAdapter extends BaseAdapter {
    private Context mContext;

    public WishListAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mWishs.length;
    }

    public Wish getItem(int position) {
        return mWishs[position];
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        /*
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);

            //imageView.setPadding(8, 8, 8, 8);
        */
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if(convertView == null){
            gridView = new View(mContext);
            gridView = inflater.inflate(R.layout.wish_item, null);


            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.wishImage);

            TextView wishName = (TextView) gridView.findViewById(R.id.wishName);
            wishName.setText(mWishs[position].getName());
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 500));
            gridView.setPadding(8, 8, 8, 8);
            int resourceId = mContext.getResources().getIdentifier(mWishs[position].getImage(), "drawable", mContext.getPackageName());
            imageView.setImageResource(resourceId);
        } else {
            gridView = (View) convertView;
        }
        return gridView;

    }

    //Wish bike = new Wish("Cykel", "drawable://"+R.drawable.bike);

    // references to our images
    private Wish[] mWishs = {
            new Wish("1", "Cykel", "bike", 2500, 1380, "none"),
            new Wish("2", "Rayman PS4 Spil", "rayman", 400, 105, "none"),
            new Wish("3", "Harry Potter Bog", "harry_p", 150, 15, "none"),
            new Wish("4", "Løbehjul", "loebehjul", 799, 600, "none"),
            new Wish("5", "Cykel", "bike", 2500, 1380, "none"),
            new Wish("6", "Rayman PS4 Spil", "rayman", 400, 105, "none"),
            new Wish("7", "Harry Potter Bog", "harry_p", 150, 15, "none"),
            new Wish("8", "Løbehjul", "loebehjul", 799, 600, "none")
    };
}
