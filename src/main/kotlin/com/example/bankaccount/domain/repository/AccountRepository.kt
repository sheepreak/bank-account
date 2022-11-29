package com.example.bankaccount.domain.repository

import com.example.bankaccount.domain.model.Account

interface AccountRepository {
    fun findAccountById(accountId: Int) : Account
    fun updateAccount(account: Account) : Account
}
