package com.example.noteapp.util.connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest

object NetworkHelper {
    private lateinit var connectivityManager: ConnectivityManager

    val networkRequest = NetworkRequest.Builder()
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()

    private var isNetworkAvailable = false

    fun checkNetwork() = isNetworkAvailable

    fun init(context: Context) {
        connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback())
    }

    private fun networkCallback() = object : ConnectivityManager.NetworkCallback() {

        override fun onLost(network: Network) {
            super.onLost(network)
            isNetworkAvailable = false
        }

        override fun onUnavailable() {
            super.onUnavailable()
            isNetworkAvailable = false
        }

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            isNetworkAvailable = true
        }
    }
}