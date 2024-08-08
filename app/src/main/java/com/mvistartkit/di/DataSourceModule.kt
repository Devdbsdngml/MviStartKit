package com.mvistartkit.di

import com.example.data.DataSource
import com.example.data.DataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindDataSource(source: DataSourceImpl): DataSource
}
