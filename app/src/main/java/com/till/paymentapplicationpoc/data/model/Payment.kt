package com.till.paymentapplicationpoc.data.model

/* Payment named data class refers to make payment transaction */
data class Payment(
    override val id: Long,
    override val amount: Float,
    val refunds: List<Refund>
) : Transaction(id, amount)