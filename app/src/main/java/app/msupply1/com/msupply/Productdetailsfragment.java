package app.msupply1.com.msupply;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Productdetailsfragment extends Fragment{


    TextView testtextview;

    private RecyclerView recyclerView;
    private AlbumsAdapterspecifyproducts adapterspecifyproducts;
    private List<Albumspecifyproduct> albumListspecifyproducts;

    public static String pposition;

    View Productdetailsfragment;
    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String strtext = Productmain.pposition;
        Log.d("valoooooo  ","****  "+Productmain.pposition);

        Productdetailsfragment = inflater.inflate(R.layout.fragment_productdetailsfragment, container, false);






        recyclerView = (RecyclerView) Productdetailsfragment.findViewById(R.id.recycler_view1);

        albumListspecifyproducts = new ArrayList<>();
        adapterspecifyproducts = new AlbumsAdapterspecifyproducts(getActivity(), albumListspecifyproducts);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterspecifyproducts);

        ProductAlbums();



        /*try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) UserdetailsFragmentView.findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return Productdetailsfragment;
    }
    private void ProductAlbums() {
        int[] covers = new int[]{
                R.drawable.bricks1,
                R.drawable.bricks2,
                R.drawable.bricks3,
                R.drawable.bricks4,
                R.drawable.bricks5,
                R.drawable.bricks6,
                R.drawable.bricks7,
                R.drawable.bricks8,
                R.drawable.bricks9,
                R.drawable.bricks10};



        Albumspecifyproduct a = new Albumspecifyproduct("RBTI Two Hole Bricks (229x102x76mm)", "ASPH11843725", covers[0]);
        albumListspecifyproducts.add(a);

        a = new Albumspecifyproduct("ABC-Red Clay Table Moulded Bricks", "ASPH10020938", covers[1]);
        albumListspecifyproducts.add(a);

        a = new Albumspecifyproduct("ABC-Red Clay Table Moulded Bricks", "ASPH10020940", covers[2]);
        albumListspecifyproducts.add(a);

        a = new Albumspecifyproduct("ABC-Wire Cut Bricks (Class A)", "ASPH10020942", covers[3]);
        albumListspecifyproducts.add(a);

        a = new Albumspecifyproduct("ABC-Wire Cut Bricks (Class B)", "ASPH10020941", covers[4]);
        albumListspecifyproducts.add(a);

        a = new Albumspecifyproduct("Biswakarma Fly Ash Brick (10Mpa, 230x110x70mm)", "ASPH11837641", covers[5]);
        albumListspecifyproducts.add(a);

        a = new Albumspecifyproduct("NG And GB Fly Ash Bricks (230x100x75 mm)", " ASPH11700573", covers[6]);
        albumListspecifyproducts.add(a);

        a = new Albumspecifyproduct("NSR-Red Clay Table Moulded Bricks", "201010002", covers[7]);
        albumListspecifyproducts.add(a);

        a = new Albumspecifyproduct("NSR-Red Clay Table Moulded Bricks", "201020002", covers[8]);
        albumListspecifyproducts.add(a);

        a = new Albumspecifyproduct("RBTI Solid Bricks (229x102x76mm)", "ASPH11843726", covers[9]);
        albumListspecifyproducts.add(a);


        adapterspecifyproducts.notifyDataSetChanged();
    }


    /**
 * RecyclerView item decoration - give equal margin around grid item
 */
public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
    private boolean includeEdge;

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = spacing;
            }
            outRect.bottom = spacing; // item bottom
        } else {
            outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing; // item top
            }
        }
    }
}

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public void onBackPressed()
    {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.popBackStack();
    }


}
