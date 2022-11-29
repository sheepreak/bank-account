package com.example.bankaccount.domain.model

import java.time.ZonedDateTime

data class Operation(
    val operation: OperationType,
    val dateTime: ZonedDateTime,
    val previousBalance: Double,
    val newBalance: Double,
    val amount: Double
)