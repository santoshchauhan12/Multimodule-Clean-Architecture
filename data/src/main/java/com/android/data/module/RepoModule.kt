package com.android.data.module

import com.android.data.network.ApiService
import com.android.data.repository.ProductListRepositoryImp
import com.android.domain.repository.IProductListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun providesProductListRepo(productRepo: ProductListRepositoryImp): IProductListRepository
}