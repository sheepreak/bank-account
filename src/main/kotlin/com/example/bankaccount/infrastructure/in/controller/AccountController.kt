package com.example.bankaccount.infrastructure.`in`.controller

import com.example.bankaccount.domain.exception.NegativeBalanceException
import com.example.bankaccount.infrastructure.`in`.model.*
import com.example.bankaccount.infrastructure.`in`.service.AccountService
import jdk.jfr.ContentType
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.NoSuchElementException

@Controller
class AccountController(private val accountService: AccountService) {
    @PostMapping("/accounts/{accountId}/deposit", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun postDeposit(@PathVariable accountId: Int, @RequestBody depositRequest: DepositRequest) : DepositResponse {
        try {
            return DepositResponse(accountService.deposit(accountId, depositRequest.value))
        } catch (e: NoSuchElementException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Account %s does not exist", accountId))
        }
    }

    @PostMapping("/accounts/{accountId}/withdraw", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun postWithdrawal(@PathVariable accountId: Int, @RequestBody withdrawalRequest: WithdrawalRequest) : WithdrawalResponse {
        try {
            return WithdrawalResponse(accountService.withdraw(accountId, withdrawalRequest.value))
        } catch (e: NoSuchElementException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Account %s does not exist", accountId))
        } catch (e: NegativeBalanceException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @GetMapping("/accounts/{accountId}/history", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun getHistory(@PathVariable accountId: Int) : HistoryResponse {
        try {
            return HistoryResponse(accountService.getHistory(accountId))
        } catch (e: NoSuchElementException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Account %s does not exist", accountId))
        }
    }
}