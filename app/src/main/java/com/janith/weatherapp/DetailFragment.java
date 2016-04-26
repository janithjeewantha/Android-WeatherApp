package com.janith.weatherapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    public String msg = "";

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();

        TextView detailField = (TextView) getActivity().findViewById(R.id.detail_field);
        detailField.setText(msg);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

}
