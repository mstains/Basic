package com.letter.basic.extend

import android.app.Activity
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import java.io.Serializable

/**
 ********************************************
 * Create by ShaoShuai
 * 2019/4/29 10:12
 * description:Anko与AndroidX冲突,所以替换
 * ******************************************
 */

inline fun <reified T : Activity> Context.baseStartActivity(vararg params: Pair<String, Any?>) {

    internalStartActivity(this, T::class.java, params)
}


inline fun <reified T : Activity> Activity.startActivityForResult(
    requestCode: Int, vararg params: Pair<String, Any?>
) {
    internalStartActivityForResult(this, T::class.java, requestCode, params)
}


inline fun <reified T : Activity> Fragment.startActivityForResult(
    requestCode: Int, vararg params: Pair<String, Any?>
) {

    startActivityForResult(
        createIntent(activity!!, T::class.java, params), requestCode
    )

}

inline fun <reified T : Service> Context.startService(vararg params: Pair<String, Any?>) =
    internalStartService(this, T::class.java, params)


inline fun <reified T : Service> Fragment.startService(vararg params: Pair<String, Any?>) =
    internalStartService(activity!!, T::class.java, params)

inline fun <reified T : Service> Context.stopService(vararg params: Pair<String, Any?>) =
    internalStopService(this, T::class.java, params)


fun internalStartActivity(
    ctx: Context, activity: Class<out Activity>, params: Array<out Pair<String, Any?>>
) {
    ctx.startActivity(createIntent(ctx, activity, params))
}


fun <T> createIntent(
    ctx: Context, clazz: Class<out T>, params: Array<out Pair<String, Any?>>
): Intent {
    val intent = Intent(ctx, clazz)
    if (params.isNotEmpty()) fillIntentArguments(intent, params)
    return intent
}


private fun fillIntentArguments(intent: Intent, params: Array<out Pair<String, Any?>>) {
    params.forEach {
        val value = it.second
        when (value) {
            null -> intent.putExtra(it.first, null as Serializable?)
            is Int -> intent.putExtra(it.first, value)
            is Long -> intent.putExtra(it.first, value)
            is CharSequence -> intent.putExtra(it.first, value)
            is String -> intent.putExtra(it.first, value)
            is Float -> intent.putExtra(it.first, value)
            is Double -> intent.putExtra(it.first, value)
            is Char -> intent.putExtra(it.first, value)
            is Short -> intent.putExtra(it.first, value)
            is Boolean -> intent.putExtra(it.first, value)
            is Serializable -> intent.putExtra(it.first, value)
            is Bundle -> intent.putExtra(it.first, value)
            is Parcelable -> intent.putExtra(it.first, value)
            is Array<*> -> when {
                value.isArrayOf<CharSequence>() -> intent.putExtra(it.first, value)
                value.isArrayOf<String>() -> intent.putExtra(it.first, value)
                value.isArrayOf<Parcelable>() -> intent.putExtra(it.first, value)
                else -> throw RuntimeException("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
            }

            is IntArray -> intent.putExtra(it.first, value)
            is LongArray -> intent.putExtra(it.first, value)
            is FloatArray -> intent.putExtra(it.first, value)
            is DoubleArray -> intent.putExtra(it.first, value)
            is CharArray -> intent.putExtra(it.first, value)
            is ShortArray -> intent.putExtra(it.first, value)
            is BooleanArray -> intent.putExtra(it.first, value)
            else -> throw RuntimeException("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
        }
        return@forEach
    }
}


fun internalStartActivityForResult(
    act: Activity,
    activity: Class<out Activity>,
    requestCode: Int,
    params: Array<out Pair<String, Any?>>
) {
    act.startActivityForResult(createIntent(act, activity, params), requestCode)
}


fun internalStartService(
    ctx: Context, service: Class<out Service>, params: Array<out Pair<String, Any?>>
): ComponentName? = ctx.startService(createIntent(ctx, service, params))


fun internalStopService(
    ctx: Context, service: Class<out Service>, params: Array<out Pair<String, Any?>>
): Boolean = ctx.stopService(createIntent(ctx, service, params))



