
package com.example.pr_pro.newbloodapplication.data.model.getprofiledata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetData {

    @SerializedName("client")
    @Expose
    private GetProfileClient client;

    public GetProfileClient getClient() {
        return client;
    }

    public void setClient(GetProfileClient getProfileClient) {
        this.client = getProfileClient;
    }

}
