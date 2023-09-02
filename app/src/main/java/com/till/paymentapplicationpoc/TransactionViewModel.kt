package com.till.paymentapplicationpoc

import androidx.lifecycle.ViewModel

class TransactionViewModel : ViewModel() {

    private var transactionService = TransactionService.instance

    fun makePayment(payment: Payment) {
        transactionService.pay(payment)
    }

    fun makeRefund(refund: Refund) {
        transactionService.refund(refund)
    }

    fun getTransaction(id: Long): Transaction? {
        return transactionService.getTransaction(id)
    }


}