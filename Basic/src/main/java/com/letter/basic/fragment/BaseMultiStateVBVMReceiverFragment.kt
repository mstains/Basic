package com.letter.basic.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.letter.basic.manager.ReceiverManager
import com.letter.basic.receiver.ReceiverImpl

/**
 * @ProjectName:    trip_android
 * @Package:        com.energy.sources.base.fragment
 * @ClassName:      BaseMultiStateVBVMReceiverFragment
 * @Description:    fragment基类，带网络请求及广播监听
 * @Author:         Boqing.wu
 * @CreateDate:     2025/1/6 下午1:29
 * @UpdateUser:     更新者：
 * @UpdateDate:     2025/1/6 下午1:29
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
abstract class BaseMultiStateVBVMReceiverFragment<VB : ViewBinding, VM : ViewModel> :
    BaseMultiStateVBVMFragment<VB, VM>(), ReceiverImpl {

    private val mReceiverManager: ReceiverManager by lazy { ReceiverManager(this) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBroadcast()
    }

    /**
     * 初始化广播监听
     * */
    abstract fun initBroadcast()
    /**
     * 广播接收的回掉
     */
    override fun onReceive(context: Context?, intent: Intent?) {

    }

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

    /**
     * 返回Context对象
     */
    override fun getReceiverContext(): Context {

        return this.requireContext()
    }

    override fun onDestroy() {
        super.onDestroy()
        unRegisterAllAction()
    }
}
