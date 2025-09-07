package com.letter.basic.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

/**
 * @Package:        com.energy.sources.base.fragment
 * @ClassName:      BaseMultiStateVBFragment
 * @Description:    Fragment基类
 * @Author:         Boqing.wu
 * @CreateDate:     2025/1/6 下午1:13
 * @UpdateUser:     更新者：
 * @UpdateDate:     2025/1/6 下午1:13
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
abstract class BaseMultiStateVBFragment<VB : ViewBinding> : BaseMultiStateCommonFragment() {

    /**
     * viewBinding应用对象
     * */
    protected  var viewBinding: VB?=null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        initListener()
    }

    /**
     * 封装Fragment的onCreateView方法，把参数返回出去
     * */
    override fun createView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        viewBinding = createViewBinding(inflater, container)
        return viewBinding!!.root
    }


    /**
     * 初始化View
     */
    abstract fun initView()

    /**
     * 初始化数据相关
     */
    open fun initData() {

    }

    open fun initListener() {}

    /**
     * 创建viewBinding
     * */
    abstract fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB
}
