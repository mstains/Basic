package com.letter.basic.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.viewbinding.ViewBinding

import com.letter.basic.receiver.ReceiverImpl
import com.letter.basic.manager.ReceiverManager

/**
 * @Package:        com.energy.sources.base.activity
 * @ClassName:      BaseMultiStateVBVMReceiverActivity
 * @Description:    带广播监听的activity基类
 * @Author:         Boqing.wu
 * @CreateDate:     2024/12/31 上午11:18
 * @UpdateUser:     更新者：
 * @UpdateDate:     2024/12/31 上午11:18
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
abstract class BaseMultiStateVBReceiverActivity<VB : ViewBinding> :
    BaseMultiStateVBActivity<VB>(), ReceiverImpl {

    private val mReceiverManager: ReceiverManager by lazy { ReceiverManager(this, this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBroadcast()

    }

    /**
     * 初始化广播监听
     * */
    abstract fun initBroadcast()


    /**
     * 注册广播
     */
    override fun registerAction(action: String) {
        mReceiverManager.registerAction(action)
    }

    /**
     * 注销单个广播
     */
    override fun unRegisterAction(action: String) {
        mReceiverManager.unRegisterAction(action)
    }

    /**
     * 注销所有的广播
     */
    override fun unRegisterAllAction() {
        mReceiverManager.unRegisterAllAction()
    }


    override fun onDestroy() {
        super.onDestroy()
        unRegisterAllAction()
    }

    /**
     * 广播接收的回调
     */
    abstract fun onReceive(intent: Intent?)


}
