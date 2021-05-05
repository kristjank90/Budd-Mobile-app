package com.nfcpay.budd

import android.nfc.NfcAdapter
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.google.android.material.snackbar.Snackbar
import com.nfcpay.budd.databinding.ActivityNfcPaymentBinding
import java.util.*
import kotlin.concurrent.schedule

class NfcPaymentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNfcPaymentBinding
    private val viewModel: payAppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    if (savedInstanceState == null) { // If the instance has not been active
    binding = DataBindingUtil.setContentView(this, R.layout.activity_nfc_payment)

    var transaction : String? // Returns the type of Transaction for Routing between Fragments

    if (intent != null) {
        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent?.action) { // NFC data processing
            // Extract transaction details to transactionObject
            transaction =  viewModel.processIntentNFC(intent)
            // Include Transaction data in Bundle to forward to Payment or Transfer fragment
        if (transaction == "Payment") {
            supportFragmentManager.beginTransaction().add(R.id.fragment_container_view, PaymentList()).commit()
        }
        else if (transaction == "Transfer")
        {
          // (TODO)  supportFragmentManager.beginTransaction().setReorderingAllowed(true).add(R.id.fragment_container_view, QkTransferFragment()).commit()
        }
        else {
            Snackbar.make(binding.root, "Failure in recognizing NFC signal (URI)! Returning to Login + ${transaction}", 2000).show()
            }
        }
    }
    }
    }
}