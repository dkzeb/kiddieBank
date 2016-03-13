package dk.zebweb.kiddiebankapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

public class TransferMoneyActivity extends AppCompatActivity {


    private static final String TAG = TransferMoneyActivity.class.getSimpleName();

    private GridView kidsList;
    private KidsAdapter kidsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_money);

        kidsList = (GridView) findViewById(R.id.kids_list);
        KidsAdapter kidsAdapter = new KidsAdapter(this);
        kidsList.setAdapter(kidsAdapter);

        kidsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(TransferMoneyActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                //TODO: get child id
                String child_id = "";
                Intent intent = new Intent(getApplicationContext(), PickWishActivity.class);
                intent.putExtra(AppConstants.CHILD_ID, child_id);
                startActivity(intent);
            }
        });
    }
}
