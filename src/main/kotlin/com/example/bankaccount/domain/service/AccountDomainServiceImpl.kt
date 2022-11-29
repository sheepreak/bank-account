package com.example.bankaccount.domain.service

import com.example.bankaccount.domain.model.Operation
import com.example.bankaccount.domain.model.OperationType
import com.example.bankaccount.domain.repository.AccountRepository
import com.example.bankaccount.domain.repository.HistoryRepository
import java.time.ZonedDateTime

class AccountDomainServiceImpl(
    private val accountRepository: AccountRepository,
    private val historyRepository: HistoryRepository
) : AccountDomainService {
    override fun deposit(accountId: Int, value: Double): Double {
        val account = accountRepository.findAccountById(accountId)
        account.addBalance(value)
        val newAccount = accountRepository.updateAccount(account)
        historyRepository.insertOperation(
            accountId,
            Operation(OperationType.DEPOSIT, ZonedDateTime.now(), account.balance, newAccount.balance, value)
        )
        return newAccount.balance
    }

    override fun withdraw(accountId: Int, value: Double): Double {
        val account = accountRepository.findAccountById(accountId)
        account.substractBalance(value)
        val newAccount = accountRepository.updateAccount(account)
        historyRepository.insertOperation(
            accountId,
            Operation(OperationType.WITHDRAWAL, ZonedDateTime.now(), account.balance, newAccount.balance, value)
        )
        return newAccount.balance
    }

    override fun getHistory(accountId: Int): List<Operation> {
        return historyRepository.getHistory(accountId)
    }
}