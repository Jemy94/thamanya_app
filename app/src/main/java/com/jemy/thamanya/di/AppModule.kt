package com.jemy.thamanya.di

import android.content.Context
import com.jemy.thamanya.ThamanyaApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): ThamanyaApp {
        return app as ThamanyaApp
    }

}