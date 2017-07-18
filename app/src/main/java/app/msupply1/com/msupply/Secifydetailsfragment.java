package app.msupply1.com.msupply;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Secifydetailsfragment extends Fragment {



    public static Secifydetailsfragment newInstance(String param1, String param2) {
        Secifydetailsfragment fragment = new Secifydetailsfragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_secifydetailsfragment, container, false);





    }
}
