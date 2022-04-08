package com.till.paymentapplicationpoc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.till.paymentapplicationpoc.databinding.FragmentTransactionsBinding

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
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

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

class TransactionsAdapter(private val dataSet: List<Transaction>, private val onClickListener: TransactionClickListener) :
    RecyclerView.Adapter<TransactionsAdapter.ViewHolder>() {

    interface TransactionClickListener {
        fun onTransactionClicked(transaction: Transaction)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val paymentAmount: TextView
        val paymentType: TextView

        init {
            paymentAmount = view.findViewById(R.id.payment_amount)
            paymentType = view.findViewById(R.id.payment_type)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.transaction_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.setOnClickListener { onClickListener.onTransactionClicked(dataSet[position]) }
        viewHolder.paymentAmount.text = dataSet[position].amount.toString()
        viewHolder.paymentType.text = if (dataSet[position] is Payment) "Payment" else "Refund"
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}