package com.example.pr_pro.newbloodapplication.ui.fragment.homscreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pr_pro.newbloodapplication.R;
import com.example.pr_pro.newbloodapplication.adapter.RequesteBloodRecyclrView;
import com.example.pr_pro.newbloodapplication.data.model.donationrequests.DonationRequests;
import com.example.pr_pro.newbloodapplication.data.model.donationrequests.DonationRequestsDatum;
import com.example.pr_pro.newbloodapplication.data.rest.ModelApiServices;
import com.example.pr_pro.newbloodapplication.data.rest.RetrofitClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pr_pro.newbloodapplication.helper.HelperLogin.verbose;


/**
 * A simple {@link Fragment} subclass.
 */
public class DonationRequestFragment extends Fragment {
    private static final String API_TOKEN = "Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27";
    //    private  int page=0;
    @BindView(R.id.blood_requests_recycler)
    RecyclerView bloodRequestsRecycler;
    Unbinder unbinder;
    private ModelApiServices modelApiServices;
    int max = 0;

    public DonationRequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donation_request, container, false);
        unbinder = ButterKnife.bind(this, view);
        setupRecyclerView();
        modelApiServices = RetrofitClient.getClient().create(ModelApiServices.class);
        returnBloodRequest();

        return view;
    }


    public void returnBloodRequest() {
        modelApiServices.getDonationRequests(API_TOKEN)
                .enqueue(new Callback<DonationRequests>() {

                    @Override
                    public void onResponse(Call<DonationRequests> call, Response<DonationRequests> response) {
                        verbose("returnBloodRequestsResponse: " + response.raw());
                        List<DonationRequestsDatum> donationRequestsDatumList = response.body().getData().getData();
                        bloodRequestsRecycler.setAdapter(new RequesteBloodRecyclrView(donationRequestsDatumList, getContext()));

                    }

                    @Override
                    public void onFailure(Call<DonationRequests> call, Throwable t) {

                    }
                });
    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        bloodRequestsRecycler.setLayoutManager(manager);

        bloodRequestsRecycler.setHasFixedSize(true);


//         bloodRequestsRecycler.setOnScrollListener(new EndlessRecyclerOnScrollListener(manager) {
//            @Override
//            public void onLoadMore(int current_page) {
//                if (current_page <= max){
//                    max = current_page;
//                    retrieveBloodRequests(current_page);
//
//                }
//
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
