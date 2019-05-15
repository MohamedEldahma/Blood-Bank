package com.example.pr_pro.newbloodapplication.ui.fragment.loginscreen;




import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.pr_pro.newbloodapplication.R;
import com.example.pr_pro.newbloodapplication.data.model.login.Login;
import com.example.pr_pro.newbloodapplication.data.rest.ModelApiServices;
import com.example.pr_pro.newbloodapplication.data.rest.RetrofitClient;
import com.example.pr_pro.newbloodapplication.helper.HelpeFragmentMethod;
import com.example.pr_pro.newbloodapplication.helper.HelperLogin;
import com.example.pr_pro.newbloodapplication.ui.activity.HomeActivity;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pr_pro.newbloodapplication.helper.SharedPreferencesManger.LoadIntegerData;
import static com.example.pr_pro.newbloodapplication.helper.SharedPreferencesManger.SaveData;
import static com.example.pr_pro.newbloodapplication.helper.SharedPreferencesManger.intent;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.API_TOKEN;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.BIRTH_DATE;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.BLOOD_TYPE;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.CITY_ID;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.DONATION_LAST_DATE;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.EMAIL;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.PHONE;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.REMEMBER_USER;
import static com.example.pr_pro.newbloodapplication.ui.Constant.SharedPreferenceKeys.UserKeys.USER_NAME;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment  {
    Unbinder unbinder;
    @BindView(R.id.logo_id)
    ImageView logoId;
    @BindView(R.id.text_togo_forget)
    TextView textTogoForget;
    @BindView(R.id.check_id)
    CheckBox checkId;
    @BindView(R.id.btt_login)
    Button bttLogin;
    @BindView(R.id.btt_niew_acount)
    Button bttNiewAcount;
    @BindView(R.id.phone_number_id)
    EditText phoneNumberId;
    @BindView(R.id.pasword_text_id)
    EditText paswordTextId;
    private String api_token ;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        rememberUser();
        bttLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check_network = HelperLogin.isNetworkConnected(getActivity(), getView());
                if (check_network == false) {
                    return;
                }
                String phone = phoneNumberId.getText().toString();
                String password=paswordTextId.getText().toString();
                ModelApiServices modelApiServices = RetrofitClient.getClient().create(ModelApiServices.class);

                Call<Login> call = modelApiServices.addLogin(phone,password);
                call.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        if (response.body() != null) {

                            if (response.body().getStatus() == 1){
                                String apiToken=response.body().getData().getApiToken();
                                String name=response.body().getData().getClient().getName();
                                String email=response.body().getData().getClient().getEmail();
                                String phone=response.body().getData().getClient().getPhone();
                                String birthDate=response.body().getData().getClient().getBirthDate();
                                String donationLastDate=response.body().getData().getClient().getDonationLastDate();
                                String bloodType =String.valueOf(response.body().getData().getClient().getBloodType());
                                String cityId =String.valueOf(response.body().getData().getClient().getCity());
                                saveData(apiToken, name, email, phone, birthDate, donationLastDate, cityId, bloodType);
//                              shardLoginData();
                            Intent logIntent = new Intent(getActivity(), HomeActivity.class);
                            startActivity(logIntent);
                            Toast.makeText(getActivity(), "User Tru", Toast.LENGTH_LONG).show();
                        } else if (response.body().getStatus() != 1) {
                            Toast.makeText(getActivity(), "User False", Toast.LENGTH_LONG).show();
                        }}
                    }


                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Toast.makeText(getActivity(),"User Error", Toast.LENGTH_LONG).show();

                    }
                });


            }
        });
        return view;
    }




    private void rememberUser() {
        int remember = LoadIntegerData(getActivity(), REMEMBER_USER);
        if (remember == 1) {
            intent(getContext(), HomeActivity.class);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();




    }
        private void saveData(String apiToken, String name, String email, String phone,
                          String birthDate, String donationLastDate, String cityId, String bloodType) {

        SaveData(getActivity(), API_TOKEN, apiToken);
        SaveData(getActivity(), USER_NAME, name);
        SaveData(getActivity(), EMAIL, email);
        SaveData(getActivity(), PHONE, phone);
        SaveData(getActivity(), BIRTH_DATE, birthDate);
        SaveData(getActivity(), DONATION_LAST_DATE, donationLastDate);
        SaveData(getActivity(), CITY_ID, cityId);
        SaveData(getActivity(), BLOOD_TYPE, bloodType);

    }




//    public void  shardLoginData(){
//        SharedPreferences.Editor editor = this.getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE).edit();
//        editor.putBoolean("Login",true);
//        editor.putString("api_token",api_token);
//        editor.apply();
//
//    }


    @OnClick({R.id.text_togo_forget, R.id.btt_login, R.id.btt_niew_acount})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_togo_forget:
                ForgetPasswordFragment forgetPasswordFragment = new ForgetPasswordFragment();
    HelpeFragmentMethod.replaceFrag(forgetPasswordFragment,getActivity().getSupportFragmentManager(),R.id.fram_login_activity);


                break;
            case R.id.btt_niew_acount:
                NewAccountFragment newAccountFragment = new NewAccountFragment();
                HelpeFragmentMethod.replaceFrag(newAccountFragment, getActivity().getSupportFragmentManager(), R.id.fram_login_activity);

                break;
        }
    }


    }
