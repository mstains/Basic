package com.letter.basic.activity

import androidx.core.content.ContextCompat
import com.letter.basic.R

/**
 * @ProjectName:    trip_android
 * @Package:        com.energy.sources.base.activity
 * @ClassName:      BaseCommonMultiStateVBActivity
 * @Description:    activity基类，common层封装一些基础数据
 * @Author:         Boqing.wu
 * @CreateDate:     2024/12/31 上午10:16
 * @UpdateUser:     更新者：
 * @UpdateDate:     2024/12/31 上午10:16
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
abstract class BaseCommonMultiStateActivity : BaseMultiStateActivity() {

    /**
     *使用viewbinding之后,
     * 一般不需要findViewById.
     * 其他第三方控件需要添加配置可重写该方法
     * */
    open fun initView() {

    }

    open fun initData() {

    }

    open fun initListener() {

    }

    /**
     * 初始化状态栏
     * */
    abstract fun initStatusBar()




}
