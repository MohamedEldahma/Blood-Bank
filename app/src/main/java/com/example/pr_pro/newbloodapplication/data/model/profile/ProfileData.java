
package com.example.pr_pro.newbloodapplication.data.model.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileData {

    @SerializedName("client")
    @Expose
    private ProfileClient client;

    public ProfileClient getClient() {
        return client;
    }

    public void setClient(ProfileClient client) {
        this.client = client;
    }

}
