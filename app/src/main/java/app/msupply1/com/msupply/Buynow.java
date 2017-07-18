package app.msupply1.com.msupply;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class Buynow extends AppCompatActivity {
    ImageView buynowimage;
    private List<Albumspecifyproduct> albumList;
    Albumspecifyproduct album;
    private Context mContext;
    Button demo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buynow);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String product_id = bundle.getString("product_id");
       // String product_image_id = bundle.getString("product_image_id");
        String position = bundle.getString("position");


        TextView spproductperticularname = (TextView) findViewById(R.id.spproductperticularname);
        TextView spproductdetailsid = (TextView) findViewById(R.id.spproductdetailsid);
        TextView buynow_image_id_position = (TextView) findViewById(R.id.buynow_image_id);
        buynowimage = (ImageView) findViewById(R.id.spproductdetailsimage);

        Button demo =(Button)findViewById(R.id.demo);

        spproductperticularname.setText(name);
        spproductdetailsid.setText(product_id);
        buynow_image_id_position.setText(position);


demo.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(Buynow.this,Current_address.class);
        startActivity(i);
    }
});



    }


    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }
    }
}
