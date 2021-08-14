package com.task.populararticles.presentation.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.task.populararticles.presentation.di.providers.BaseResourceProvider
import com.task.populararticles.presentation.di.providers.ResourceProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Singleton
    @Binds
    abstract fun provideContext(application: Application): Context

    @Singleton
    @Binds
    abstract fun provideResourceProvider(resourceProvider: ResourceProvider): BaseResourceProvider

}