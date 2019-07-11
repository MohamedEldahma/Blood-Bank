//package com.example.pr_pro.newbloodapplication.ui.activity;
//
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v7.app.AppCompatActivity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.example.pr_pro.newbloodapplication.R;
//import com.example.pr_pro.newbloodapplication.data.model.donation_request.DonationRequest;
//import com.example.pr_pro.newbloodapplication.data.rest.ModelApiServices;
//import com.example.pr_pro.newbloodapplication.data.rest.RetrofitClient;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.MapsInitializer;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import butterknife.Unbinder;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class InformationRequestDetail extends AppCompatActivity implements OnMapReadyCallback {
//
//
//    @BindView(R.id.person_name_text)
//    TextView personNameText;
//    @BindView(R.id.person_age_text)
//    TextView personAgeText;
//    @BindView(R.id.person_blood_type_text)
//    TextView personBloodTypeText;
//    @BindView(R.id.bags_number_text)
//    TextView bagsNumberText;
//    @BindView(R.id.hospital_name_text)
//    TextView hospitalNameText;
//    @BindView(R.id.hospital_address_text)
//    TextView hospitalAddressText;
//    @BindView(R.id.person_phone_number_text)
//    TextView personPhoneNumberText;
//    @BindView(R.id.detail_text)
//    TextView detailText;
//    @BindView(R.id.btt_conect)
//    Button bttConect;
//    Unbinder unbinder;
//    ModelApiServices modelApiServices;
//    String api_token;
//    private GoogleMap googMap;
//    private double sLate ;
//    private double sLong;
//
//
//    public InformationRequestDetail() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_information_request);
//        unbinder = ButterKnife.bind(this);
//        modelApiServices = RetrofitClient.getClient().create(ModelApiServices.class);
//        api_token = "W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl";
//
//        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager()
//                .findFragmentById(R.id.googleMap);
//        if (mapFragment != null) {
//            mapFragment.getMapAsync((OnMapReadyCallback) this);
//        }
//    }
//
//
//    private void addMarker(double mLat, double mLong) {
//        LatLng latLng = new LatLng(mLat, mLong);
//        googMap.addMarker(new MarkerOptions().position(latLng).title("موقع المريض"));
//        googMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));
//    }
//
//
//
//    public void getDonationDetails(){
//
//        modelApiServices.getDonationRequestDetail(api_token, String.valueOf(1))
//                .enqueue(new Callback<DonationRequest>() {
//                    @Override
//                    public void onResponse(Call<DonationRequest> call, Response<DonationRequest> response) {
//                       if (response.body().getStatus() == 1){
//
//                           personNameText.setText(String.format("%s%s",
//                                   getString(R.string.the_name), response.body().getData().getPatientName()));
//                           personAgeText.setText(String.format("%s%s",
//                                   getString(R.string.age), response.body().getData().getPatientAge()));
//                           personBloodTypeText.setText(String.format("%s%s",
//                                   getString(R.string.blood_typ), response.body().getData().getBloodType()));
//                           bagsNumberText.setText(String.format("%s%s",
//                                   getString(R.string.number), response.body().getData().getBagsNum()));
//                           hospitalNameText.setText(String.format("%s%s",
//                                   getString(R.string.hospital_name), response.body().getData().getHospitalName()));
//                           personPhoneNumberText.setText(String.format("%s%s",
//                                   getString(R.string.phon_numpet), response.body().getData().getPhone()));
//                           hospitalAddressText.setText(String.format("%s%s",
//                                   getString(R.string.hospital_address), response.body().getData().getHospitalAddress()));
//                           detailText.setText(response.body().getData().getNotes());
//
//
//                           sLate = Double.parseDouble(response.body().getData().getLatitude());
//                           sLong = Double.parseDouble(response.body().getData().getLongitude());
//                            addMarker(sLate,sLong);
//
//
//
//                       }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<DonationRequest> call, Throwable t) {
//
//                    }
//                });
//    }
//
//
//
//    @OnClick(R.id.btt_conect)
//    public void onViewClicked() {
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        MapsInitializer.initialize(this);
//        this.googMap = googleMap;
//
//    }
//}
