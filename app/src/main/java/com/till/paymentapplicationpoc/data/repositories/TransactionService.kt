package com.till.paymentapplicationpoc.data.repositories

import com.till.paymentapplicationpoc.data.model.Payment
import com.till.paymentapplicationpoc.data.model.Refund
import com.till.paymentapplicationpoc.data.model.Transaction

class TransactionService {

    private val transactions = mutableMapOf<Long, Transaction>()

    companion object {
        val instance = TransactionService()
    }

    /** processing payment request to network call
     * @param payment data to process payment
     * */
    fun pay(payment: Payment) {
        transactions[payment.id] = payment
    }

    fun getTransactions(): List<Transaction> = transactions.values.toList()

    fun getTransaction(id: Long): Transaction? = transactions[id]

    /** processing refund request to network call
     * @param refund data to process refund payment
     * */
    fun refund(refund: Refund) {
        transactions[refund.id] = refund
    }

    /** provides the eligibility considering total amount of transaction processed
    * @return true if amount eligible to process
    * @param amount amount value to compare
    * */
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
