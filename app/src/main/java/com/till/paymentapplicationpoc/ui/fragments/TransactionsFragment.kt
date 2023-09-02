package com.till.paymentapplicationpoc.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.till.paymentapplicationpoc.R
import com.till.paymentapplicationpoc.data.model.Payment
import com.till.paymentapplicationpoc.data.model.Transaction
import com.till.paymentapplicationpoc.data.repositories.TransactionService
import com.till.paymentapplicationpoc.databinding.FragmentTransactionsBinding
import com.till.paymentapplicationpoc.ui.adapters.TransactionsAdapter

class TransactionsFragment : Fragment(), TransactionsAdapter.TransactionClickListener {

    private var _binding: FragmentTransactionsBinding? = null
    private var transactionsService = TransactionService.instance
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TransactionsAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTransactionsBinding.inflate(inflater, container, false)

        val root: View = binding.root
        recyclerView = root.findViewById(R.id.transaction_list)
        adapter = TransactionsAdapter(transactionsService.getTransactions(), this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onTransactionClicked(transaction: Transaction) {
        if (transaction is Payment) {
            val direction = TransactionsFragmentDirections.actionToTransactionView(transaction.id)
            binding.root.findNavController().navigate(direction)
        }
    }

}

