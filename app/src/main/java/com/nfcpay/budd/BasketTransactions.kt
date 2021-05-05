package com.nfcpay.budd

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nfcpay.budd.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BasketTransactions {
    // Includes all the Basket transaction API functions
    fun fetchTransactions(): LiveData<Example> { // Get all basket transactions via Retrofit
        val data = MutableLiveData<Example>()
        val service = ApiClient.getApiClient().create(ApiInterface::class.java)
        service.fetchTransactions().enqueue(object : Callback<Example> {

            override fun onFailure(call: Call<Example>, t: Throwable) {
                data.value = null
                Log.e("Retrofit onFailure", "Error.")
                t.message?.let { Log.e("Retrofit onFailure", it )}
            }

            override fun onResponse(call: Call<Example>, response: Response<Example>) {
                Log.i("Retrofit onResponse", "Received a response!")
                val res = response.body()
                Log.i("Data", (data).toString())
                if (response.code() == 200 && res != null) {
                    data.value = res
                } else {
                    data.value = null
                }
            }
        })
        return data
    }
}