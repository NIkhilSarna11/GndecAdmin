package com.birjot.gndec_sports_admin.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.birjot.gndec_sports_admin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class introduction extends Fragment {


    public introduction() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmen


        return inflater.inflate(R.layout.fragment_introduction, container, false);
    }

}
