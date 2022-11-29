package com.example.bankaccount.infrastructure.out.model

import com.example.bankaccount.domain.model.OperationType

enum class OperationType {
    DEPOSIT, WITHDRAWAL;

    fun toDomain(): OperationType {
        return OperationType.valueOf(this.name)
    }

    companion object {
        fun fromDomain(domainType: OperationType): com.example.bankaccount.infrastructure.out.model.OperationType {
            return valueOf(domainType.name)
        }
    }
}