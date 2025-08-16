package com.letter.basic.dialog.builder

import android.view.Gravity
import android.view.WindowManager

/**
 ********************************************
 * Create by Vander
 * 2018/11/29 16:53
 * description: Dialog Window相关的构造者
 * ******************************************
 */
class WindowBuilder {

    var leftPadding = 0

    var rightPadding = 0

    var topPadding = 0

    var bottomPadding = 0

    var widthParam = WindowManager.LayoutParams.MATCH_PARENT

    var heightParam = WindowManager.LayoutParams.MATCH_PARENT

    var gravity = Gravity.CENTER

    var isTouchOutside = true

    var isCancelable = true

    var isAnim = true

    var isFocus = true

    /**
     * 设置Padding
     */
    fun padding(leftPadding: Int, topPadding: Int, rightPadding: Int, bottomPadding: Int): WindowBuilder {
        this.leftPadding = leftPadding
        this.topPadding = topPadding
        this.rightPadding = rightPadding
        this.bottomPadding = bottomPadding
        return this
    }

    /**
     * 设置宽度
     */
    fun width(widthParam: Int): WindowBuilder {
        this.widthParam = widthParam
        return this
    }

    /**
     * 设置高度
     */
    fun height(heightParam: Int): WindowBuilder {
        this.heightParam = heightParam
        return this
    }

    /**
     * 设置对齐方式
     */
    fun gravity(gravity: Int): WindowBuilder {
        this.gravity = gravity
        return this
    }

    /**
     * 设置触碰边界是否Dismiss
     */
    fun onTouchOutSide(isTouchOutside: Boolean): WindowBuilder {
        this.isTouchOutside = isTouchOutside
        return this
    }

    /**
     * 设置是否可以取消
     */
    fun cancelable(isCancelable: Boolean): WindowBuilder {
        this.isCancelable = isCancelable
        return this
    }

    /**
     * 设置是否使用默认的动画
     */
    fun anim(isAnim: Boolean): WindowBuilder {
        this.isAnim = isAnim
        return this
    }

    /**
     * 设置其是否获取焦点
     */
    fun focus(isFocus: Boolean): WindowBuilder {
        this.isFocus = isFocus
        return this
    }

}