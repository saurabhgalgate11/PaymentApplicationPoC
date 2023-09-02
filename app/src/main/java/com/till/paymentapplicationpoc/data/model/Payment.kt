package com.till.paymentapplicationpoc.data.model

data class Payment(
    override val id: Long,
    override val amount: Float,
    val refunds: List<Refund>
) : Transaction(id, amount)