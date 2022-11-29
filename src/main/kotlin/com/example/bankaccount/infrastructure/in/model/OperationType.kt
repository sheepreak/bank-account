package com.example.bankaccount.infrastructure.`in`.model

enum class OperationType {
    DEPOSIT, WITHDRAWAL;

    companion object {
        fun fromDomain(domainType: com.example.bankaccount.domain.model.OperationType) : OperationType {
            return valueOf(domainType.name)
        }
    }
}
