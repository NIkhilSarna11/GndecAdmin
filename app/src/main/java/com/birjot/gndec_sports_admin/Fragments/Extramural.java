package com.birjot.gndec_sports_admin.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.birjot.gndec_sports_admin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Extramural extends Fragment {


    public Extramural() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_extramural, container, false);
    }

}
