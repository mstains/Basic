package com.letter.basic.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager

/**
 ********************************************
 * 2018/12/4 11:31
 * description:广播的Util
 * ******************************************
 */
object BroadcastUtil {

    /**
     * 注册广播
     */
    fun registerLocalAction(action: String,
                            receiver: BroadcastReceiver,
                            context: Context) {
        val filter = IntentFilter()
        filter.addAction(action)
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, filter)
    }

    /**
     * 注销单个广播
     */
    fun unRegisterAction(context: Context, receiver: BroadcastReceiver) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver)
    }

    /**
     * 注销Map内的广播
     */
    fun unRegisterAction(context: Context, map: HashMap<String, out BroadcastReceiver>?) {
        map?.apply {
            map.keys.forEach {
                var receiver = get(it)
                receiver?.let {
                    LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver)
                }
            }

            map.clear()
        }
    }

}

