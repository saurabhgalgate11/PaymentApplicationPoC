package com.till.paymentapplicationpoc.data.repositories

import com.till.paymentapplicationpoc.data.model.Payment
import com.till.paymentapplicationpoc.data.model.Refund
import com.till.paymentapplicationpoc.data.model.Transaction

class TransactionService {

    private val transactions = mutableMapOf<Long, Transaction>()

    companion object {
        val instance = TransactionService()
    }

    fun pay(payment: Payment) {
        transactions[payment.id] = payment
    }

    fun getTransactions(): List<Transaction> = transactions.values.toList()

    fun getTransaction(id: Long): Transaction? = transactions[id]

    fun refund(refund: Refund) {
        transactions[refund.id] = refund
    }

    fun checkTransactionEligibility(amount: Float): Boolean {
        var sumRefund = amount
        var sumPayments = 0F
        for (transaction in transactions.values) {
            when (transaction) {
                is Payment -> sumPayments += transaction.amount
                is Refund -> sumRefund += transaction.amount
            }
        }
        return sumRefund <= sumPayments
    }

}
