package com.example.bankaccount.infrastructure.`in`.model

import java.time.ZonedDateTime

data class Operation(
    val operation: OperationType,
    val dateTime: ZonedDateTime,
    val previousBalance: Double,
    val newBalance: Double,
    val amount: Double
) {
    companion object {
        fun fromDomain(domainOperation: com.example.bankaccount.domain.model.Operation): Operation {
            return Operation(
                OperationType.fromDomain(domainOperation.operation),
                domainOperation.dateTime,
                domainOperation.previousBalance,
                domainOperation.newBalance,
                domainOperation.amount
            )
        }
    }
}