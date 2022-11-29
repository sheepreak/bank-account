package com.example.bankaccount.domain.repository

import com.example.bankaccount.domain.model.Operation

interface HistoryRepository {
    fun getHistory(accountId: Int) : List<Operation>
    fun insertOperation(accountId: Int, operation: Operation)
}
