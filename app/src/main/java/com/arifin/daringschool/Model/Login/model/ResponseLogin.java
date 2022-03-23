package com.arifin.daringschool.Model.Login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResponseLogin implements Serializable {

    @SerializedName("status")
    @Expose
    private String responseStatus;
    @SerializedName("message")
    @Expose
    private String responseMessage;
    @SerializedName("data")
    @Expose
    private List<Login> responseData = null;

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<Login> getResponseData() {
        return responseData;
    }

    public void setResponseData(List<Login> responseData) {
        this.responseData = responseData;
    }
}
