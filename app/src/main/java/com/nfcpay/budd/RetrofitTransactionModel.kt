package com.nfcpay.budd

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BasketTransaction {
    @SerializedName("itemList")
    @Expose
    var itemList: List<TransactItem>? = null

    @SerializedName("_id")
    @Expose
    var id: String? = null

    @SerializedName("basketId")
    @Expose
    var basketId: Int? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("transactId")
    @Expose
    var transactionId: Int? = null

    @SerializedName("customerId")
    @Expose
    var customerId: String? = null


    @SerializedName("VAT")
    @Expose
    var VAT: Double? = null

    @SerializedName("basketVAT")
    @Expose
    var basketVAT: Double? = null

    @SerializedName("basketSum")
    @Expose
    var basketSum: Double? = null

    @SerializedName("totalSum")
    @Expose
    var totalSum: Double? = null


    @SerializedName("__v")
    @Expose
    var v: Int? = null

}