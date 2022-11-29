package com.example.bankaccount.domain.model

import com.example.bankaccount.domain.exception.NegativeBalanceException

data class Account(val id: Int, var balance: Double) {
    fun addBalance(value: Double) {
        balance += value
    }

    fun substractBalance(value: Double) {
        if (value > balance) {
            throw NegativeBalanceException("Your account balance can't be negative, try withdrawing a lower amount")
        }
        balance -= value
    }
}
