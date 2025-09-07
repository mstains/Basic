package com.letter.basic.dialog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.letter.basic.manager.ReceiverManager
import com.letter.basic.receiver.ReceiverImpl


/**
 * @Package:        com.letter.basic.dialog
 * @ClassName:      BaseVBVMReceiverDialogFragment
 * @Description:    DialogFragment的基类
 * @Author:         Boqing.wu
 * @CreateDate:     2024/12/17 14:52
 * @UpdateUser:     更新者：
 * @UpdateDate:     2024/12/17 14:52
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
abstract class BaseMultiStateVBVMReceiverDialogFragment<VB : ViewBinding, VM : ViewModel> :
    BaseMultiStateVBVMDialogFragment<VB, VM>(), ReceiverImpl {

    private val mReceiverManager: ReceiverManager by lazy {
        ReceiverManager(
            requireContext(), this
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initBroadcast()
    }


    /**
     * 初始化广播
     */
    open fun initBroadcast() {

    }

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


    override fun onDestroy() {
        super.onDestroy()
        unRegisterAllAction()
    }
}
