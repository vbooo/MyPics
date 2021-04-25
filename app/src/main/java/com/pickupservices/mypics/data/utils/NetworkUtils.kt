
package com.pickupservices.mypics.data.utils

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.pickupservices.mypics.data.INetworkUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Checks if a network connection exists.
 */
class NetworkUtils @Inject constructor(@ApplicationContext val context: Context):
    INetworkUtils {

    /**
     * this method check the device internet connection and returns a boolean which
     * signify that the device is connected to a network either on mobile data or wifi
     *
     * @return true if the device is connected to cellular or wifi network
     *
     * @see ConnectivityManager
     */
    override fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            networkCapabilities != null &&
                    (
                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                            )
        } else {
            /*
                Since we are targeting minSdk 21 we should check connection
                for older versions using deprecated method
            */
            if (connectivityManager.activeNetworkInfo != null) {
                val networkInfo = connectivityManager.activeNetworkInfo!!.type
                networkInfo == ConnectivityManager.TYPE_WIFI || networkInfo == ConnectivityManager.TYPE_MOBILE
            } else {
                false
            }
        }
    }
}
