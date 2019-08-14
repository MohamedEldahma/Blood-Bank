package com.example.pr_pro.newbloodapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pr_pro.newbloodapplication.R;
import com.example.pr_pro.newbloodapplication.data.model.listofnotification.DatumListNotification;
import com.example.pr_pro.newbloodapplication.data.rest.ModelApiServices;
import com.example.pr_pro.newbloodapplication.data.rest.RetrofitClient;
import com.example.pr_pro.newbloodapplication.helper.HelpeFragmentMethod;
import com.example.pr_pro.newbloodapplication.ui.fragment.donation.InformationRequestDetail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetListNotificationAdapter extends RecyclerView.Adapter<GetListNotificationAdapter.ViewHolder> {
    ModelApiServices modelApiServices;
    private Context context;
    private Activity activity;
    private List<DatumListNotification> datumListNotifications ;

    public GetListNotificationAdapter(Context context, Activity activity, List<DatumListNotification> datumListNotifications) {
        this.context = context;
        this.activity = activity;
        this.datumListNotifications = datumListNotifications;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_get_notification_list, viewGroup, false);
        modelApiServices = RetrofitClient.getClient().create(ModelApiServices.class);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.titleNotificationAdapter.setText(datumListNotifications.get(i).getTitle());
        viewHolder.timeNotificationAdapter.setText(datumListNotifications.get(i).getUpdatedAt());
        if (datumListNotifications.get(i).getPivot().getIsRead().equals("0")) {
            viewHolder.imageNotification.setImageResource(R.drawable.ic_siting_notivication);
        } else {
            viewHolder.imageNotification.setImageResource(R.drawable.ic_siting_notivication);
        }


        viewHolder.notificationAdapterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putString("getDonationRequestId", datumListNotifications.get(i).getDonationRequestId());
                bundle.putInt("returnResult", 1);
                Fragment fragment = new InformationRequestDetail();
                fragment.setArguments(bundle);
                HelpeFragmentMethod.replaceFrag( fragment,((FragmentActivity) context).getSupportFragmentManager(), R.id.fram_home);
            }
        });



    }


    @Override
    public int getItemCount() {
        return datumListNotifications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.time_notification_Adapter)
        TextView timeNotificationAdapter;
        @BindView(R.id.title_notification_Adapter)
        TextView titleNotificationAdapter;
        @BindView(R.id.image_notification)
        ImageView imageNotification;
        @BindView(R.id.notificationAdapterLayout)
        LinearLayout notificationAdapterLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
