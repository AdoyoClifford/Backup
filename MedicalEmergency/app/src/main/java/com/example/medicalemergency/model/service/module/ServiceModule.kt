package com.example.medicalemergency.model.service.module

import com.example.medicalemergency.model.service.AccountService
import com.example.medicalemergency.model.service.LogService
import com.example.medicalemergency.model.service.impl.AccountServiceImpl
import com.example.medicalemergency.model.service.impl.LogServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

    @Binds
    abstract fun provideLogService(impl: LogServiceImpl): LogService

}