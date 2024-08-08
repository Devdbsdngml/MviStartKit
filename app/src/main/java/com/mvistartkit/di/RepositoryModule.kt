package com.mvistartkit.di

import com.example.data.RepositoryImpl
import com.example.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}
