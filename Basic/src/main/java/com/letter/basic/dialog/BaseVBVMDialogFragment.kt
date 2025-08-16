package com.letter.basic.dialog

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

/**
 * @ProjectName:    trip_android
 * @Package:        com.geely.travel.geelytravel.base.geely.dialog
 * @ClassName:      BaseDialogVBVMDialogFragment
 * @Description:    DialogFragment基类
 * @Author:         Boqing.wu
 * @CreateDate:     2024/3/7 14:57
 * @UpdateUser:     更新者：
 * @UpdateDate:     2024/3/7 14:57
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
abstract class BaseVBVMDialogFragment<VB : ViewBinding, VM : ViewModel> :
    BaseVBDialogFragment<VB>() {

    protected val mViewModel: VM by lazy {
        ViewModelProvider.NewInstanceFactory().create(providerVMClass())
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initViewModel()
        startObserve()
        super.onActivityCreated(savedInstanceState)
    }

    private fun initViewModel() {
        providerVMClass().let { viewModel ->

            lifecycle.addObserver(this)


        }
    }


    abstract fun startObserve()


    abstract fun providerVMClass(): Class<VM>


    override fun onDestroy() {
        mViewModel.let {
            lifecycle.removeObserver(this)
        }
        super.onDestroy()
    }
}
