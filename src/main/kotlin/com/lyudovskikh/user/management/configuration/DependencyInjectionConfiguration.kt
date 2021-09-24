package com.lyudovskikh.user.management.configuration

import com.lyudovskikh.user.management.controller.UserInfoController
import com.lyudovskikh.user.management.mapper.UserInfoMapper
import com.lyudovskikh.user.management.repository.UserInfoRepository
import com.lyudovskikh.user.management.service.UserInfoServiceImpl
import com.lyudovskikh.user.management.service.api.UserInfoService
import io.dropwizard.jdbi3.JdbiFactory
import io.dropwizard.setup.Environment
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.sqlobject.kotlin.KotlinSqlObjectPlugin
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import org.mapstruct.factory.Mappers

object DependencyInjectionConfiguration {
    fun getInstance(configuration: UserManagementConfiguration, environment: Environment) : DI {
        return DI {
            bind { singleton { JdbiFactory() }}
            bind {
                singleton {
                    instance<JdbiFactory>()
                        .build(environment, configuration.database, "h2")
                        .installPlugin(KotlinPlugin())
                        .installPlugin(KotlinSqlObjectPlugin())
                }
            }
            bind<UserInfoMapper> { singleton { Mappers.getMapper(UserInfoMapper::class.java) } }
            bind<UserInfoRepository> { singleton { instance<Jdbi>().onDemand(UserInfoRepository::class.java) } }
            bind<UserInfoService> { singleton { UserInfoServiceImpl(instance(), instance()) } }
            bind { singleton { UserInfoController(instance()) } }
        }
    }
}