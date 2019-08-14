package com.example.pr_pro.newbloodapplication.ui.fragment.homscreen.homnavigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pr_pro.newbloodapplication.R;
import com.example.pr_pro.newbloodapplication.adapter.GetListNotificationAdapter;
import com.example.pr_pro.newbloodapplication.data.model.listofnotification.DatumListNotification;
import com.example.pr_pro.newbloodapplication.data.model.listofnotification.ListOfNotification;
import com.example.pr_pro.newbloodapplication.data.rest.ModelApiServices;
import com.example.pr_pro.newbloodapplication.data.rest.RetrofitClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetNotificationlistFragment extends Fragment {


    @BindView(R.id.recyclerListNotification)
    RecyclerView recyclerListNotification;
    Unbinder unbinder;
    ModelApiServices modelApiServices;
    GetListNotificationAdapter getListNotificationAdapter ;
    String api_Token;


    public GetNotificationlistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_get_notificationlist, container, false);
        unbinder = ButterKnife.bind(this, view);
        modelApiServices = RetrofitClient.getClient().create(ModelApiServices.class);
        api_Token = "W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl";
        listNotificatio();
        return view;
    }



    public void  listNotificatio(){
        modelApiServices.getNotifications(api_Token).enqueue(new Callback<ListOfNotification>() {
            @Override
            public void onResponse(Call<ListOfNotification> call, Response<ListOfNotification> response) {
                String msg = response.body().getMsg();
                if (response.body().getStatus() == 1){
                    Toast.makeText(getActivity(), ""+msg, Toast.LENGTH_SHORT).show();
                    List<DatumListNotification> data = response.body().getData().getData();
                    recyclerListNotification.setHasFixedSize(true);
                    recyclerListNotification.setLayoutManager(new LinearLayoutManager(getContext()));
                    getListNotificationAdapter = new GetListNotificationAdapter(getContext(),getActivity(),data);
                    recyclerListNotification.setAdapter(getListNotificationAdapter);
                }else {
                    Toast.makeText(getActivity(), ""+msg, Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ListOfNotification> call, Throwable t) {
                Toast.makeText(getActivity(), "Eroorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
