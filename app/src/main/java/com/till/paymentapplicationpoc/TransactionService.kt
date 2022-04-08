package com.till.paymentapplicationpoc

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
}

open class Transaction(
    open val id: Long,
    open val amount: Float
)

class Payment(
    override val id: Long,
    override val amount: Float,
    val refunds: List<Refund>
): Transaction(id, amount)

class Refund(
    override val id: Long,
    override val amount: Float,
    val referenceId: Long
): Transaction(id, amount)
