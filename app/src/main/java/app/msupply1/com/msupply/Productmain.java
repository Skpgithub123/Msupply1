package app.msupply1.com.msupply;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Productmain extends Fragment {

    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private List<Album> albumList;

    public static String pposition;

    View UserdetailsFragmentView;
    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         UserdetailsFragmentView = inflater.inflate(R.layout.productmain, container, false);


          initCollapsingToolbar();

        recyclerView = (RecyclerView) UserdetailsFragmentView.findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter(getActivity(), albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                      Log.d("click position","  "+position);
                        pposition =""+position;
                       // Toast.makeText(getContext(),"ckickposition"+position,Toast.LENGTH_SHORT).show();

                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.Productmainfragment, new Productdetailsfragment());
                        ft.commit();

                        /*

                        Intent intent = new Intent(getContext(),Productdetailsfragment.class);
                        startActivity(intent);*/
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        prepareAlbums();

        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) UserdetailsFragmentView.findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UserdetailsFragmentView;
    }


    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) UserdetailsFragmentView.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("");

        AppBarLayout appBarLayout = (AppBarLayout) UserdetailsFragmentView.findViewById(R.id.appbar);
        appBarLayout.setExpanded(false);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.bricks,
                R.drawable.cement,
                R.drawable.consumables,
                R.drawable.accessories,
                R.drawable.commodes,
                R.drawable.faucet,
                R.drawable.showers,
                R.drawable.washbasins,
                R.drawable.wellness,
                R.drawable.appliance,
                R.drawable.bulb,
                R.drawable.fans,
                R.drawable.lightfittings,
                R.drawable.switch1};

        Album a = new Album("Bricks", 13, covers[0]);
        albumList.add(a);

        a = new Album("Cement", 8, covers[1]);
        albumList.add(a);

        a = new Album("Consumables 5", 11, covers[2]);
        albumList.add(a);

        a = new Album("Accessories", 12, covers[3]);
        albumList.add(a);

        a = new Album("Commodes", 14, covers[4]);
        albumList.add(a);

        a = new Album("Faucet", 1, covers[5]);
        albumList.add(a);

        a = new Album("Showers", 11, covers[6]);
        albumList.add(a);

        a = new Album("Washbasins", 14, covers[7]);
        albumList.add(a);

        a = new Album("Wellness", 11, covers[8]);
        albumList.add(a);

        a = new Album("Appliance", 17, covers[9]);
        albumList.add(a);

        a = new Album("bulb", 17, covers[10]);
        albumList.add(a);

        a = new Album("Fans", 17, covers[11]);
        albumList.add(a);

        a = new Album("lightfittings", 17, covers[12]);
        albumList.add(a);

        a = new Album("switch", 17, covers[13]);
        albumList.add(a);

        adapter.notifyDataSetChanged();
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

}
