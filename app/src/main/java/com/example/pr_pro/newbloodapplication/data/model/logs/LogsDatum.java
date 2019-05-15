
package com.example.pr_pro.newbloodapplication.data.model.logs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogsDatum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("content")
    @Expose
    private LogsContent content;
    @SerializedName("service")
    @Expose
    private String service;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LogsContent getContent() {
        return content;
    }

    public void setContent(LogsContent logsContent) {
        this.content = logsContent;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

}
