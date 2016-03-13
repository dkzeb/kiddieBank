package dk.zebweb.kiddiebankapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class PickWishActivity extends AppCompatActivity {

    private final String TAG = PickWishActivity.class.getSimpleName();

    String child_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_pick_wish);
        setContentView(R.layout.activity_wish_list);

        Intent intent = getIntent();
        child_id = intent.getStringExtra(AppConstants.CHILD_ID);

        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new WishListAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
                //TODO: get item id
                String item_id = "";
                Intent intent = new Intent(getApplicationContext(), ChooseAmountActivity.class);
                intent.putExtra(AppConstants.CHILD_ID, child_id);
                intent.putExtra(AppConstants.ITEM_ID, item_id);
                startActivity(intent);
            }
        });
    }
}
