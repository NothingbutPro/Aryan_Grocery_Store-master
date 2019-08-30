package com.aryanonline.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aryanonline.MainActivity;
import com.aryanonline.R;

public class Emi_fragment extends Fragment {





    public Emi_fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_emi_, container, false);

        ((MainActivity) getActivity()).setTitle(getResources().getString(R.string.emi_detail));









        return view;
    }

}
