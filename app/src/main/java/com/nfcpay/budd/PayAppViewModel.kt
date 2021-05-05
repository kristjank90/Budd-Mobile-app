package com.nfcpay.budd

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel

class payAppViewModel : ViewModel()  {
    // Define a Bunch of LiveData objects to be returned
    data class TransactionDetails (var ID : String?, var Owner : String?, var Description : String?,
                                   var Type : String?, var Meta : String? )

    public fun processIntentNFC(intent: Intent?) : String {
        val data: Uri? = intent?.data
        val myString : String = data.toString()

        val transaction_nfc = TransactionDetails(null, null, null, null, null)
        val lstValues: List<String> = myString.split(":").map { it -> it.trim() }

        lstValues.forEach { it ->
            Log.i("Values", "value=$it")
            when
            {
                it.contains("nfcid=") -> transaction_nfc.ID = it.substringAfter("'").substringBefore("'")
                it.contains("description=") -> transaction_nfc.Description= it.substringAfter("'").substringBefore("'")
                it.contains("meta=") -> transaction_nfc.Meta= it.substringAfter("'").substringBefore("'")
                it.contains("p2ym/") -> transaction_nfc.Type = "Payment"
                else -> null
            }
        }
        Log.i("PayApp type", "${transaction_nfc.Type}")
        Log.i("Transaction details", "ID: ${transaction_nfc.ID}, Description: ${transaction_nfc.Description}")
        // Return type of transaction for Routing purposes!
        if (transaction_nfc.Type == "Payment" && !transaction_nfc.ID.isNullOrEmpty()) {
            Transaction.setTransaction(transaction_nfc.ID,"",transaction_nfc.Description, transaction_nfc.Type,transaction_nfc.Meta)
            return "Payment"
        }
        else if (transaction_nfc.Type != "Payment" && !transaction_nfc.ID.isNullOrEmpty()) {
            transaction_nfc.Type = "Transfer"
            Transaction.setTransaction(transaction_nfc.ID,"",transaction_nfc.Description, transaction_nfc.Type,transaction_nfc.Meta)
            return "Transfer"
        }
        else { return "Error: unknown type" }
    }
}
