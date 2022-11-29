package com.example.bankaccount

import com.example.bankaccount.configuration.DatabaseClean
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(DatabaseClean::class)
open class BankApplicationIntegrationTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    var objectMapper: ObjectMapper = ObjectMapper()

    @Nested
    @DisplayName("When depositing money on your account")
    inner class DepositIntegrationTest {

        @Test
        @Sql(scripts = ["dataset/emptybankaccount.sql"])
        @DisplayName("Then it should deposit the correct amount and return the new balance")
        fun should_make_deposit_on_existing_account() {
            mockMvc.post("/accounts/0/deposit") {
                contentType = MediaType.APPLICATION_JSON
                content = "{\"value\": 28.0}"
                accept = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isOk() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    json("{\"balance\": 28.0}")
                }
            }
        }

        @Test
        @DisplayName("Then it should not find the account and throw an error")
        fun should_throw_not_found_if_account_does_not_exist() {
            val errorMessage = mockMvc.post("/accounts/0/deposit") {
                contentType = MediaType.APPLICATION_JSON
                content = "{\"value\": 28.0}"
                accept = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isNotFound() }
            }.andReturn().resolvedException?.message

            Assertions.assertNotNull(errorMessage)
            if (errorMessage != null) {
                Assertions.assertTrue(errorMessage.contains("Account 0 does not exist"))
            }
        }
    }

    @Nested
    @DisplayName("When withdrawing money from your account")
    inner class WithdrawalIntegrationTest {
        @Test
        @Sql(scripts = ["dataset/fullbankaccount.sql"])
        @DisplayName("Then it should apply the withdrawal to your account and return the new balance")
        fun should_withdraw_value_and_return_new_balance() {
            mockMvc.post("/accounts/0/withdraw") {
                contentType = MediaType.APPLICATION_JSON
                content = "{\"value\": 30.0}"
                accept = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isOk() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    json("{\"balance\": 20.0}")
                }
            }
        }

        @Test
        @DisplayName("Then it should not find the account and throw an error")
        fun should_throw_not_found_if_account_does_not_exist() {
            val errorMessage = mockMvc.post("/accounts/0/withdraw") {
                contentType = MediaType.APPLICATION_JSON
                content = "{\"value\": 28.0}"
                accept = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isNotFound() }
            }.andReturn().resolvedException?.message

            Assertions.assertNotNull(errorMessage)
            if (errorMessage != null) {
                Assertions.assertTrue(errorMessage.contains("Account 0 does not exist"))
            }
        }

        @Test
        @Sql(scripts = ["dataset/fullbankaccount.sql"])
        @DisplayName("Then it should throw an error when the amount to withdraw is higher than the current balance")
        fun should_throw_bad_request_if_amount_higher_than_balance() {
            val errorMessage = mockMvc.post("/accounts/0/withdraw") {
                contentType = MediaType.APPLICATION_JSON
                content = "{\"value\": 100.0}"
                accept = MediaType.APPLICATION_JSON
            }.andExpect {
                status { isBadRequest() }
            }.andReturn().resolvedException?.message

            Assertions.assertNotNull(errorMessage)
            if (errorMessage != null) {
                Assertions.assertTrue(errorMessage.contains("Your account balance can't be negative, try withdrawing a lower amount"))
            }
        }
    }

    @Nested
    @DisplayName("When I want to check my account history")
    inner class HistoryIntegrationTest {
        @Test
        @Sql(scripts = ["dataset/multiplebankaccounts.sql"])
        @DisplayName("Then I should get every operation made on my account")
        fun should_return_all_operations() {
            insertOperations()

            mockMvc.get("/accounts/0/history")
                .andExpect {
                    status { isOk() }
                    jsonPath("$.operations.length()") { value(4) }
                }
        }

        private fun insertOperations() {
            mockMvc.post("/accounts/0/deposit") {
                contentType = MediaType.APPLICATION_JSON
                content = "{\"value\": 28.0}"
                accept = MediaType.APPLICATION_JSON
            }
            mockMvc.post("/accounts/0/withdraw") {
                contentType = MediaType.APPLICATION_JSON
                content = "{\"value\": 35.0}"
                accept = MediaType.APPLICATION_JSON
            }
            mockMvc.post("/accounts/0/deposit") {
                contentType = MediaType.APPLICATION_JSON
                content = "{\"value\": 160.0}"
                accept = MediaType.APPLICATION_JSON
            }
            mockMvc.post("/accounts/0/withdraw") {
                contentType = MediaType.APPLICATION_JSON
                content = "{\"value\": 150.0}"
                accept = MediaType.APPLICATION_JSON
            }
        }
    }
}
