package com.example.pr_pro.newbloodapplication.ui.fragment.homscreen;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.pr_pro.newbloodapplication.R;
import com.example.pr_pro.newbloodapplication.data.model.bloodtypes.BloodTypes;
import com.example.pr_pro.newbloodapplication.data.model.bloodtypes.BloodTypesDatum;
import com.example.pr_pro.newbloodapplication.data.model.cities.Cities;
import com.example.pr_pro.newbloodapplication.data.model.cities.CitiesDatum;
import com.example.pr_pro.newbloodapplication.data.model.governorates.Governorates;
import com.example.pr_pro.newbloodapplication.data.model.governorates.GovernoratesDatum;
import com.example.pr_pro.newbloodapplication.data.rest.ModelApiServices;
import com.example.pr_pro.newbloodapplication.data.rest.RetrofitClient;
import com.example.pr_pro.newbloodapplication.helper.CalendrHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pr_pro.newbloodapplication.helper.HelperLogin.error;
import static com.example.pr_pro.newbloodapplication.helper.HelperLogin.verbose;
import static com.example.pr_pro.newbloodapplication.helper.SharedPreferencesManger.LoadStringData;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.API_TOKEN;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.BIRTH_DATE;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.CITY_ID;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.DONATION_LAST_DATE;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.EMAIL;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.PHONE;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.USER_NAME;
import static java.lang.String.valueOf;

public class InformationNavigationFragment extends Fragment {

    @BindView(R.id.info_edit_name)
    EditText infoEditName;
    @BindView(R.id.info_edit_email)
    EditText infoEditEmail;
    @BindView(R.id.info_edit_birce_date)
    EditText infoEditBirceDate;
    @BindView(R.id.info_spin_bloodtyp)
    Spinner infoSpinBloodtyp;
    @BindView(R.id.info_lastdate_typ)
    EditText infoLastdateTyp;
    @BindView(R.id.info_spin_governorat)
    Spinner infoSpinGovernorat;
    @BindView(R.id.info_spin_city)
    Spinner infoSpinCity;
    @BindView(R.id.info_edit_phonnumber)
    EditText infoEditPhonnumber;
    @BindView(R.id.info_edit_password)
    EditText infoEditPassword;
    @BindView(R.id.edit_confirmpassword)
    EditText editConfirmpassword;
    @BindView(R.id.btt_update_info)
    Button bttUpdateInfo;
    ModelApiServices modelApiServices;
    private int startCityId;
    String bloodTyp;
    SharedPreferences sharedPreferences;
    String apiToken;
    private String name,email,phone,password,birth_date,donation_last_date, password_confirmation;
    int iid;
    Unbinder unbinder;


    public InformationNavigationFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_navigation, container, false);
        modelApiServices = RetrofitClient.getClient().create(ModelApiServices.class);
        unbinder = ButterKnife.bind(this, view);
        spinnerPloodTyp();
        getGovernoratfromSever();
         apiToken=LoadStringData(getActivity(),API_TOKEN);
         getShowData();
        return view;
    }

    public void spinnerPloodTyp() {
        modelApiServices.getBloodTyp().enqueue(new Callback<BloodTypes>() {
            @Override
            public void onResponse(Call<BloodTypes> call, Response<BloodTypes> response) {
                List<BloodTypesDatum> bloodTypesData = response.body().getData();
                ArrayList<String> typBlood = new ArrayList<>();
                final ArrayList<Integer> idBlood = new ArrayList<Integer>();
                typBlood.add(getString(R.string.blood_typ));
                idBlood.add(0);
                for (int i = 0; i < bloodTypesData.size(); i++) {
                    String bloodTypNAme = bloodTypesData.get(i).getName();
                    Integer bloodTypId = bloodTypesData.get(i).getId();
                    typBlood.add(bloodTypNAme);
                    idBlood.add(bloodTypId);

                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_spinner_item, typBlood);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                    infoSpinBloodtyp.setAdapter(adapter);
                    infoSpinBloodtyp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            verbose("onItemSelected: " + parent.getItemAtPosition(position));
                            InformationNavigationFragment.this.bloodTyp = valueOf(parent.getItemAtPosition(position));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }


            }

            @Override
            public void onFailure(Call<BloodTypes> call, Throwable t) {

            }
        });

    }

    public void getGovernoratfromSever() {

        modelApiServices.getGovernorates().enqueue(new Callback<Governorates>() {
            @Override
            public void onResponse(Call<Governorates> call, Response<Governorates> response) {
                List<GovernoratesDatum> governoratesDatumList = response.body().getData();
                ArrayList<String> governorat = new ArrayList<>();
                final ArrayList<Integer> idGovern = new ArrayList<>();
                governorat.add(getString(R.string.governorate));
                idGovern.add(0);

                for (int i = 0; i < governoratesDatumList.size(); i++) {

                    String governorateName = governoratesDatumList.get(i).getName();
                    Integer governoratId = governoratesDatumList.get(i).getId();
                    governorat.add(governorateName);
                    idGovern.add(governoratId);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, governorat);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                infoSpinGovernorat.setAdapter(adapter);

                infoSpinGovernorat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position != 0) {
                            setSpinnerCity((idGovern.get(position)));
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }


            @Override
            public void onFailure(Call<Governorates> call, Throwable t) {
                error("GavernoratesResponse Onfailure: " + t.getMessage());


            }

        });
    }

    private void setSpinnerCity(Integer gavernoratesId) {
        modelApiServices.getCities(gavernoratesId)
                .enqueue(new Callback<Cities>() {
                    @Override
                    public void onResponse(Call<Cities> call, Response<Cities> response) {
                        List<CitiesDatum> citiesDatumList = response.body().getData();
                        ArrayList<String> cities = new ArrayList<>();
                        final ArrayList<Integer> citiesId = new ArrayList<>();

                        cities.add(getString(R.string.city));
                        citiesId.add(0);

                        for (int i = 0; i < citiesDatumList.size(); i++) {
                            String cityName = citiesDatumList.get(i).getName();
                            Integer cityId = citiesDatumList.get(i).getId();

                            cities.add(cityName);
                            citiesId.add(cityId);
                        }

                        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                                android.R.layout.simple_spinner_item, cities);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        infoSpinCity.setAdapter(adapter);

                        infoSpinCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position != 0) {
                                    verbose("onCityItemSelected: " + citiesId.get(position));
                                    startCityId = citiesId.get(position);
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<Cities> call, Throwable t) {
                        error("CitiesResponse Onfailure: " + t.getMessage());

                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }




//    public void shardLoginData(){
//        SharedPreferences.Editor editor = this.getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE).edit();
//        editor.putBoolean("Login",true);
//        editor.putString("neme",name);
//        editor.putString("email",email);
//        editor.putString("birth_date",birth_date);
//        editor.putString("donation_last_date",donation_last_date);
//        editor.putString("password",password);
//        editor.putString("passwoordConfirm",password_confirmation);
//        editor.putString("bloodTyp",bloodTyp);
//        editor.putString("api_token",api_token);
//
//        editor.putInt("city",startCityId);

        public void getShowData(){

            String name =LoadStringData(getActivity(),USER_NAME);
            infoEditName.setText(name);
            String email =LoadStringData(getActivity(),EMAIL);
            infoEditEmail.setText(email);
            String birthDate =LoadStringData(getActivity(),BIRTH_DATE);
            infoEditBirceDate.setText(birthDate);
            String donationLastDate =LoadStringData(getActivity(),DONATION_LAST_DATE);
            infoLastdateTyp.setText(donationLastDate);
            String passwor =LoadStringData(getActivity(),USER_NAME);
            infoEditPassword.setText(passwor);
            String phone =LoadStringData(getActivity(), PHONE);
            infoEditPhonnumber.setText(phone);
//            int   bloodTyp=Integer.parseInt( LoadStringData(getActivity(),BLOOD_TYPE));
//            spinnerPloodTyp(bloodTyp);
//            int  cityId=Integer.parseInt(LoadStringData(getActivity(),CITY_ID));
//            getGovernoratfromSever(cityId);



        }





    @OnClick(R.id.btt_update_info)
    public void onViewClicked() {



    }

    @OnClick({R.id.info_edit_birce_date, R.id.info_lastdate_typ})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.info_edit_birce_date:
                CalendrHelper calendrHelper = new CalendrHelper(getContext());
                calendrHelper.showCalendr(infoEditBirceDate);
                break;
            case R.id.info_lastdate_typ:
                CalendrHelper calendrHelper1 = new CalendrHelper(getContext());
                calendrHelper1.showCalendr(infoLastdateTyp);
                break;
        }
    }
}
