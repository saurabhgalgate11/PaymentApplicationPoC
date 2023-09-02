package com.till.paymentapplicationpoc.data.model

/* named Refund data class refers to Refund transaction amount */
data class Refund(
    override val id: Long,
    override val amount: Float,
    val referenceId: Long
) : Transaction(id, amount)