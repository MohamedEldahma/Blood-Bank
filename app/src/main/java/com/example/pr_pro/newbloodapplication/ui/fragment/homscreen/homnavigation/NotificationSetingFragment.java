package com.example.pr_pro.newbloodapplication.ui.fragment.homscreen.homnavigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.pr_pro.newbloodapplication.R;
import com.example.pr_pro.newbloodapplication.adapter.NotificationSeetingAdaper;
import com.example.pr_pro.newbloodapplication.data.model.governorates.Governorates;
import com.example.pr_pro.newbloodapplication.data.model.governorates.GovernoratesDatum;
import com.example.pr_pro.newbloodapplication.data.model.notificationssettings.NotificationSsettings;
import com.example.pr_pro.newbloodapplication.data.rest.ModelApiServices;
import com.example.pr_pro.newbloodapplication.data.rest.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pr_pro.newbloodapplication.helper.SharedPreferencesManger.LoadStringData;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.API_TOKEN;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationSetingFragment extends Fragment {

    @BindView(R.id.butt_save)
    Button buttSave;
    Unbinder unbinder;
    @BindView(R.id.blood_typ_check_recycler)
    RecyclerView bloodTypCheckRecycler;
    @BindView(R.id.overnorat_check_recicler)
    RecyclerView overnoratCheckRecicler;
    NotificationSeetingAdaper  bloodTypAdebterRsycler,governoratAdapterRecycler;
    String apiToken;
    ModelApiServices modelApiServices;

    public NotificationSetingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification_seting, container, false);
        unbinder = ButterKnife.bind(this, view);
        govrnoratRecycler();
//        modelApiServices = RetrofitClient.getClient().create(ModelApiServices.class);
//        apiToken = LoadStringData(getActivity(),API_TOKEN);
//        getGovernorat();
        return view;
    }

    private  void  bloodTypeRecycler(){
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),4);
        bloodTypCheckRecycler.setLayoutManager(layoutManager);
        bloodTypCheckRecycler.setHasFixedSize(true);
        List<String>bloodTypList = new ArrayList<>();
        bloodTypList.add("A+");
        bloodTypList.add("A-");
        bloodTypList.add("B+");
        bloodTypList.add("B-");
        bloodTypList.add("AB+");
        bloodTypList.add("AB-");
        bloodTypList.add("O+");
        bloodTypList.add("O-");
//        bloodTypAdebterRsycler = new NotificationSeetingAdaper(bloodTypList,getContext(),1);
//        bloodTypCheckRecycler.setAdapter(bloodTypAdebterRsycler);


    }

    public  void getGovernorat(){

        modelApiServices.getGovernorates().enqueue(new Callback<Governorates>() {
            @Override
            public void onResponse(Call<Governorates> call, Response<Governorates> response) {
                long status = response.body().getStatus();
                List<String> list =new ArrayList<>();
                String massg = response.body().getMsg();
                if (status == 1){
                    Toast.makeText(getContext(),"Governorat is Check:"+massg,Toast.LENGTH_SHORT).show();
                    List<GovernoratesDatum> governoratesData = response.body().getData();
                    for (int i=0;i<governoratesData.size();i++){
                        String govrnoratName =governoratesData.get(i).getName();
                        Toast.makeText(getActivity(),"Governorat Name : "+govrnoratName,Toast.LENGTH_SHORT).show();
                        list.add(govrnoratName);

                    }

//                        governoratAdapterRecycler =new NotificationSeetingAdaper(list,getContext(),2);
//                       overnoratCheckRecicler.setAdapter(governoratAdapterRecycler);

                }else {Toast.makeText(getActivity(),"Govrnora Cancel Check"+massg,Toast.LENGTH_SHORT).show();}

            }

            @Override
            public void onFailure(Call<Governorates> call, Throwable t) {
                Toast.makeText(getActivity(),"Errrror "+t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void govrnoratRecycler(){
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),4);
//        overnoratCheckRecicler.setLayoutManager(layoutManager);
//        overnoratCheckRecicler.setHasFixedSize(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.butt_save)
    public void onViewClicked() {
          Object [] bloodArray = bloodTypAdebterRsycler.getBloodArray();
          Object [] governratArray=governoratAdapterRecycler.getGovernoratArray();
        modelApiServices.addNotificationsSettings(apiToken,bloodArray,governratArray)
                      .enqueue(new Callback<NotificationSsettings>() {
                          @Override
                          public void onResponse(Call<NotificationSsettings> call, Response<NotificationSsettings> response) {
                          if (response.code()==200){
                              Toast.makeText(getActivity(),"responsMessg"+response.body().getMsg(),Toast.LENGTH_SHORT).show();
                          }else {Toast.makeText(getActivity(),"responsMessg"+response.body().getMsg(),Toast.LENGTH_SHORT).show();}
                          }

                          @Override
                          public void onFailure(Call<NotificationSsettings> call, Throwable t) {
                              Toast.makeText(getActivity(),"Errror"+t.getMessage(),Toast.LENGTH_SHORT).show();

                          }
                      });


    }
}
