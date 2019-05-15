package com.example.pr_pro.newbloodapplication.ui.fragment.loginscreen;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pr_pro.newbloodapplication.R;

import com.example.pr_pro.newbloodapplication.data.model.bloodtypes.BloodTypes;
import com.example.pr_pro.newbloodapplication.data.model.bloodtypes.BloodTypesDatum;
import com.example.pr_pro.newbloodapplication.data.model.cities.Cities;
import com.example.pr_pro.newbloodapplication.data.model.cities.CitiesDatum;
import com.example.pr_pro.newbloodapplication.data.model.governorates.Governorates;
import com.example.pr_pro.newbloodapplication.data.model.governorates.GovernoratesDatum;
import com.example.pr_pro.newbloodapplication.data.model.register.Register;
import com.example.pr_pro.newbloodapplication.data.model.register.RegisterBloodType;
import com.example.pr_pro.newbloodapplication.data.model.register.RegisterData;
import com.example.pr_pro.newbloodapplication.data.rest.ModelApiServices;
import com.example.pr_pro.newbloodapplication.data.rest.RetrofitClient;
import com.example.pr_pro.newbloodapplication.helper.CalendrHelper;
import com.example.pr_pro.newbloodapplication.ui.activity.HomeActivity;


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
import static com.example.pr_pro.newbloodapplication.helper.SharedPreferencesManger.SaveData;
import static com.example.pr_pro.newbloodapplication.helper.TokenContenApi.API_TOKEN;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.BIRTH_DATE;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.BLOOD_TYPE;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.CITY_ID;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.DONATION_LAST_DATE;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.EMAIL;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.PHONE;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.USER_NAME;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewAccountFragment extends Fragment {
    ModelApiServices modelApiServices;

    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_email)
    EditText editEmail;
    @BindView(R.id.edit_birce_date)
    EditText editBirceDate;
    @BindView(R.id.spin_bloodtyp)
    Spinner spinBloodtyp;
    @BindView(R.id.edit_lastdate_typ)
    EditText editLastdateTyp;
    @BindView(R.id.spin_governorat)
    Spinner spinGovernorat;
    @BindView(R.id.spin_city)
    Spinner spinCity;
    @BindView(R.id.edit_phonnumber)
    EditText editPhonnumber;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.edit_confirmpassword)
    EditText editConfirmpassword;
    @BindView(R.id.btt_signin)
    Button bttSignin;
    Unbinder unbinder;
    private String name,email,phone,password,birth_date,donation_last_date, password_confirmation;
    String bloodTyp;
    private int startCityId=0;

    public NewAccountFragment() {// Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_account, container, false);
        modelApiServices =RetrofitClient.getClient().create(ModelApiServices.class);
        unbinder = ButterKnife.bind(this, view);
       getGovernoratfromSever();
        spinnerPloodTyp();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    public void spinnerPloodTyp(){
    modelApiServices.getBloodTyp().enqueue(new Callback<BloodTypes>() {
        @Override
        public void onResponse(Call<BloodTypes> call, Response<BloodTypes> response) {
            List<BloodTypesDatum> bloodTypesData=response.body().getData();
            ArrayList<String>typBlood=new ArrayList<>();
            final ArrayList<Integer>idBlood=new ArrayList<Integer>();
            typBlood.add(getString(R.string.blood_typ));
            idBlood.add(0);
            for (int i=0;i<bloodTypesData.size();i++){
                String bloodTypNAme = bloodTypesData.get(i).getName();
                Integer bloodTypId  = bloodTypesData.get(i).getId();
                typBlood.add(bloodTypNAme);
                idBlood.add(bloodTypId);

                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, typBlood);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                spinBloodtyp.setAdapter(adapter);
                spinBloodtyp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        verbose("onItemSelected: " + parent.getItemAtPosition(position));
                        bloodTyp = String.valueOf(parent.getItemAtPosition(position));
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
                spinGovernorat.setAdapter(adapter);

                spinGovernorat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

                        spinCity.setAdapter(adapter);

                        spinCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


//         public void shardLoginData(){
//             SharedPreferences.Editor editor = this.getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE).edit();
//             editor.putBoolean("Login",true);
//             editor.putString("neme",name);
//             editor.putString("email",email);
//             editor.putString("birth_date",birth_date);
//             editor.putString("donation_last_date",donation_last_date);
//             editor.putString("password",password);
//             editor.putString("passwoordConfirm",password_confirmation);
//             editor.putString("bloodTyp",bloodTyp);
//             editor.putInt("city",startCityId);}


            @OnClick(R.id.btt_signin)
            public void onViewClicked() {

                 name = editName.getText().toString();
                 email = editEmail.getText().toString();
                 birth_date = editBirceDate.getText().toString();
                 phone = editPhonnumber.getText().toString();
                 donation_last_date = editLastdateTyp.getText().toString();
                 password = editPassword.getText().toString();
                 password_confirmation = editConfirmpassword.getText().toString();
//                String blood_type = textBloodtypt.getText().toString();


                modelApiServices
                        .addRegister(name,email,birth_date,password_confirmation,phone,password,donation_last_date,startCityId,bloodTyp)
                        .enqueue(new Callback<Register>() {
                    @Override
                    public void onResponse(Call<Register> call, Response<Register> response) {


                        if (response.body() != null) {
                          if (response.body().getStatus()==1) {
                              RegisterData registerData = response.body().getData();
                              String apiToken = registerData.getApiToken();
                              String name = registerData.getClient().getName();
                              String email = registerData.getClient().getEmail();
                              String phone = registerData.getClient().getPhone();
                              String birthDate = registerData.getClient().getBirthDate();
                              String donationLastDate = registerData.getClient().getDonationLastDate();
//                              String bloodType =String.valueOf(registerData.getClient().getBloodType());
//                              String cityId =String.valueOf(registerData.getClient().getCity());


                              SaveData(getActivity(), API_TOKEN, apiToken);
                              SaveData(getActivity(), USER_NAME, name);
                              SaveData(getActivity(), EMAIL, email);
                              SaveData(getActivity(), PHONE, phone);
                              SaveData(getActivity(), BIRTH_DATE, birthDate);
                              SaveData(getActivity(), DONATION_LAST_DATE, donationLastDate);
//                              SaveData(getActivity(), CITY_ID, cityId);
//                              SaveData(getActivity(), BLOOD_TYPE, bloodType);

                          }
//                            shardLoginData();
                            Intent intent = new Intent(getActivity(), HomeActivity.class);
                            startActivity(intent);
                            Toast.makeText(getActivity(), "Registre is InBut", Toast.LENGTH_LONG).show();

                        } else if (response.body() == null) {
                            Toast.makeText(getActivity(), "Registre is Not InBut", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Register> call, Throwable t) {
                        Toast.makeText(getActivity(), "Registre is InBut Error", Toast.LENGTH_LONG).show();

                    }
                });

            }

            @OnClick({R.id.edit_birce_date, R.id.edit_lastdate_typ})
            public void onViewClicked(View view) {
                switch (view.getId()) {
                    case R.id.edit_birce_date:
                        CalendrHelper calendrHelper = new CalendrHelper(getContext());
                        calendrHelper.showCalendr(editBirceDate);
                        break;
                    case R.id.edit_lastdate_typ:
                        CalendrHelper calendrHelper2 = new CalendrHelper(getContext());
                        calendrHelper2.showCalendr(editLastdateTyp);
                        break;

                }
            }
//    private void saveData(String apiToken, String name, String email, String phone,
//                          String birthDate, String donationLastDate, String cityId, String bloodType) {
//
//        SaveData(getActivity(), API_TOKEN, apiToken);
//        SaveData(getActivity(), USER_NAME, name);
//        SaveData(getActivity(), EMAIL, email);
//        SaveData(getActivity(), PHONE, phone);
//        SaveData(getActivity(), BIRTH_DATE, birthDate);
//        SaveData(getActivity(), DONATION_LAST_DATE, donationLastDate);
//        SaveData(getActivity(), CITY_ID, cityId);
//        SaveData(getActivity(), BLOOD_TYPE, bloodType);
//
//    }


}


