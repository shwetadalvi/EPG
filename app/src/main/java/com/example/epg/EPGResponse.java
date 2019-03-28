package com.example.epg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EPGResponse {
    @SerializedName("Transaction")
    @Expose
    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }


}
