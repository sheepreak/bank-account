package com.example.bankaccount.infrastructure.out.repository

import com.example.bankaccount.infrastructure.out.model.OperationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OperationJpaRepository : JpaRepository<OperationEntity, Int> {
    fun findAllByAccountId(accountId: Int) : List<OperationEntity>
}
