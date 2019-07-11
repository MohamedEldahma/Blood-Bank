//package com.example.pr_pro.newbloodapplication.ui.fragment;
//
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import com.example.pr_pro.newbloodapplication.R;
//import com.example.pr_pro.newbloodapplication.data.model.bloodtypes.BloodTypes;
//import com.example.pr_pro.newbloodapplication.data.model.bloodtypes.BloodTypesDatum;
//import com.example.pr_pro.newbloodapplication.data.model.cities.Cities;
//import com.example.pr_pro.newbloodapplication.data.model.cities.CitiesDatum;
//import com.example.pr_pro.newbloodapplication.data.model.donation_request_creat.DonationRequestCreat;
//import com.example.pr_pro.newbloodapplication.data.model.governorates.Governorates;
//import com.example.pr_pro.newbloodapplication.data.model.governorates.GovernoratesDatum;
//import com.example.pr_pro.newbloodapplication.data.rest.ModelApiServices;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import butterknife.Unbinder;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//import static com.example.pr_pro.newbloodapplication.helper.HelperLogin.error;
//import static com.example.pr_pro.newbloodapplication.helper.HelperLogin.verbose;
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class CreatRequestDonationFragment extends Fragment {
//
//
//    @BindView(R.id.name_editText)
//    EditText nameEditText;
//    @BindView(R.id.age_editText)
//    EditText ageEditText;
//
//    @BindView(R.id.bags_number_editText)
//    EditText bagsNumberEditText;
//    @BindView(R.id.hospital_name_editText)
//    EditText hospitalNameEditText;
//    @BindView(R.id.hospital_address_editText)
//    EditText hospitalAddressEditText;
//    @BindView(R.id.my_locationButton)
//    Button myLocationButton;
//    @BindView(R.id.state_spinner)
//    Spinner stateSpinner;
//    @BindView(R.id.city_spinner)
//    Spinner citySpinner;
//    @BindView(R.id.phone_number_editText)
//    EditText phoneNumberEditText;
//    @BindView(R.id.comments_editText)
//    EditText commentsEditText;
//    @BindView(R.id.send_button)
//    Button sendButton;
//    Unbinder unbinder;
//
//    String api_token ;
//    ModelApiServices modelApiServices;
//    String bloodTyp;
//    int startCityId;
//    double lon;
//    double lat;
//
//    public CreatRequestDonationFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_creat_request_donation, container, false);
//        unbinder = ButterKnife.bind(this, view);
//        return view;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
//    public void spinnerPloodTyp(){
//        modelApiServices.getBloodTyp().enqueue(new Callback<BloodTypes>() {
//            @Override
//            public void onResponse(Call<BloodTypes> call, Response<BloodTypes> response) {
//                List<BloodTypesDatum> bloodTypesData=response.body().getData();
//                ArrayList<String> typBlood=new ArrayList<>();
//                final ArrayList<Integer>idBlood=new ArrayList<Integer>();
//                typBlood.add(getString(R.string.blood_typ));
//                idBlood.add(0);
//                for (int i=0;i<bloodTypesData.size();i++){
//                    String bloodTypNAme = bloodTypesData.get(i).getName();
//                    Integer bloodTypId  = bloodTypesData.get(i).getId();
//                    typBlood.add(bloodTypNAme);
//                    idBlood.add(bloodTypId);
//
//                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
//                            android.R.layout.simple_spinner_item, typBlood);
//                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//
//                    bloodTypeSpinner.setAdapter(adapter);
//                    bloodTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                            verbose("onItemSelected: " + parent.getItemAtPosition(position));
//                            bloodTyp = String.valueOf(parent.getItemAtPosition(position));
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> parent) {
//
//                        }
//                    });
//                }
//            }
//            @Override
//            public void onFailure(Call<BloodTypes> call, Throwable t) {
//
//            }
//        });
//
//    }
//
//
//
//    public void  creatRequest(){
//
//        String name = nameEditText.getText().toString();
//        String age = ageEditText.getText().toString();
//        String bagsNum = bagsNumberEditText.getText().toString();
//        String hospitalAddress = hospitalAddressEditText.getText().toString();
//        String phoneNumber = phoneNumberEditText.getText().toString();
//        String comment = commentsEditText.getText().toString();
//        String hospitalName = hospitalNameEditText.getText().toString();
//
//        modelApiServices.addDonationRequestCreate(api_token,name,age,bloodTyp, Integer.parseInt(bagsNum),hospitalName,hospitalAddress,
//                    startCityId,phoneNumber,comment,lat,lon).enqueue(new Callback<DonationRequestCreat>() {
//            @Override
//            public void onResponse(Call<DonationRequestCreat> call, Response<DonationRequestCreat> response) {
//                if (response.body().getStatus() == 1){
//                    Toast.makeText(getContext(), "Your Donation Request Created", Toast.LENGTH_SHORT).show();
//                }else {Toast.makeText(getContext(), "Your Donation Request Not Created", Toast.LENGTH_SHORT).show();}
//
//            }
//
//            @Override
//            public void onFailure(Call<DonationRequestCreat> call, Throwable t) {
//                Toast.makeText(getContext(), "Your Donation Request Erorr", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//    }
//
//
//    public void getGovernoratfromSever() {
//
//        modelApiServices.getGovernorates().enqueue(new Callback<Governorates>() {
//            @Override
//            public void onResponse(Call<Governorates> call, Response<Governorates> response) {
//                List<GovernoratesDatum> governoratesDatumList = response.body().getData();
//                ArrayList<String> governorat = new ArrayList<>();
//                final ArrayList<Integer> idGovern = new ArrayList<>();
//                governorat.add(getString(R.string.governorate));
//                idGovern.add(0);
//
//                for (int i = 0; i < governoratesDatumList.size(); i++) {
//
//                    String governorateName = governoratesDatumList.get(i).getName();
//                    Integer governoratId = governoratesDatumList.get(i).getId();
//                    governorat.add(governorateName);
//                    idGovern.add(governoratId);
//                }
//
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
//                        android.R.layout.simple_spinner_item, governorat);
//
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                stateSpinner.setAdapter(adapter);
//
//                stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        if (position != 0) {
//                            setSpinnerCity((idGovern.get(position)));
//                        }
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//
//                    }
//                });
//
//            }
//
//
//            @Override
//            public void onFailure(Call<Governorates> call, Throwable t) {
//                error("GavernoratesResponse Onfailure: " + t.getMessage());
//
//
//
//            }
//
//        });
//    }
//
//    private void setSpinnerCity(Integer gavernoratesId) {
//        modelApiServices.getCities(gavernoratesId)
//                .enqueue(new Callback<Cities>() {
//                    @Override
//                    public void onResponse(Call<Cities> call, Response<Cities> response) {
//                        List<CitiesDatum> citiesDatumList = response.body().getData();
//                        ArrayList<String> cities = new ArrayList<>();
//                        final ArrayList<Integer> citiesId = new ArrayList<>();
//
//                        cities.add(getString(R.string.city));
//                        citiesId.add(0);
//
//                        for (int i = 0; i < citiesDatumList.size(); i++) {
//                            String cityName = citiesDatumList.get(i).getName();
//                            Integer cityId = citiesDatumList.get(i).getId();
//
//                            cities.add(cityName);
//                            citiesId.add(cityId);
//                        }
//
//                        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
//                                android.R.layout.simple_spinner_item, cities);
//
//                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//                        citySpinner.setAdapter(adapter);
//
//                        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                            @Override
//                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                if (position != 0) {
//                                    verbose("onCityItemSelected: " + citiesId.get(position));
//                                    startCityId = citiesId.get(position);
//                                }
//
//                            }
//
//                            @Override
//                            public void onNothingSelected(AdapterView<?> parent) {
//
//                            }
//                        });
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<Cities> call, Throwable t) {
//                        error("CitiesResponse Onfailure: " + t.getMessage());
//
//                    }
//                });
//    }
//
//
//
//
//
//    @OnClick({R.id.my_locationButton, R.id.send_button})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.my_locationButton:
//                break;
//            case R.id.send_button:
//                break;
//        }
//    }
//}
