package dk.zebweb.kiddiebankapp;

import android.content.Context;
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

    private Context context;
    private final Wish[] wishes;

    public PickWishListAdapter(Context context, Wish[] wishes) {
        this.context = context;
        this.wishes = wishes;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.grid_wish, null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(wishes[position].getName());

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            String image = wishes[position].getImage();

            if (image.equals("bike")) {
                imageView.setImageResource(R.drawable.bike);
            } else if (image.equals("loebehjul")) {
                imageView.setImageResource(R.drawable.loebehjul);
            } else if (image.equals("rayman")) {
                imageView.setImageResource(R.drawable.rayman);
            } else {
                imageView.setImageResource(R.drawable.harry_p);
            }

        } else {
            gridView = convertView;
        }

        return gridView;
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
