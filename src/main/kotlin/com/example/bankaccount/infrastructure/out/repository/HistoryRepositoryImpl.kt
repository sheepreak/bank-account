package com.example.bankaccount.infrastructure.out.repository

import com.example.bankaccount.domain.model.Operation
import com.example.bankaccount.domain.repository.HistoryRepository
import com.example.bankaccount.infrastructure.out.model.OperationEntity

class HistoryRepositoryImpl(private val operationJpaRepository: OperationJpaRepository) : HistoryRepository {
    override fun getHistory(accountId: Int): List<Operation> {
        return operationJpaRepository.findAllByAccountId(accountId).map { it.toDomain() }
    }

    override fun insertOperation(accountId: Int, operation: Operation) {
        operationJpaRepository.save<OperationEntity?>(OperationEntity.fromDomain(accountId, operation))
    }
}
