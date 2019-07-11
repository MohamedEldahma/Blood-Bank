package com.example.pr_pro.newbloodapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pr_pro.newbloodapplication.R;
import com.example.pr_pro.newbloodapplication.data.model.donationrequests.DonationRequestsDatum;
import com.example.pr_pro.newbloodapplication.ui.activity.InformationRequestDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.pr_pro.newbloodapplication.helper.HelpeFragmentMethod.intentWithExtra;
import static com.example.pr_pro.newbloodapplication.helper.HelpeFragmentMethod.intentWithPhone;

public class RequesteBloodRecyclrView extends RecyclerView.Adapter<RequesteBloodRecyclrView.ViewHolder> {


    private List<DonationRequestsDatum> donationRequestsDatumList = new ArrayList<>();
    private Context context;

    public RequesteBloodRecyclrView(List<DonationRequestsDatum> donationRequestsDatumList, Context context) {
        this.donationRequestsDatumList = donationRequestsDatumList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.blood_item_request_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int postion) {
        final DonationRequestsDatum donationRequestsData = donationRequestsDatumList.get(postion);
       viewHolder.textBloodTyp.setText(donationRequestsData.getBloodTypeId());
       viewHolder.patientTextName.setText(donationRequestsData.getPatientName());
       viewHolder.cityTextName.setText(donationRequestsData.getCity().getName());
       viewHolder.hospitalTextName.setText(donationRequestsData.getHospitalName());
       viewHolder.contactBotton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               intentWithPhone(context,donationRequestsData.getPhone());
           }
       });
      viewHolder.detailBotton.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View v) {
               String donationId = String.valueOf(donationRequestsData.getId());
               intentWithExtra(context, InformationRequestDetailActivity.class, "donation_id", donationId);
           }
       });
    }

    @Override
    public int getItemCount() {
        return donationRequestsDatumList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_blood_typ)
        TextView textBloodTyp;
        @BindView(R.id.patient_text_name)
        TextView patientTextName;
        @BindView(R.id.hospital_text_name)
        TextView hospitalTextName;
        @BindView(R.id.city_text_name)
        TextView cityTextName;
        @BindView(R.id.detail_botton)
        Button detailBotton;
        @BindView(R.id.contact_botton)
        Button contactBotton;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
