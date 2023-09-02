package com.till.paymentapplicationpoc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.till.paymentapplicationpoc.databinding.FragmentPurchaseBinding

class PurchaseFragment : Fragment() {

    private var _binding: FragmentPurchaseBinding? = null
    private lateinit var paymentEditText: EditText
    private lateinit var viewModel: TransactionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[TransactionViewModel::class.java]

        _binding = FragmentPurchaseBinding.inflate(inflater, container, false)
        val binding = _binding!!
        paymentEditText = binding.paymentAmount

        binding.paymentButton.setOnClickListener {
            processAccountTransaction(paymentEditText.text.toString())
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun processAccountTransaction(enteredAmount: String) {
        val message = if (enteredAmount.isNotBlank()) {
            val payment = Payment(
                System.currentTimeMillis(),
                enteredAmount.toFloat(),
                emptyList()
            )
            viewModel.makePayment(payment)
            paymentEditText.text.clear()
            R.string.transaction_success_message
        } else {
            R.string.enter_transaction_amount
        }
        Snackbar.make(
            paymentEditText.rootView,
            resources.getString(message),
            Snackbar.LENGTH_SHORT
        ).show()
    }

}