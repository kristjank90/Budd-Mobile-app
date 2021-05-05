package com.nfcpay.budd

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TransactItem {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("price")
    @Expose
    var price: Double? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("currency")
    @Expose
    var currency: String? = null

    @SerializedName("amount")
    @Expose
    var amount: Int? = null

}