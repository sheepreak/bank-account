package com.example.bankaccount.infrastructure.out.repository

import com.example.bankaccount.infrastructure.out.model.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountJpaRepository : JpaRepository<AccountEntity, Int> {
}