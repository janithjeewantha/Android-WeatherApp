package com.janith.weatherapp;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CityListFragment extends Fragment {

    OnCitySelectedListener citySelectedListener;

    public CityListFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();

        ListView cityListView = (ListView) getActivity().findViewById(R.id.city_list);
        if (cityListView != null) {
            cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    citySelectedListener.onCitySelected(position);
                }
            });
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public interface OnCitySelectedListener {
        void onCitySelected(int position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city_list, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            citySelectedListener = (OnCitySelectedListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(e.toString());
        }
    }
}
