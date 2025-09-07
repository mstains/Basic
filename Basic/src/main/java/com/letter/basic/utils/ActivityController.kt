package com.letter.basic.utils

import android.app.Activity
import android.app.Application
import java.lang.ref.WeakReference
import java.util.*


object ActivityController {

    private val actList = LinkedList<Activity>()

    private var mApplication: Application? = null

    /**
     * 设置application
     * */
    fun setApplication(application: Application) {
        this.mApplication = application
    }

    fun addActivity(activity: Activity) {
        actList.add(activity)
    }

    fun removeActivity(activity: Activity) {
        actList.remove(activity)
    }

    fun clearAll() {
        actList.forEach {
            if (!it.isFinishing) {
                it.finish()
            }
        }
    }

    fun getSize(): Int {
        return actList.size
    }

    /**
     * 获取application对象
     * */
    fun getApplication(): Application? {
        return mApplication
    }


}