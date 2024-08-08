package com.mvistartkit.di

import com.example.data.DispatcherProvider
import com.example.data.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {
    @Binds
    abstract fun bindDispatcherProvider(dispatcherProvider: DispatcherProviderImpl): DispatcherProvider
}
