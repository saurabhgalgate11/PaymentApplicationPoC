package com.till.paymentapplicationpoc.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.till.paymentapplicationpoc.data.model.Payment
import com.till.paymentapplicationpoc.data.model.Refund
import com.till.paymentapplicationpoc.data.model.Transaction
import com.till.paymentapplicationpoc.data.repositories.TransactionService

class TransactionViewModel : ViewModel() {

    private var transactionService = TransactionService.instance

    fun makePayment(enteredAmount: String) {
        val payment = Payment(
            System.currentTimeMillis(),
            enteredAmount.toFloat(),
            emptyList()
        )
        transactionService.pay(payment)
    }

    fun makeRefund(amount: Float, txId: Long) {
        val refund = Refund(
            id = System.currentTimeMillis(),
            amount = amount,
            referenceId = txId
        )
        transactionService.refund(refund)
    }

    fun getTransaction(id: Long): Transaction? {
        return transactionService.getTransaction(id)
    }

    fun checkRefundEligibility(amount: Float): Boolean {
        return transactionService.checkTransactionEligibility(amount)
    }

}