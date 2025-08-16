package com.letter.basic.utils

import android.app.Activity
import java.lang.ref.WeakReference
import java.util.*


object ActivityController {
    private val actList = LinkedList<Activity>()

    private lateinit var sCurrentActivityWeakRef: WeakReference<Activity>
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


    fun setCurrentActivity(activity: Activity) {
        sCurrentActivityWeakRef = WeakReference<Activity>(activity)
    }

    fun getCurrentActivity(): Activity? {

        if (sCurrentActivityWeakRef != null && sCurrentActivityWeakRef.get() != null) {

            return sCurrentActivityWeakRef.get()
        }

        return null
    }
}