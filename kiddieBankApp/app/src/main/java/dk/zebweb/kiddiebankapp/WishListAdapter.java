package dk.zebweb.kiddiebankapp;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

    public Object getItem(int position) {
        return null;
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
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setImageResource(mWishs[position].getImageID());
        } else {
            gridView = (View) convertView;
        }
        return gridView;

    }

    //Wish bike = new Wish("Cykel", "drawable://"+R.drawable.bike);

    // references to our images
    private Wish[] mWishs = {
            /*new Wish("Cykel", R.drawable.bike, 2500, 1380),
            new Wish("Rayman PS4 Spil", R.drawable.rayman, 400, 105),
            new Wish("Harry Potter Bog", R.drawable.harry_p, 150, 15),
            new Wish("Løbehjul", R.drawable.loebehjul, 799, 600),
            new Wish("Cykel", R.drawable.bike, 2500, 1380),
            new Wish("Rayman PS4 Spil", R.drawable.rayman, 400, 105),
            new Wish("Harry Potter Bog", R.drawable.harry_p, 150, 15),
            new Wish("Løbehjul", R.drawable.loebehjul, 799, 600)*/
    };
}
