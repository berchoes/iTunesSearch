package com.example.itunesapp.di

import com.example.itunesapp.domain.repository.ListRepository
import com.example.itunesapp.repository.list.ListRepositoryImpl
import com.example.itunesapp.repository.list.ListService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Berk Ç. on 10.11.2021.
 */

@Module
@InstallIn(SingletonComponent::class)
object ListModule {

    @Provides
    @Singleton
    fun provideListService(retrofit: Retrofit): ListService =
        retrofit.create(ListService::class.java)

    @Provides
    @Singleton
    fun provideListRepository(service: ListService): ListRepository =
        ListRepositoryImpl(service)

}