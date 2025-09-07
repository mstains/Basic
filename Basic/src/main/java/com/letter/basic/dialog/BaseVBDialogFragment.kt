package com.letter.basic.dialog

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleObserver
import androidx.viewbinding.ViewBinding
import com.letter.basic.dialog.builder.WindowBuilder

/**
 * @Package:        BaseVBDialogFragment
 * @ClassName:      BaseDialogVBFragment
 * @Description:    DialogFragment的基类
 * @Author:         Boqing.wu
 * @CreateDate:     2022/2/10 16:56
 * @UpdateUser:     更新者：
 * @UpdateDate:     2022/2/10 16:56
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
abstract class BaseVBDialogFragment<VB : ViewBinding> : DialogFragment(), LifecycleObserver {


    /**
     * viewBinding应用对象
     * */
    protected var viewBinding: VB? = null

    private val mDisplayMetrics: DisplayMetrics? by lazy {
        val dm = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(dm)
        dm
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Dialog)
        init()
    }

    /**
     * BaseDialogFragment内置的初始化
     */
    private fun init() {
        activity?.apply {
            initIntent(intent)
        }
        arguments?.apply {
            initBundle(this)
        }
        initBase()
    }

    /**
     * 获取Activity中Intent传递的参数
     */
    open fun initIntent(intent: Intent) {

    }

    /**
     * 获取Bundle中传递的参数
     */
    open fun initBundle(bundle: Bundle) {

    }

    /**
     * 加载必要初始化对象方法
     */
    open fun initBase() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        initWindow()
        viewBinding = createViewBinding(inflater, container)
        return viewBinding!!.root
    }


    /**
     * 配置Dialog的Window参数
     */
    open fun getWindowBuild(dm: DisplayMetrics?): WindowBuilder {
        val builder = WindowBuilder()
        builder.apply {

            widthParam = WindowManager.LayoutParams.MATCH_PARENT
            heightParam = WindowManager.LayoutParams.WRAP_CONTENT
            isTouchOutside = false
            isCancelable = true
            gravity = Gravity.BOTTOM
        }
        return builder
    }

    /**
     * 加载窗口参数
     */
    protected open fun initWindow() {

        val builder = getWindowBuild(mDisplayMetrics)
        dialog?.apply {
            window?.apply {
                builder.apply {
                    decorView.setPadding(leftPadding, topPadding, rightPadding, bottomPadding)
                    attributes.width = widthParam
                    attributes.height = heightParam
                    attributes.gravity = gravity
                    setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    setCanceledOnTouchOutside(isTouchOutside)
                }

            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        isCancelable = getWindowBuild(mDisplayMetrics).isCancelable
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
        initListener()
    }


    open fun initView() {

    }

    /**
     * 加载Data方法
     */
    open fun initData() {

    }

    /**
     * 加载监听Listener
     */
    open fun initListener() {

    }

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            super.show(manager, tag)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun show(transaction: FragmentManager) {
        try {
            this.show(transaction, this::class.java.simpleName)
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }


    fun isShowing(): Boolean {
        dialog?.let {
            return it.isShowing

        }

        return false
    }


    /**
     * 创建viewBinding
     * */
    abstract fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB
}
