package com.letter.basic.extend

import android.os.Parcelable
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


/**
 ********************************************
 * 2018/11/28 15:34
 * description:Fragment的扩展函数
 * ******************************************
 */

/**
 * 启动FragmentTransation,然后不保存状态的方式commit
 */
inline fun FragmentManager.transactAllowingStateLoss(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commitAllowingStateLoss()
}


/**
 * `fragment`添加到了容器中 其Id为`frameId`,然后展示该Fragment
 * 操作由fragmentManager执行
 */
fun Fragment.replaceChildInFragment(child: Fragment, @IdRes frameId: Int) {
    childFragmentManager.transact {
        replace(frameId, child, child.javaClass.simpleName)
    }
}

/**
 * `fragment`添加到了容器中 其Id为`frameId`,然后展示该Fragment
 * 操作由fragmentManager执行
 * 注意此方法为不保存状态的方法
 */
fun Fragment.replaceChildNotState(child: Fragment, @IdRes frameId: Int, tag: String) {
    childFragmentManager.transactAllowingStateLoss {
        replace(frameId, child, tag)
    }
}


fun Fragment.replaceChildNotState(child: Fragment, @IdRes frameId: Int) {
    childFragmentManager.transactAllowingStateLoss {
        replace(frameId, child, child.javaClass.simpleName)
    }
}

fun Fragment.replaceChildInFragment(child: Fragment, @IdRes frameId: Int, tag: String) {
    childFragmentManager.transact {
        replace(frameId, child, tag)
    }
}


/**
 * `fragment`添加到了容器中 其tag为`tag`.
 * 操作由fragmentManager执行
 */
fun Fragment.addChildToFragment(child: Fragment) {
    childFragmentManager.transactAllowingStateLoss {
        add(child, child.javaClass.simpleName)
    }
}

fun Fragment.addChildToFragmentNotState(child: Fragment, @IdRes frameId: Int) {
    childFragmentManager.transactAllowingStateLoss {
        add(frameId, child, child.javaClass.simpleName)
    }
}

fun Fragment.addChildToFragment(child: Fragment, @IdRes frameId: Int) {
    childFragmentManager.transact {
        add(frameId, child, child.javaClass.simpleName)
    }
}

fun Fragment.addChildToFragment(child: Fragment, @IdRes frameId: Int, tag: String) {
    childFragmentManager.transact {
        add(frameId, child, tag)
    }
}

/**
 * `fragment`在容器中隐藏
 * 操作由fragmentManager执行
 */
fun Fragment.showChildInFragment(child: Fragment) {
    childFragmentManager.transact {
        show(child)
    }
}

/**
 * `fragment`在容器中隐藏
 * 操作由fragmentManager执行
 */
fun Fragment.hideChildInFragment(child: Fragment) {
    childFragmentManager.transact {
        hide(child)
    }
}

/**
 * 隐藏Fragment下所有的Child
 */
fun Fragment.hideAllChild() {
    val childs = childFragmentManager.fragments
    if (childs.size > 0) {
        val transaction = childFragmentManager.beginTransaction()
        childs.forEach {
            transaction.hide(it)
        }
        transaction.commit()
    }
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
fun <T> Fragment.getArgumentsSerializableExtra(keyName: String): T? {
    return try {
        arguments?.getSerializable(keyName) as T?
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }


}


/**
 * 封装获取intent中boolean值
 * */
fun Fragment.getBooleanExtra(keyName: String, defaultValue: Boolean = false): Boolean =
    requireActivity().intent.getBooleanExtra(keyName, defaultValue)

/**
 * 封装获取intent中double值
 * */
fun Fragment.getDoubleExtra(keyName: String, defaultValue: Double = 0.0): Double =
    requireActivity().intent.getDoubleExtra(keyName, defaultValue)

/**
 * 封装获取intent中int值
 * */
fun Fragment.getIntExtra(keyName: String, defaultValue: Int = 0): Int =
    requireActivity().intent.getIntExtra(keyName, defaultValue)


/**
 * 封装获取intent中Long值
 * */
fun Fragment.getLongExtra(keyName: String, defaultValue: Long = 0L): Long =
    requireActivity().intent.getLongExtra(keyName, defaultValue)


/**
 * 封装获取intent中Float值
 * */
fun Fragment.getFloatExtra(keyName: String, defaultValue: Float = 0f): Float =
    requireActivity().intent.getFloatExtra(keyName, defaultValue)


/**
 * 获取Serializable
 * */
fun <T> Fragment.getSerializableExtra(keyName: String): T? {
    return try {
        requireActivity().getSerializableExtra(keyName) as T?
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }


}

/**
 * 获取ParcelableExtra
 * */
fun <T : Parcelable> Fragment.getParcelableExtra(keyName: String): T? =
    requireActivity().getParcelableExtra<T>(keyName)








