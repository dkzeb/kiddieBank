package dk.zebweb.kiddiebankapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class DesignateMoney extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designate_money);

        String wishID = getIntent().getStringExtra("wishID");
        System.out.println("Wish ID: " + wishID);
        Wish w = (Wish) getIntent().getSerializableExtra("wish");

        ImageView img = (ImageView) findViewById(R.id.designate_wishimage);

        int resourceId = getApplicationContext().getResources().getIdentifier(w.getImage(), "drawable", getApplicationContext().getPackageName());
        img.setImageResource(resourceId);

    }
}
