package com.example.pr_pro.newbloodapplication.ui.fragment.loginscreen;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.example.pr_pro.newbloodapplication.R;

import com.example.pr_pro.newbloodapplication.data.model.newpassword.NewPassword;
import com.example.pr_pro.newbloodapplication.data.rest.ModelApiServices;
import com.example.pr_pro.newbloodapplication.data.rest.RetrofitClient;
import com.example.pr_pro.newbloodapplication.ui.activity.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pr_pro.newbloodapplication.helper.HelpeFragmentMethod.tosatShow;
import static com.example.pr_pro.newbloodapplication.helper.HelperLogin.verbose;
import static com.example.pr_pro.newbloodapplication.helper.VaildClass.validConfirmPassword;
import static com.example.pr_pro.newbloodapplication.helper.VaildClass.validPassword;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangPasswordFragment extends Fragment {

    @BindView(R.id.id_sms_pin_cod)
    EditText idSmsPinCod;
    @BindView(R.id.new_pasword_pncod)
    EditText newPaswordPncod;
    @BindView(R.id.confirm_pasword_picod)
    EditText confirmPaswordPicod;
    @BindView(R.id.chang_password)
    Button changPassword;
    Unbinder unbinder;
    private FragmentManager fragmentManager;
   private String pinCodPhoneNumber;
   private ModelApiServices modelApiServices;


    public ChangPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chang_password, container, false);
//        modelApiServices = RetrofitClient.getClient().create(ModelApiServices.class);
//        unbinder = ButterKnife.bind(this, view);

        if (getArguments() !=null){
    pinCodPhoneNumber = getArguments().getString("phone");
        }
        verbose("SMS vChangPasswordFragment: phone number: " + pinCodPhoneNumber);
        modelApiServices = RetrofitClient.getClient().create(ModelApiServices.class);
        fragmentManager=getFragmentManager();
        unbinder = ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.chang_password)
    public void onViewClicked() {

        String pinCode = idSmsPinCod.getText().toString();
        String password = newPaswordPncod .getText().toString();
        String confirmPasword=confirmPaswordPicod.getText().toString();
        changPasswordReturn(pinCode,password,confirmPasword);
    }


    public  void changPasswordReturn(String pinCode,String password,String confirmPassword ){
        if (!validPassword(password)){
          newPaswordPncod.setError(getString(R.string.errorPassword_Input));
            return;
        }

        if (!validConfirmPassword(password ,confirmPassword)) {
            confirmPaswordPicod.setError("The password is wrong.");
            return;
        }
        creatNewPassword(pinCode,password,confirmPassword,pinCodPhoneNumber);
}

public void creatNewPassword(String pinCode,String password,String confirmPasword,String pinCodPhoneNumber ) {
    modelApiServices.inputNewPassword(pinCode, password, confirmPasword, pinCodPhoneNumber).enqueue(new Callback<NewPassword>() {
        public void onResponse(Call<NewPassword> call, Response<NewPassword> response) {
            if (response.isSuccessful() && response.body().getStatus() == 1) {
                tosatShow(getContext(), response.body().getMsg());
                Intent intentNewPassword = new Intent(getContext(), HomeActivity.class);
                startActivity(intentNewPassword);
            }
        }

        @Override
        public void onFailure(Call<NewPassword> call, Throwable t) {
            verbose("onCreatNewPasswordOnFailure: " + t.getMessage());

        }
    });

}}
