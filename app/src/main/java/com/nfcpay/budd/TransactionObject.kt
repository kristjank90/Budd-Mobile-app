package com.nfcpay.budd

object Transaction {

    data class TransactionDetails(var ID : String?, var Owner : String?, var Description : String?,
                                  var Type : String?, var Meta : String? )

    private lateinit var _TransactionDetails : TransactionDetails

    fun setTransaction(ID : String?, Owner : String?, Description : String?,
                       Type : String?, Meta : String? ) {
       _TransactionDetails =  TransactionDetails(ID, Owner, Description, Type, Meta)
    }

    fun getTransaction() : TransactionDetails {
        return _TransactionDetails
    }
}