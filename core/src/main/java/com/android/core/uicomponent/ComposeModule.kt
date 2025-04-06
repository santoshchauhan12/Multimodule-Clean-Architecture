package com.android.core.uicomponent

import com.android.core.ILoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ComposeModule {

    @Provides
    @Singleton
    fun providesComposeInstance(loader: ILoader):ComposeInstance =
        ComposeInstance(loader)
}