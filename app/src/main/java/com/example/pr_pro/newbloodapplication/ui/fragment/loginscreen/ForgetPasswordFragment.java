package com.example.pr_pro.newbloodapplication.ui.fragment.loginscreen;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.pr_pro.newbloodapplication.R;
import com.example.pr_pro.newbloodapplication.data.model.resetpassword.ResetPassword;
import com.example.pr_pro.newbloodapplication.data.rest.ModelApiServices;
import com.example.pr_pro.newbloodapplication.data.rest.RetrofitClient;
import com.example.pr_pro.newbloodapplication.helper.HelpeFragmentMethod;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static com.example.pr_pro.newbloodapplication.helper.HelpeFragmentMethod.tosatShow;
import static com.example.pr_pro.newbloodapplication.helper.HelperLogin.error;
import static com.example.pr_pro.newbloodapplication.helper.HelperLogin.verbose;
import static com.example.pr_pro.newbloodapplication.helper.VaildClass.validPhone;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPasswordFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.edit_phone_number)
    EditText editPhoneNumber;
    @BindView(R.id.btt_sendmassg)
    Button bttSendmassg;
    ModelApiServices modelApiServices;
    private FragmentManager fragmentManager;
    public ForgetPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forget_password, container, false);
        fragmentManager =getFragmentManager();
        modelApiServices=RetrofitClient.getClient().create(ModelApiServices.class);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btt_sendmassg)
    public void onViewClicked() {sendReturnPasswordCod(); }

    public void sendReturnPasswordCod() {
        String phoneNumber =editPhoneNumber.getText().toString();
     if (!validPhone(phoneNumber)){
         editPhoneNumber.setError("phone number must 11 number");
         return;
     }
     sendReturnCod(phoneNumber);
    }


    public  void  sendReturnCod(final String phoneNumber){

   modelApiServices.addResetPassword(phoneNumber)
           .enqueue(new Callback<ResetPassword>() {
               @Override
               public void onResponse(Call<ResetPassword> call, Response<ResetPassword> response) {
                   if (response.isSuccessful() && response.body().getStatus() ==
                           1){
                       tosatShow(getContext(),response.body().getMsg());
                       Bundle bundle = new Bundle();

                       bundle.putString("phone",phoneNumber);
                       verbose("onRestPasswordResponse: pin code = " + phoneNumber);

                       ChangPasswordFragment smsChangPassworFragment = new ChangPasswordFragment();
                       smsChangPassworFragment.setArguments(bundle);
                     HelpeFragmentMethod.replaceFrag(smsChangPassworFragment,getActivity().getSupportFragmentManager(),R.id.fram_login_activity);

                   } else {
                       tosatShow(getContext(), response.body().getMsg());
                   }
               }


               @Override
               public void onFailure(Call<ResetPassword> call, Throwable t) {
                   error("onRestPasswordFailure: " + t.getMessage());

               }
           });


    }
}
