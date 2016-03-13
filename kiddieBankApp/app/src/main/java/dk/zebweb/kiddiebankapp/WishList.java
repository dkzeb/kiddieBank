package dk.zebweb.kiddiebankapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class WishList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        final GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new WishListAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Wish w = (Wish) gridview.getItemAtPosition(position);
                System.out.println("Wish ID: "+w.getId());
                Intent i = new Intent(getApplicationContext(), DesignateMoney.class);
                //i.putExtra("wishID", w.getId());
                i.putExtra("wish", w);
                startActivity(i);
            }
        });

    }
}
