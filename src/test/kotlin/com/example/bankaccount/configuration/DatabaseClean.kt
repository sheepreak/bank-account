package com.example.bankaccount.configuration

import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.sql.DataSource

class DatabaseClean : AfterEachCallback {
    override fun afterEach(context: ExtensionContext) {
        val ds = SpringExtension.getApplicationContext(context).getBean(DataSource::class.java)

        ds.connection.use { connection ->
            connection.prepareStatement("DELETE FROM account").execute()
            connection.prepareStatement("DELETE FROM operation").execute()
        }
    }
}