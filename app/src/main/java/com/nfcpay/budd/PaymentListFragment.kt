package com.nfcpay.budd

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nfcpay.budd.databinding.PaymentlistBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PaymentList : Fragment() {
    private lateinit var binding: PaymentlistBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var transaction : BasketTransactions?=null
    var transactionListLiveData : LiveData<BasketTransaction>?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.paymentlist, container, false)
        // Inflate the layout for this fragment

        transaction = BasketTransactions()
        transactionListLiveData = MutableLiveData()
        Log.i("PaymentList onCreateView", "Fetching transactions")
        transactionListLiveData = transaction?.fetchTransactions()

        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("PaymentList onViewCreated", "Launching transactionListLiveData observer")
        transactionListLiveData?.observe(this, Observer {
            if(it!=null){
                // Update the form values with Basket information
                val transactString = "Transaction id. " + it.transactionId
                val VATString = it.VAT?.times(100).toString() + "%"
                binding.transactionId.setText(transactString)
                binding.summa.setText((it.totalSum).toString())
                binding.VAT.setText(VATString)
                // Update the Recycleview
                viewManager = LinearLayoutManager(requireActivity())
                try {
                    viewAdapter = PayListAdapter(it)
                    // Assign adapter and manager to Recyclerview
                    binding.recylerview.apply {
                        this.layoutManager = viewManager
                        this.adapter = viewAdapter
                        setHasFixedSize(true)
                    }
                }
                catch  (e : Exception) {
                    Log.e("QkPayAdapter fail", "Binding adapter failed: ${e.toString()}")
                }
            }
            else {
             Log.i("Error", "There's an error!")
            }
        })

        fun fetchTransactions(){
            transactionListLiveData = transaction?.fetchTransactions()
        }

    }
}