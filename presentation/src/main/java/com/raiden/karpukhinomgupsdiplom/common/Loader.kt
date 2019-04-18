package com.raiden.karpukhinomgupsdiplom.common

import android.content.Context
import android.util.AttributeSet
import android.widget.ViewAnimator
import androidx.annotation.IdRes

class Loader(context: Context, attrs: AttributeSet) : ViewAnimator(context, attrs) {

    var displayedChildId: Int
        get() = getChildAt(displayedChild).id
        set(@IdRes id) {
            if (displayedChildId == id) {
                return
            }
            for (i in 0 until childCount) {
                if (getChildAt(i).id == id) {
                    displayedChild = i
                    return
                }
            }
            throw IllegalArgumentException("No view with ID $id")
        }
}
