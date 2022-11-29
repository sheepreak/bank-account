package com.example.bankaccount.infrastructure.out.repository

import com.example.bankaccount.domain.model.Account
import com.example.bankaccount.domain.repository.AccountRepository
import org.springframework.transaction.annotation.Transactional

open class AccountRepositoryImpl(private val accountJpaRepository: AccountJpaRepository) : AccountRepository {
    override fun findAccountById(accountId: Int): Account {
        return accountJpaRepository.findById(accountId).orElseThrow().toDomain()
    }

    @Transactional
    override fun updateAccount(account: Account): Account {
        val oldEntity = accountJpaRepository.findById(account.id).orElseThrow()
        oldEntity.updateBalance(account)
        return accountJpaRepository.save(oldEntity).toDomain()
    }
}
