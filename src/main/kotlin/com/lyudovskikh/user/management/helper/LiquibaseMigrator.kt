package com.lyudovskikh.user.management.helper

import com.lyudovskikh.user.management.configuration.UserManagementConfiguration
import io.dropwizard.setup.Environment
import liquibase.Liquibase
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor

object LiquibaseMigrator {

    private const val NO_CONTEXT_MODE = ""

    fun migrate(configuration: UserManagementConfiguration, environment: Environment) {
        val managedDataSource = configuration.database.build(environment.metrics(), "migrations")
        val connection = managedDataSource.connection
        connection.use {
            val migrator = Liquibase("migrations.xml", ClassLoaderResourceAccessor(), JdbcConnection(connection))
            migrator.update(NO_CONTEXT_MODE)
        }
    }

}