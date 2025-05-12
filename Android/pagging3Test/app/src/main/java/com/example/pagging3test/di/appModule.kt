package com.example.pagging3test.di

import com.example.pagging3test.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    @Provides
    @Singleton
    fun provideItemRepository(): ItemRepository{
        return ItemRepository()
    }
}