package com.example.bankaccount.domain.service

import com.example.bankaccount.domain.model.Operation


interface AccountDomainService {
    fun deposit(accountId: Int, value: Double): Double
    fun withdraw(accountId: Int, value: Double): Double
    fun getHistory(accountId: Int): List<Operation>
}