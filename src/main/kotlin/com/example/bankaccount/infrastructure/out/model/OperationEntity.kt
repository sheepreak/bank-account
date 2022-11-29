package com.example.bankaccount.infrastructure.out.model

import com.example.bankaccount.domain.model.Operation
import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
@Table(name = "operation")
class OperationEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var operation: OperationType,

    @Column(nullable = false)
    var accountId: Int,

    @Column(nullable = false)
    var dateTime: ZonedDateTime,

    @Column(nullable = false)
    var previousBalance: Double,

    @Column(nullable = false)
    var newBalance: Double,

    @Column(nullable = false)
    var amount: Double
) {

    fun toDomain(): Operation {
        return Operation(operation.toDomain(), dateTime, previousBalance, newBalance, amount)
    }

    companion object {
        fun fromDomain(accountId: Int, domainOperation: Operation): OperationEntity {
            return OperationEntity(
                null,
                OperationType.fromDomain(domainOperation.operation),
                accountId,
                domainOperation.dateTime,
                domainOperation.previousBalance,
                domainOperation.newBalance,
                domainOperation.amount
            )
        }
    }
}