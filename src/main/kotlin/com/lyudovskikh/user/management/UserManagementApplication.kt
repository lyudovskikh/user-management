package com.lyudovskikh.user.management

import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.lyudovskikh.user.management.configuration.DependencyInjectionConfiguration
import com.lyudovskikh.user.management.configuration.UserManagementConfiguration
import com.lyudovskikh.user.management.controller.UserInfoController
import com.lyudovskikh.user.management.helper.LiquibaseMigrator
import io.dropwizard.Application
import io.dropwizard.db.DataSourceFactory
import io.dropwizard.migrations.MigrationsBundle
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import io.federecio.dropwizard.swagger.SwaggerBundle
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration
import org.kodein.di.instance

class UserManagementApplication: Application<UserManagementConfiguration>() {

    override fun run(configuration: UserManagementConfiguration, environment: Environment) {
        LiquibaseMigrator.migrate(configuration, environment)
        val di = DependencyInjectionConfiguration.getInstance(configuration, environment)
        val userInfoController: UserInfoController by di.instance()
        environment.jersey().register(userInfoController)
    }

    override fun initialize(bootstrap: Bootstrap<UserManagementConfiguration>) {
        bootstrap.objectMapper.registerModule(KotlinModule())
        bootstrap.addBundle(
            object : MigrationsBundle<UserManagementConfiguration>() {
                override fun getDataSourceFactory(configuration: UserManagementConfiguration): DataSourceFactory {
                    return configuration.database
                }
            }
        )
        bootstrap.addBundle(object : SwaggerBundle<UserManagementConfiguration>() {
            override fun getSwaggerBundleConfiguration(configuration: UserManagementConfiguration): SwaggerBundleConfiguration {
                return configuration.swagger
            }
        })
    }
}

fun main(args: Array<String>) {
    UserManagementApplication().run(*args)
}