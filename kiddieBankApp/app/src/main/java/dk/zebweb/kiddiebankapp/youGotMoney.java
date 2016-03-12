package dk.zebweb.kiddiebankapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class youGotMoney extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_got_money);
/*
        ImageView moneyImage = (ImageView) findViewById(moneyImage);
        if(moneyImage != null){
            moneyImage.setImageDrawable("DK1000");
        }
*/

        Button nextBtn = (Button) findViewById(R.id.nxtBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), WishList.class);
                i.putExtra("amount", 1000);
                startActivity(i);
            }
        });
    }
}
