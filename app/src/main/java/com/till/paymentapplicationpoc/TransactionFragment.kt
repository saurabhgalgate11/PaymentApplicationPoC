package com.till.paymentapplicationpoc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.till.paymentapplicationpoc.databinding.FragmentTransactionBinding

class TransactionFragment : Fragment() {
    private var transactionService = TransactionService.instance
    private var _binding: FragmentTransactionBinding? = null
    private lateinit var refundEditText: EditText

    private val args: TransactionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTransactionBinding.inflate(inflater, container, false)
        val binding = _binding!!
        val root: View = binding.root

        with(transactionService.getTransaction(args.txId)!!) {
            root.findViewById<TextView>(R.id.amount).text = amount.toString()
            root.findViewById<TextView>(R.id.tx_id).text = id.toString()
        }

        refundEditText = root.findViewById(R.id.refund_amount)
        root.findViewById<Button>(R.id.refund_button).setOnClickListener {
            val refundAmount = refundEditText.text.toString()
            processRefundTransaction(refundAmount)
        }

        return root
    }

    private fun processRefundTransaction(refundAmount: String) {
        val message = if (refundAmount.isNotEmpty()) {
            val refund = Refund(
                id = System.currentTimeMillis(),
                amount = refundAmount.toFloat(),
                referenceId = args.txId
            )
            transactionService.refund(refund)
            refundEditText.text.clear()
            R.string.refund_transaction_message
        } else {
            R.string.enter_transaction_amount
        }
        Snackbar.make(
            refundEditText.rootView,
            resources.getString(message),
            Snackbar.LENGTH_SHORT
        ).show()
    }

}