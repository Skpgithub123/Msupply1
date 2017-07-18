package app.msupply1.com.msupply;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.util.List;

    public class AlbumsAdapterspecifyproducts extends RecyclerView.Adapter<app.msupply1.com.msupply.AlbumsAdapterspecifyproducts.MyViewHolder> {

        private Context mContext;
        private List<Albumspecifyproduct> albumList;
        Albumspecifyproduct album;
        CardView cardview;
        byte[] byteArray;
        public AlbumsAdapterspecifyproducts(Context mContext, List<Albumspecifyproduct> albumList) {
            this.mContext = mContext;
            this.albumList = albumList;
        }



        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title, count;
            public ImageView thumbnail, overflow;
            Button spdetailsbuynow,spdetailsaddcart;
            FrameLayout framlayout;

            @RequiresApi(api = Build.VERSION_CODES.M)
            public MyViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.sptitle);
                count = (TextView) view.findViewById(R.id.spcount);
                thumbnail = (ImageView) view.findViewById(R.id.spthumbnail);
                overflow = (ImageView) view.findViewById(R.id.spoverflow);
                cardview = (CardView) view.findViewById(R.id.spcard_view);
                spdetailsbuynow = (Button) view.findViewById(R.id.spdetailsbuynow);
                spdetailsaddcart = (Button) view.findViewById(R.id.spdetailsaddcart);
                framlayout = (FrameLayout) view.findViewById(R.id.Productdetailscard);
            }
        }




        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public AlbumsAdapterspecifyproducts.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.album_card_detailsproducts, parent, false);

            return new AlbumsAdapterspecifyproducts.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final AlbumsAdapterspecifyproducts.MyViewHolder holder, final int position) {
            album = albumList.get(position);
            holder.title.setText(album.getName());
            holder.count.setText("Product ID: "+album.getProductID());

            // loading album cover using Glide library
            Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

            holder.overflow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(holder.overflow);



                }

            });
       holder.spdetailsbuynow.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent = new Intent(mContext,Buynow.class);
               //Create the bundle
               Bundle bundle = new Bundle();

                //Add your data to bundle
               intent.putExtra("name", album.getName());
               intent.putExtra("product_id",album.getProductID());
               intent.putExtra("image",byteArray);
               intent.putExtra("position",albumList.get(position).toString());


               //Add the bundle to the intent
               intent.putExtras(bundle);

               mContext.startActivity(intent);


               /*FragmentManager fragmentManager = getFragmentManager();
               FragmentTransaction fragmentTransaction = fragmentManager
                       .beginTransaction();
               fragmentTransaction.replace(R.id.content_frame, fragment2);
               fragmentTransaction.commit();*/

/*
               FragmentManager fragmentManager = getFragmentManager();

               FragmentManager fm = new F
               ft.replace(R.id.Productmainfragment, new Productdetailsfragment());
               ft.commit();
*/


              /* mContext.getApplicationContext();
               Product_perticular_details fragment = new Product_perticular_details();
               FragmentManager fragmentManager = ((Activity) mContext).getFragmentManager();
               fragmentManager.beginTransaction().replace(R.id.Productmainfragment, fragment).commit();*/
               //Product_perticular_details fragment = new Product_perticular_details();


                //You can change the fragment, something like this, not tested, please correct for your desired output:
              /* AppCompatActivity activity = (AppCompatActivity) v.getContext();
               Product_perticular_details myFragment = new Product_perticular_details();
               //Create a bundle to pass data, add data, set the bundle to your fragment and:
               activity.getSupportFragmentManager().beginTransaction().replace(R.id.Productdetailscard, myFragment).addToBackStack(null).commit();


*/
               Toast.makeText(mContext,"go to payement page",Toast.LENGTH_SHORT).show();
           }
       });

            holder.spdetailsaddcart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"1 item added in cart",Toast.LENGTH_SHORT).show();
                }
            });


        }




        private void showPopupMenu(View view) {
            // inflate menu
            PopupMenu popup = new PopupMenu(mContext, view);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.menu_album, popup.getMenu());
            popup.setOnMenuItemClickListener(new app.msupply1.com.msupply.AlbumsAdapterspecifyproducts.MyMenuItemClickListener());
            popup.show();
        }

        /**
         * Click listener for popup menu items
         */
        class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

            public MyMenuItemClickListener() {
            }

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_add_favourite:
                        Toast.makeText(mContext, "Add to cart", Toast.LENGTH_SHORT).show();
                        return true;

                    default:
                }
                return false;
            }
        }

        @Override
        public int getItemCount() {
            return albumList.size();
        }
    }

