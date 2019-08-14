package com.example.pr_pro.newbloodapplication.ui.fragment.donation;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pr_pro.newbloodapplication.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class InformationRequestDetail extends Fragment implements OnMapReadyCallback {


    public InformationRequestDetail() {
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_information_request, container, false);
        return view;

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
