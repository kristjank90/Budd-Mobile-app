package com.nfcpay.budd

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PaymentList : Fragment() {
    private var BasketTransaction : BasketTransactions?=null
    var transactionListLiveData : LiveData<Example>?=null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        BasketTransaction = BasketTransactions()
        transactionListLiveData = MutableLiveData()
        Log.i("PaymentList onCreateView", "Fetching transactions")
        transactionListLiveData = BasketTransaction?.fetchTransactions()

        return inflater.inflate(R.layout.paymentlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("PaymentList onViewCreated", "Launching transactionListLiveData observer")
        transactionListLiveData?.observe(this, Observer {
            if(it!=null){
            view.findViewById<TextView>(R.id.transactionId).setText((it.transactionId).toString())
            view.findViewById<TextView>(R.id.summa).setText((it.totalSum).toString())
            view.findViewById<TextView>(R.id.VAT).setText((it.VAT).toString())
            }
            else {
             Log.i("Error", "There's an error!")
            }
        })

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            activity?.supportFragmentManager?.commit {
                    setReorderingAllowed(true)
                    replace<SecondFragment>(R.id.fragment_container_view)
                }
        }
        fun fetchTransactions(){
            transactionListLiveData = BasketTransaction?.fetchTransactions()
        }
    }
}