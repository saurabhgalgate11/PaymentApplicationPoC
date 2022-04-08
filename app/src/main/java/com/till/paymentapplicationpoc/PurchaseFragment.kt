package com.till.paymentapplicationpoc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.till.paymentapplicationpoc.databinding.FragmentPurchaseBinding

class PurchaseFragment : Fragment() {

    private var _binding: FragmentPurchaseBinding? = null
    private lateinit var paymentEditText: EditText
    private lateinit var paymentButton: Button
    private val transactionService = TransactionService.instance

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPurchaseBinding.inflate(inflater, container, false)
        val binding = _binding!!
        paymentEditText = binding.paymentAmount
        binding.paymentButton.setOnClickListener {
            transactionService.pay(Payment(System.currentTimeMillis(), paymentEditText.text.toString().toFloat(), emptyList()))
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}