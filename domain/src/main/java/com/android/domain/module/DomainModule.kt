package com.android.domain.module

import com.android.domain.interactor.GetProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

//    @Provides
//    @Singleton
//    fun providesProductUseCase(): GetProductUseCase = GetProductUseCase()
}