package net.dankito.richtexteditor.android.extensions

import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager


fun View.showKeyboard() {
    this.context?.let { context ->
        this.requestFocus()

        val keyboard = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.showSoftInput(this, 0)
    }
}

fun View.showKeyboardDelayed(delayMillis: Long = 50L) {
    this.postDelayed({
        this.showKeyboard()
    }, delayMillis)
}

fun View.hideKeyboard(flags: Int = 0) {
    this.context?.let { context ->
        val keyboard = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.hideSoftInputFromWindow(this.windowToken, flags)
    }
}


fun View.getPixelSizeForDisplay(deviceIndependentPixel: Int): Int {
    return (deviceIndependentPixel * getDisplayDensity()).toInt()
}

fun View.getLayoutSize(sizeInDp: Int): Int {
    if(sizeInDp >= 0) {
        return getPixelSizeForDisplay(sizeInDp)
    }
    else { // e.g. ViewGroup.LayoutParams.MATCH_PARENT
        return sizeInDp
    }
}

fun View.getDisplayDensity(): Float {
    return this.context.resources.displayMetrics.density
}


fun View.setPadding(paddingTopBottomLeftRight: Int) {
    this.setPadding(paddingTopBottomLeftRight, paddingTopBottomLeftRight, paddingTopBottomLeftRight, paddingTopBottomLeftRight)
}


fun View.removeOnGlobalLayoutListener(layoutListener: ViewTreeObserver.OnGlobalLayoutListener?) {
    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
        this.viewTreeObserver.removeOnGlobalLayoutListener(layoutListener)
    }
    else {
        this.viewTreeObserver.removeGlobalOnLayoutListener(layoutListener)
    }
}