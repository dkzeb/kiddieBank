package dk.zebweb.kiddiebankapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Lunding on 13/03/16.
 */
public class KidsListAdapter extends BaseAdapter {

    private static final String TAG = KidsListAdapter.class.getSimpleName();

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<User> children;

    public KidsListAdapter(Context context, ArrayList<User> children){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.children = children;
    }

    @Override
    public int getCount(){
        return children.size();
    }

    @Override
    public Object getItem(int position){
        return children.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
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

        User user = (User) getItem(position);
        Log.d(TAG, "User to be displayed: " + user);
        if (user.getGender() == 0){
            picture.setImageDrawable(this.context.getResources().getDrawable(R.mipmap.avatar_lise));
        } else {
            picture.setImageDrawable(this.context.getResources().getDrawable(R.mipmap.avatar_peter));
        }

        name.setText(user.getName());
        value.setText("");

        return v;
    }

}
