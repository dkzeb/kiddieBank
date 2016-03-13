package dk.zebweb.kiddiebankapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.cengalabs.flatui.FlatUI;
import com.cengalabs.flatui.views.FlatButton;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.github.lzyzsd.circleprogress.DonutProgress;

import org.w3c.dom.Text;

public class DesignateMoney extends AppCompatActivity {

    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designate_money);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // FlatUI
        FlatUI.initDefaultValues(this);
        FlatUI.setDefaultTheme(FlatUI.BLOOD);
        getSupportActionBar().setBackgroundDrawable(FlatUI.getActionBarDrawable(this, FlatUI.BLOOD, false));
        String wishID = getIntent().getStringExtra("wishID");
        final Wish w = (Wish) getIntent().getSerializableExtra("wish");
        final Gift g = (Gift) getIntent().getSerializableExtra("gift");

        FlatButton okGo = (FlatButton) findViewById(R.id.putMoneyBtn);
        okGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), UDidIt.class);
                startActivity(i);
            }
        });

        System.out.println(g.getGiftName());

        ImageView img = (ImageView) findViewById(R.id.designate_wishimage);

        int resourceId = getApplicationContext().getResources().getIdentifier(w.getImage(), "drawable", getApplicationContext().getPackageName());
        img.setImageResource(resourceId);

        int savings = w.getBalance();
        int price = w.getPrice();

        SeekBar moneyBar = (SeekBar) findViewById(R.id.depositBar);
        moneyBar.setMax(g.getAmount());

        final DonutProgress progressArc = (DonutProgress) findViewById(R.id.arc_progress);
        progressArc.setProgress(65);
        progressArc.setTextSize(20);
        progressArc.setTextColor(255);
        //progressArc.setBottomText(w.getName());
        //progressArc.setUnfinishedStrokeColor();
        progressArc.bringToFront();
        progressArc.setMax(w.getPrice());
        progressArc.setProgress(w.getBalance());


        TextView amtTxt = (TextView) findViewById(R.id.maxVal);
        amtTxt.setText(g.getAmount()+" kr.");

        String progressString = w.getBalance()+"/"+w.getPrice()+" kr.";
        TextView progressStringTV = (TextView) findViewById(R.id.moneyTxt);
        progressStringTV.setText(progressString);

        moneyBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                if (progress + w.getBalance() > w.getPrice()) {
                    progress = w.getPrice();
                } else {

                    System.out.println("Val: " + (progress + w.getBalance()));
                    String newPriceUpdate = (progress + w.getBalance()) + "/" + w.getPrice() + " kr.";
                    TextView newMnyText = (TextView) findViewById(R.id.moneyTxt);
                    newMnyText.setText(newPriceUpdate);
                    progressArc.setProgress(progress + w.getBalance());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        System.out.println(savings + "/" + price);

    }
}
