package com.nfcpay.budd
import retrofit2.Call
import retrofit2.http.GET

public interface ApiInterface {
    @GET("/baskets/transaction/1")
    fun fetchTransactions(): Call<Example>
}