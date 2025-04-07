package com.android.network

import android.content.Context
import android.content.pm.Capability
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

class NetworkHelper @Inject constructor(
    private val context: Context
) {

    fun isNetworkConnected() : Boolean {

      val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      val network =  manager.activeNetwork ?: return false
       val capabilities =  manager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}