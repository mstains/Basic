package com.letter.basic.manager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.letter.basic.receiver.ReceiverImpl
import com.letter.basic.utils.BroadcastUtil


/**
 * 2018/12/4 11:31
 * description:广播管理类
 * ******************************************
 */
class ReceiverManager(private val mContext: Context, private val mReceiverImpl: ReceiverImpl) {


    private var mReceiverMap: HashMap<String, Receiver>? = null

    /**
     * 注册广播
     */
    fun registerAction(action: String) {
        if (mReceiverMap == null) {
            mReceiverMap = HashMap()
        }
        mReceiverMap?.apply {
            if (!containsKey(action)) {
                val receiver = Receiver()
                this[action] = receiver
                BroadcastUtil.registerLocalAction(
                    action, receiver, mContext
                )
            }
        }
    }

    /**
     * 注销广播
     */
    fun unRegisterAction(action: String) {
        mReceiverMap?.let { map ->
            map[action]?.let { receiver ->
                BroadcastUtil.unRegisterAction(mContext, receiver)
            }
        }
    }

    /**
     * 注销所有广播
     */
    fun unRegisterAllAction() {
        BroadcastUtil.unRegisterAction(mContext, mReceiverMap)
    }

    /**
     * ReceiverManager内部实现的接收者
     */
    inner class Receiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            this@ReceiverManager.mReceiverImpl.onReceive(context, intent)
        }
    }
}

