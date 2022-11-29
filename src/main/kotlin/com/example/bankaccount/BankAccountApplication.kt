package com.example.bankaccount

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
open class BankAccountApplication

fun main(args: Array<String>) {
    runApplication<BankAccountApplication>(*args)
}
