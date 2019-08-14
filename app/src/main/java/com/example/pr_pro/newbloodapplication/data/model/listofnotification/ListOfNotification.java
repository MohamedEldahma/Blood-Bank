
package com.example.pr_pro.newbloodapplication.data.model.listofnotification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListOfNotification {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private DataListNotification data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataListNotification getData() {
        return data;
    }

    public void setData(DataListNotification data) {
        this.data = data;
    }

}
