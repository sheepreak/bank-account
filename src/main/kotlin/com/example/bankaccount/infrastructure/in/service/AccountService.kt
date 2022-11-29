package com.example.bankaccount.infrastructure.`in`.service

import com.example.bankaccount.domain.service.AccountDomainService
import com.example.bankaccount.infrastructure.`in`.model.Operation
import org.springframework.stereotype.Service

@Service
class AccountService(private val accountDomainService: AccountDomainService) {
    fun deposit(accountId: Int, value: Double): Double {
        return accountDomainService.deposit(accountId, value)
    }

    fun withdraw(accountId: Int, value: Double): Double {
        return accountDomainService.withdraw(accountId, value)
    }

    fun getHistory(accountId: Int): List<Operation> {
        return accountDomainService.getHistory(accountId).map { Operation.fromDomain(it) }
    }

}
