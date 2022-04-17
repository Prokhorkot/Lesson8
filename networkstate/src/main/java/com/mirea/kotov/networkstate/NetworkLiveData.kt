package com.mirea.kotov.networkstate

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import java.lang.Boolean


class NetworkLiveData(context: Context): LiveData<String>() {
    init {
        if (instance != null)
            throw RuntimeException("Use getInstance() method to get the single instance of this" +
                    "class.")
        context.also { this.context = it }
    }

    private var context: Context? = null
    private var broadcastReceiver: BroadcastReceiver? = null

    companion object{
        private var instance: NetworkLiveData? = null

        fun getInstance(context: Context): NetworkLiveData?{
            if(instance  == null)
                instance = NetworkLiveData(context.applicationContext)
            return instance
        }
    }

    private fun prepareReceiver(context: Context) {
        val filter = IntentFilter()
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val cm =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                val activeNetwork = cm.activeNetworkInfo

                if (activeNetwork != null) {
                    val isConnected = activeNetwork.isConnectedOrConnecting
                    value = Boolean.toString(isConnected)
                } else value = "false"
            }
        }
        context.registerReceiver(broadcastReceiver, filter)
    }

    override fun onActive() {
        prepareReceiver(context!!)
    }

    override fun onInactive() {
        context!!.unregisterReceiver(broadcastReceiver)
        broadcastReceiver = null
    }

}