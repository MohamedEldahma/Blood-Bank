package com.example.pr_pro.newbloodapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.pr_pro.newbloodapplication.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationSeetingAdaper extends RecyclerView.Adapter<NotificationSeetingAdaper.ViewHolder> {


    private List<String> listData = new ArrayList<>();
    private Context context;
    private int typRecyclerData;


    private List<String> bloodTypNotification = new ArrayList<>();
    private List<Integer> governoratNotification = new ArrayList<>();


    public NotificationSeetingAdaper(List<String> listData, Context context, int typRecyclerData) {
        this.listData = listData;
        this.context = context;
        this.typRecyclerData = typRecyclerData;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.notificationsetting_custom_adapter, viewGroup, false);

        notifyDataSetChanged();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        String data = listData.get(position);

        setData(viewHolder, data);
        setActionData(viewHolder, position, data);

    }

    public void setActionData(final ViewHolder viewHolder, final int positin, final String data) {
        viewHolder.governoratCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (typRecyclerData == 1) {
                    String checkText = buttonView.getText().toString();
                    bloodTypNotification.add(checkText);
                } else {
                    governoratNotification.add((positin + 1));
                }

            }
        });

    }

    private void setData(ViewHolder viewHolder, String data) {
        viewHolder.governoratCheck.setText(data);

    }

    @Override
    public int getItemCount() {
        if (listData == null) {
        }
        return (listData.size());
    }

    public Object[] getBloodArray() {
        return bloodTypNotification.toArray();
    }

    public Object[] getGovernoratArray() {
        return governoratNotification.toArray();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.governorat_check)
        CheckBox governoratCheck;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
