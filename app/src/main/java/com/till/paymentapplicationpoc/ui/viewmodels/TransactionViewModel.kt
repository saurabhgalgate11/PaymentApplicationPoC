package com.till.paymentapplicationpoc.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.till.paymentapplicationpoc.data.model.Payment
import com.till.paymentapplicationpoc.data.model.Refund
import com.till.paymentapplicationpoc.data.model.Transaction
import com.till.paymentapplicationpoc.data.repositories.TransactionService

/**
 * ViewModel class responsible for handling transactions and providing data to the UI.
 *
 * @property transactionService to make transaction interactions
 */
class TransactionViewModel : ViewModel() {

    private var transactionService = TransactionService.instance

    /**
     * Initialize payment transaction and saves data
     * @param enteredAmount processed payment amount.
     */
    fun makePayment(enteredAmount: String) {
        val payment = Payment(
            System.currentTimeMillis(),
            enteredAmount.toFloat(),
            emptyList()
        )
        transactionService.pay(payment)
    }

    /**
     * Initialize payment refund processing
     * @param amount to make refunc.
     * @param txId to compare reference
     */
    fun makeRefund(amount: Float, txId: Long) {
        val refund = Refund(
            id = System.currentTimeMillis(),
            amount = amount,
            referenceId = txId
        )
        transactionService.refund(refund)
    }

    /**
     * Fetch trancation data
     * @param id to get transaction details
     */
    fun getTransaction(id: Long): Transaction? {
        return transactionService.getTransaction(id)
    }

    /**
     * Check transaction data
     * @param amount to check eligibility
     */
    fun checkRefundEligibility(amount: Float): Boolean {
        return transactionService.checkTransactionEligibility(amount)
    }

}