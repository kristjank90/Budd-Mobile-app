package com.nfcpay.budd

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import java.util.*

class PayListAdapter(private val TransactionList : BasketTransaction) : RecyclerView.Adapter<PayListAdapter.BasketHolder>()  {
    // Inflate the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PayListAdapter.BasketHolder {
        val inflatedView = parent.inflate(R.layout.recycleview_paylist, false)
        return BasketHolder(inflatedView)
    }
    // Get the list of items to show
    override fun getItemCount(): Int {
        return TransactionList.itemList!!.size
    }
    // Bind the data to ViewHolder
    override fun onBindViewHolder(holder: PayListAdapter.BasketHolder, position: Int) {
        holder.itemNameTV.text = TransactionList.itemList!!.get(position).name
        holder.itemDescTV.text = TransactionList.itemList!!.get(position).description
        holder.itemCostTV.text = TransactionList.itemList!!.get(position).price.toString()
        holder.itemPcsTV.text = TransactionList.itemList!!.get(position).amount.toString()
        // holder.itemTotalTV.text = TransactionList.getJSONArray("itemList")!!.getJSONObject(position).getString("totalSum")
        holder.itemCurrencyOneTV.text =  Currency.getInstance(TransactionList.itemList!!.get(position).currency).getSymbol()
        holder.itemCurrencySumTV.text =  Currency.getInstance(TransactionList.itemList!!.get(position).currency).getSymbol()
    }

    // Let's add the viewHolder nested class to store all the views references
    //1
    class BasketHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        //2
        private var view: View = v
        var itemNameTV : TextView
        var itemPcsTV : TextView
        var itemDescTV : TextView
        var itemCostTV : TextView
        var itemTotalTV : TextView
        var itemCurrencyOneTV : TextView
        var itemCurrencySumTV : TextView

        //3
        init {
            v.setOnClickListener(this)
            itemNameTV = v.findViewById(R.id.itemName)
            itemPcsTV = v.findViewById(R.id.itemPcs)
            itemCostTV = v.findViewById(R.id.itemPrice)
            itemTotalTV = v.findViewById(R.id.itemTotalPrice)
            itemDescTV = v.findViewById(R.id.itemDescription)
            itemCurrencyOneTV = v.findViewById(R.id.itemCurrency)
            itemCurrencySumTV = v.findViewById(R.id.itemSumCurrency)
        }

        //4
        override fun onClick(v: View) {
            Snackbar.make(v,"Extra spicy details @ here..", 1000).show()
        }
    }
    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }
}