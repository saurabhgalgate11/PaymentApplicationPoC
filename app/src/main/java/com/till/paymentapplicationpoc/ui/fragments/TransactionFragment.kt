package com.till.paymentapplicationpoc.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.till.paymentapplicationpoc.R
import com.till.paymentapplicationpoc.databinding.FragmentTransactionBinding
import com.till.paymentapplicationpoc.ui.viewmodels.TransactionViewModel

class TransactionFragment : Fragment() {

    private lateinit var viewModel: TransactionViewModel
    private var _binding: FragmentTransactionBinding? = null
    private lateinit var refundEditText: EditText
    private val args: TransactionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[TransactionViewModel::class.java]
        _binding = FragmentTransactionBinding.inflate(inflater, container, false)
        val binding = _binding!!
        val root: View = binding.root

        with(viewModel.getTransaction(args.txId)!!) {
            root.findViewById<TextView>(R.id.amount).text = amount.toString()
            root.findViewById<TextView>(R.id.tx_id).text = id.toString()
        }

        refundEditText = root.findViewById(R.id.refund_amount)
        root.findViewById<Button>(R.id.refund_button).setOnClickListener {
            val refundAmount = refundEditText.text.toString()
            processRefundTransaction(refundAmount.toFloat())
        }

        return root
    }

    private fun processRefundTransaction(refundAmount: Float) {
        val message = if (refundAmount > 0) {
            if (viewModel.checkRefundEligibility(refundAmount)) {
                viewModel.makeRefund(refundAmount, args.txId)
                refundEditText.text.clear()
                R.string.refund_transaction_message
            } else {
                R.string.refund_transaction_error_message
            }
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