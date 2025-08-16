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
    ctx: Context,
    service: Class<out Service>,
    params: Array<out Pair<String, Any?>>
): ComponentName? = ctx.startService(createIntent(ctx, service, params))


fun internalStopService(
    ctx: Context,
    service: Class<out Service>,
    params: Array<out Pair<String, Any?>>
): Boolean = ctx.stopService(createIntent(ctx, service, params))

/**
 * 封装获取intent中string值
 * */
fun Activity.getStringExtra(keyName: String, defaultValue: String = ""): String {

    return intent.getStringExtra(keyName) ?: defaultValue

}

/**
 * 获取intent中List<String>值
 * */
fun Activity.getStringListExtra(
    keyName: String, defaultValue: MutableList<String> = mutableListOf()
): MutableList<String> {

    return intent.getStringArrayListExtra(keyName) ?: defaultValue

}

/**
 * 封装获取intent中string值
 * */
fun Fragment.getStringExtra(keyName: String, defaultValue: String = ""): String {

    return requireActivity().intent.getStringExtra(keyName) ?: defaultValue

}

/**
 * 封装获取Fragment中string值
 * */
fun Fragment.getArgumentsStringExtra(keyName: String, defaultValue: String = ""): String {
    return arguments?.getString(keyName) ?: defaultValue

}

/**
 * 封装获取Fragment中Int值
 * */
fun Fragment.getArgumentsIntExtra(keyName: String, defaultValue: Int = 0): Int {
    return arguments?.getInt(keyName) ?: defaultValue

}

/**
 * 封装获取Fragment中Long值
 * */
fun Fragment.getArgumentsLongExtra(keyName: String, defaultValue: Long = 0L): Long {
    return arguments?.getLong(keyName) ?: defaultValue

}

/**
 * 封装获取Fragment中Boolean值
 * */
fun Fragment.getArgumentsBooleanExtra(keyName: String, defaultValue: Boolean = false): Boolean {
    return arguments?.getBoolean(keyName, defaultValue) ?: defaultValue

}

/**
 * 封装获取Fragment中Serializable值
 * */
fun Fragment.getArgumentsSerializableExtra(keyName: String): Serializable? {
    return arguments?.getSerializable(keyName)

}

/**
 * 封装获取intent中boolean值
 * */
fun Activity.getBooleanExtra(keyName: String, defaultValue: Boolean = false): Boolean {

    return intent.getBooleanExtra(keyName, defaultValue)
}

/**
 * 封装获取intent中boolean值
 * */
fun Fragment.getBooleanExtra(keyName: String, defaultValue: Boolean = false): Boolean {

    return requireActivity().intent.getBooleanExtra(keyName, defaultValue)
}

/**
 * 封装获取intent中double值
 * */
fun Activity.getDoubleExtra(keyName: String, defaultValue: Double = 0.0): Double =
    intent.getDoubleExtra(keyName, defaultValue)

/**
 * 封装获取intent中double值
 * */
fun Fragment.getDoubleExtra(keyName: String, defaultValue: Double = 0.0): Double =
    requireActivity().intent.getDoubleExtra(keyName, defaultValue)

/**
 * 封装获取intent中int值
 * */
fun Activity.getIntExtra(keyName: String, defaultValue: Int = 0): Int {

    return intent.getIntExtra(keyName, defaultValue)
}

/**
 * 封装获取intent中int值
 * */
fun Fragment.getIntExtra(keyName: String, defaultValue: Int = 0): Int {

    return requireActivity().intent.getIntExtra(keyName, defaultValue)
}

/**
 * 封装获取intent中Long值
 * */
fun Activity.getLongExtra(keyName: String, defaultValue: Long = 0L): Long {

    return intent.getLongExtra(keyName, defaultValue)
}

/**
 * 封装获取intent中Long值
 * */
fun Fragment.getLongExtra(keyName: String, defaultValue: Long = 0L): Long {

    return requireActivity().intent.getLongExtra(keyName, defaultValue)
}

/**
 * 封装获取intent中Float值
 * */
fun Activity.getFloatExtra(keyName: String, defaultValue: Float = 0f): Float {

    return intent.getFloatExtra(keyName, defaultValue)
}

/**
 * 封装获取intent中Float值
 * */
fun Fragment.getFloatExtra(keyName: String, defaultValue: Float = 0f): Float {

    return requireActivity().intent.getFloatExtra(keyName, defaultValue)
}

/**
 * 获取Serializable
 * */
fun Activity.getSerializableExtra(keyName: String): Serializable? {

    return intent.getSerializableExtra(keyName)

}

/**
 * 获取Serializable
 * */
fun Fragment.getSerializableExtra(keyName: String): Serializable? {

    return requireActivity().intent.getSerializableExtra(keyName)

}