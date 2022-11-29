package com.example.bankaccount.infrastructure.out.model

import com.example.bankaccount.domain.model.Account
import jakarta.persistence.*

@Entity
@Table(name = "account")
class AccountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(nullable = false, updatable = true)
    var balance: Double
) {
    fun toDomain(): Account {
        return Account(id, balance)
    }

    fun updateBalance(account: Account) {
        balance = account.balance
    }
}