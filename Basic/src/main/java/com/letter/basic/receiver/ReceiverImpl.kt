package com.letter.basic.receiver

import android.content.Context
import android.content.Intent

/**
 ********************************************
 * Create by Vander
 * 2018/12/4 11:13
 * description: 广播接收者的抽象
 * ******************************************
 */
interface ReceiverImpl {

    /**
     * 广播接收的回调
     */
    fun onReceive(context: Context?, intent: Intent?)


    /**
     * 注册广播
     */
    fun registerAction(action: String)

    /**
     * 注销单个广播
     */
    fun unRegisterAction(action: String)


    /**
     * 注销所有的广播
     */
    fun unRegisterAllAction()


    /**
     * 返回Context对象
     */
    fun getReceiverContext(): Context


}