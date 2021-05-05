package com.nfcpay.budd

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BasketTransactions {
    // Includes all the Basket transaction API functions
    fun fetchTransactions(): LiveData<BasketTransaction> { // Get all basket transactions via Retrofit
        val data = MutableLiveData<BasketTransaction>()
        val service = ApiClient.getApiClient().create(ApiInterface::class.java)
        service.fetchTransactions().enqueue(object : Callback<BasketTransaction> {

            override fun onFailure(call: Call<BasketTransaction>, t: Throwable) {
                // data.value = null (TODO) check why this was needed originally?
                Log.e("Retrofit onFailure", "Error.")
                t.message?.let { Log.e("Retrofit onFailure", it )}
            }

            override fun onResponse(call: Call<BasketTransaction>, response: Response<BasketTransaction>) {
                Log.i("Retrofit onResponse", "Received a response!")
                val res = response.body()
                Log.i("Data", (data).toString())
                if (response.code() == 200 && res != null) {
                    data.value = res
                } else {
                   // data.value = null
                    Log.e("Retrofit onResponse", "Incorrect response. Code, ${response.code().toString()}" )
                }
            }
        })
        return data
    }
}