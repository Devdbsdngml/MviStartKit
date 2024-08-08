package com.mvistartkit.di

import com.example.domain.GetIssueListUseCase
import com.example.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetIssueListUseCase(repository: Repository) = GetIssueListUseCase(repository)
}
