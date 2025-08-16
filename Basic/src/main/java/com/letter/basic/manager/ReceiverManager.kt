package com.letter.basic.manager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.letter.basic.receiver.ReceiverImpl
import com.letter.basic.utils.BroadcastUtil


/**
 ********************************************
 * Create by Vander
 * 2018/12/4 11:12
 * description: 广播管理者
 * ******************************************
 */
class ReceiverManager {

    var mReceiverImpl: ReceiverImpl

    private var mReceiverMap: HashMap<String, Receiver>? = null

    constructor(impl: ReceiverImpl) {
        this.mReceiverImpl = impl
    }

    /**
     * 注册广播
     */
    fun registerAction(action: String) {
        if (mReceiverMap == null) {
            mReceiverMap = HashMap()
        }
        mReceiverMap?.apply {
            if (!containsKey(action)) {
                var receiver = Receiver()
                this[action] = receiver
                BroadcastUtil.registerLocalAction(action, receiver, mReceiverImpl.getReceiverContext())
            }
        }
    }

    /**
     * 注销广播
     */
    fun unRegisterAction(action: String) {
        mReceiverMap?.let { map ->
            map[action]?.let { receiver ->
                BroadcastUtil.unRegisterAction(mReceiverImpl.getReceiverContext(), receiver)
            }
        }
    }

    /**
     * 注销所有广播
     */
    fun unRegisterAllAction() {
        BroadcastUtil.unRegisterAction(mReceiverImpl.getReceiverContext(), mReceiverMap)
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

