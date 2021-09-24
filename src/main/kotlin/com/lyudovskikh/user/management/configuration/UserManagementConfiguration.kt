package com.lyudovskikh.user.management.configuration

import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration

class UserManagementConfiguration(
    val database: DataSourceFactory = DataSourceFactory(),
    val swagger: SwaggerBundleConfiguration = SwaggerBundleConfiguration()
):  Configuration()