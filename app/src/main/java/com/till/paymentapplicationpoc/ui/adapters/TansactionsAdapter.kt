package com.till.paymentapplicationpoc.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.till.paymentapplicationpoc.R
import com.till.paymentapplicationpoc.data.model.Payment
import com.till.paymentapplicationpoc.data.model.Transaction

class TransactionsAdapter(
    private val dataSet: List<Transaction>,
    private val onClickListener: TransactionClickListener
) : RecyclerView.Adapter<TransactionsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.transaction_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.setOnClickListener { onClickListener.onTransactionClicked(dataSet[position]) }
        viewHolder.paymentAmount.text = dataSet[position].amount.toString()
        viewHolder.paymentType.text = if (dataSet[position] is Payment) PAYMENT else REFUND
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

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

    companion object {

        const val PAYMENT = "Payment"
        const val REFUND = "Refund"

    }

}