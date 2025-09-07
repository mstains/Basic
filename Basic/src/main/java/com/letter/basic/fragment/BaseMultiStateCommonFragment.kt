package com.letter.basic.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver


/**
 * @Package:        com.energy.sources.base.fragment
 * @ClassName:      BaseCommonFragment
 * @Description:    Fragment基类
 * @Author:         Boqing.wu
 * @CreateDate:     2022/11/18 12:36
 * @UpdateUser:     更新者：
 * @UpdateDate:     2022/11/18 12:36
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
abstract class BaseMultiStateCommonFragment : Fragment(), LifecycleObserver {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return createView(inflater, container, savedInstanceState)
    }


    /**
     * 封装Fragment的onCreateView方法，把参数返回出去
     * */
    abstract fun createView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View

}
