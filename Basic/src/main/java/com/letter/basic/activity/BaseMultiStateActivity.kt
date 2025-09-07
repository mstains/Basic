package com.letter.basic.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import com.letter.basic.utils.ActivityController


/**
 * @Package:        com.energy.sources.base.activity
 * @ClassName:      BaseMultiStateActivity
 * @Description:    activity基类
 * @Author:         Boqing.wu
 * @CreateDate:     2024/12/31 上午10:28
 * @UpdateUser:     更新者：
 * @UpdateDate:     2024/12/31 上午10:28
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
open class BaseMultiStateActivity : AppCompatActivity(), LifecycleObserver {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityController.addActivity(this)
    }



    override fun onDestroy() {
        super.onDestroy()
        ActivityController.removeActivity(this)
    }
}
