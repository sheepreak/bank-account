package com.example.bankaccount.application.configuration

import com.example.bankaccount.domain.repository.AccountRepository
import com.example.bankaccount.domain.repository.HistoryRepository
import com.example.bankaccount.domain.service.AccountDomainService
import com.example.bankaccount.domain.service.AccountDomainServiceImpl
import com.example.bankaccount.infrastructure.out.repository.AccountJpaRepository
import com.example.bankaccount.infrastructure.out.repository.AccountRepositoryImpl
import com.example.bankaccount.infrastructure.out.repository.OperationJpaRepository
import com.example.bankaccount.infrastructure.out.repository.HistoryRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class BankAccountConfiguration {

    @Bean
    open fun accountRepository(accountJpaRepository: AccountJpaRepository): AccountRepository {
        return AccountRepositoryImpl(accountJpaRepository)
    }

    @Bean
    open fun historyRepository(operationJpaRepository: OperationJpaRepository): HistoryRepository {
        return HistoryRepositoryImpl(operationJpaRepository)
    }

    @Bean
    open fun accountDomainService(
        accountRepository: AccountRepository,
        historyRepository: HistoryRepository
    ): AccountDomainService {
        return AccountDomainServiceImpl(accountRepository, historyRepository)
    }
}