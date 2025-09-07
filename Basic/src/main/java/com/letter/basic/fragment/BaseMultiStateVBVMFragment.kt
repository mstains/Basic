package com.letter.basic.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

/**
 * @Package:        com.energy.sources.base.fragment
 * @ClassName:      BaseMultiStateVBVBFragment
 * @Description:    Fragment基类，带网络请求
 * @Author:         Boqing.wu
 * @CreateDate:     2025/1/6 下午1:22
 * @UpdateUser:     更新者：
 * @UpdateDate:     2025/1/6 下午1:22
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
abstract class BaseMultiStateVBVMFragment<VB : ViewBinding, VM : ViewModel> :
    BaseMultiStateVBFragment<VB>() {

    protected val mViewModel: VM by lazy {
        ViewModelProvider.NewInstanceFactory().create(providerVMClass())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        startObserve()
    }




    private fun initViewModel() {
        providerVMClass().let { viewModel ->

            lifecycle.addObserver(this)


        }
    }

    protected open fun initErrorMessage(code: String?, message: String?) {

    }

    /**
     * 接口请求回调
     * */
    abstract fun startObserve()


    abstract fun providerVMClass(): Class<VM>


    override fun onDestroy() {
        mViewModel.let {
            lifecycle.removeObserver(this)
        }
        super.onDestroy()
    }
}
