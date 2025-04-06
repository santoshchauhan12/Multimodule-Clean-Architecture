package com.android.core.uicomponent

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface ComponentInstanceEntryPoint {

    fun getComponentInstanceEntry(): ComposeInstance
}