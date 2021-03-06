package dk.zebweb.kiddiebankapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Lunding on 13/03/16.
 */
public class PickWishListAdapter extends BaseAdapter {

    private final String TAG = PickWishListAdapter.class.getSimpleName();

    private LayoutInflater inflater;
    private Context context;
    private final Wish[] wishes;

    public PickWishListAdapter(Context context, Wish[] wishes) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.wishes = wishes;
    }


    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;

        ImageView picture;
        TextView name;
        TextView value;

        if (v == null){
            v = inflater.inflate(R.layout.imageview_squareimage, parent, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
            v.setTag(R.id.value, v.findViewById(R.id.value));
        }

        picture = (ImageView) v.getTag(R.id.picture);
        name = (TextView) v.getTag(R.id.text);
        value = (TextView) v.getTag(R.id.value);

        Wish wish = (Wish) getItem(position);
        Log.d(TAG, "Wish to be displayed: " + wish);
        name.setText(wish.getName());
        if (!wish.getId().equals("free")){
            int resourceId = context.getResources().getIdentifier(wishes[position].getImage(), "drawable", context.getPackageName());
            picture.setImageResource(resourceId);
            value.setText(wish.getPrice() + " (" + wish.getBalance() + ")");
        } else {
            picture.setImageDrawable(this.context.getResources().getDrawable(R.drawable.coinstack));
            value.setText("");
        }





        return v;
    }

    @Override
    public int getCount() {
        return wishes.length;
    }

    @Override
    public Object getItem(int position) {
        return wishes[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
