package com.example.pr_pro.newbloodapplication.ui.fragment.donation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pr_pro.newbloodapplication.R;
import com.example.pr_pro.newbloodapplication.data.model.bloodtypes.BloodDatum;
import com.example.pr_pro.newbloodapplication.data.model.bloodtypes.BloodTypes;
import com.example.pr_pro.newbloodapplication.data.model.cities.Cities;
import com.example.pr_pro.newbloodapplication.data.model.cities.CitiesDatum;
import com.example.pr_pro.newbloodapplication.data.model.creatdonationrequest.CreatDonationRequest;
import com.example.pr_pro.newbloodapplication.data.model.governorates.Governorates;
import com.example.pr_pro.newbloodapplication.data.model.governorates.GovernoratesDatum;
import com.example.pr_pro.newbloodapplication.data.rest.ModelApiServices;
import com.example.pr_pro.newbloodapplication.data.rest.RetrofitClient;
import com.example.pr_pro.newbloodapplication.helper.HelpeFragmentMethod;
import com.example.pr_pro.newbloodapplication.ui.activity.MapLocationActivity;
import com.example.pr_pro.newbloodapplication.ui.fragment.homscreen.DonationRequestFragment;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.example.pr_pro.newbloodapplication.helper.HelperLogin.error;
import static com.example.pr_pro.newbloodapplication.helper.HelperLogin.isNetworkConnected;
import static com.example.pr_pro.newbloodapplication.helper.HelperLogin.verbose;
import static com.example.pr_pro.newbloodapplication.helper.SharedPreferencesManger.LoadStringData;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.API_TOKEN;
import static com.example.pr_pro.newbloodapplication.ui.activity.MapLocationActivity.latitude;
import static com.example.pr_pro.newbloodapplication.ui.activity.MapLocationActivity.longitude;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreatRequestDonation extends Fragment {
//    private static final int PLACE_PICKER_REQUEST = 100;


    @BindView(R.id.request_name)
    EditText requestName;
    @BindView(R.id.request_age)
    EditText requestAge;
    @BindView(R.id.Request_Blood_type)
    Spinner RequestBloodType;
    @BindView(R.id.Number_Blood)
    EditText NumberBlood;
    @BindView(R.id.Hospital_name)
    EditText HospitalName;
    @BindView(R.id.location_Hospital)
    Button locationHospital;
    @BindView(R.id.Address_hospital)
    EditText AddressHospital;
    @BindView(R.id.Make_request_layout_Hospital_address)
    LinearLayout MakeRequestLayoutHospitalAddress;
    @BindView(R.id.Spinner_Cantry)
    Spinner SpinnerCantry;
    @BindView(R.id.Make_Request_layout_cantry)
    LinearLayout MakeRequestLayoutCantry;
    @BindView(R.id.Spinner_Cities)
    Spinner SpinnerCities;
    @BindView(R.id.Make_Request_layout_cities)
    LinearLayout MakeRequestLayoutCities;
    @BindView(R.id.Phone_Number)
    EditText PhoneNumber;
    @BindView(R.id.Request_notes)
    EditText RequestNotes;
    @BindView(R.id.btn_send_request)
    Button btnSendRequest;
    Unbinder unbinder;
    ModelApiServices modelApiServices;
    private    String  bloodTyp ;
    private    int startCityId;
    private     String api_token ;
    int bagsNum;

    public CreatRequestDonation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_creat_request_donation2, container, false);
        unbinder = ButterKnife.bind(this, view);
        modelApiServices= RetrofitClient.getClient().create(ModelApiServices.class);
        api_token = "YbAaJa3SuvnrvuctylVgryH97RqaV4gh2t0FxMf6igMncu9sHpw7ICGW6wUs";
//        api_token="YbAaJa3SuvnrvuctylVgryH97RqaV4gh2t0FxMf6igMncu9sHpw7ICGW6wUs";

        spinnerPloodTyp();
        getGovernoratfromSever();
        return view;

    }



    public void  creatRequest(){

        String name = requestName.getText().toString();
        String age = requestAge.getText().toString();
        bagsNum = Integer.parseInt(NumberBlood.getText().toString());
        String hospitalAddress = AddressHospital.getText().toString();
        String phoneNumber = PhoneNumber.getText().toString();
        String comment = RequestNotes.getText().toString();
        String hospitalName = HospitalName.getText().toString();

        modelApiServices.addDonationRequestCreate(api_token,name,age, bloodTyp, bagsNum,hospitalName,hospitalAddress,
                    startCityId,phoneNumber,comment,31.7655,30.7541).enqueue(new Callback<CreatDonationRequest>() {
            @Override
            public void onResponse(Call<CreatDonationRequest> call, Response<CreatDonationRequest> response) {
                if (response.body().getStatus() == 1){
                    Toast.makeText(getContext(), "Your Donation Request Created", Toast.LENGTH_SHORT).show();
                    HelpeFragmentMethod.replaceFrag(new DonationRequestFragment(),getFragmentManager(),R.id.fram_home);
                }else {Toast.makeText(getContext(), "Your Donation Request Not Created", Toast.LENGTH_SHORT).show();}

            }

            @Override
            public void onFailure(Call<CreatDonationRequest> call, Throwable t) {
                Toast.makeText(getContext(), "Your Donation Request Erorr", Toast.LENGTH_SHORT).show();

            }
        });

    }


    public void spinnerPloodTyp() {
        modelApiServices.getBloodTyp().enqueue(new Callback<BloodTypes>() {
            @Override
            public void onResponse(Call<BloodTypes> call, Response<BloodTypes> response) {
                List<BloodDatum> bloodTypesData=response.body().getData();
                ArrayList<String> typBlood=new ArrayList<>();
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


                    RequestBloodType.setAdapter(adapter);
                    RequestBloodType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            verbose("onItemSelected: " + parent.getItemAtPosition(position));
                            bloodTyp = (String) parent.getItemAtPosition(position);
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
                SpinnerCantry.setAdapter(adapter);

                SpinnerCantry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

                        SpinnerCities.setAdapter(adapter);

                        SpinnerCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
//
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.location_Hospital, R.id.btn_send_request})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.location_Hospital:
                startActivity(new Intent(getActivity(), MapLocationActivity.class));

//                openPlacePicker();

                break;
            case R.id.btn_send_request:
                creatRequest();
                break;
        }
    }

//    private void openPlacePicker() {
//        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
//        try {
//            startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
//        } catch (GooglePlayServicesRepairableException e) {
//            e.printStackTrace();
//        } catch (GooglePlayServicesNotAvailableException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == PLACE_PICKER_REQUEST) {
//            if (resultCode == RESULT_OK) {
//                Place place = PlacePicker.getPlace(getActivity(), data);
//                lat = place.getLatLng().latitude;
//                lon = place.getLatLng().longitude;
//
//                String placeAddress = String.valueOf(place.getAddress());
//                AddressHospital.setText(placeAddress);
//
//
//                verbose("location longitude and latitude: " + lon + ", " + lat);
//            }
//        }
//
//    }
}
