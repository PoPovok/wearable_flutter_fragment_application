package hu.popoapps.wearable_flutter_fragment_application.layout;

import android.content.Context;
import android.support.wearable.internal.view.SwipeDismissLayout;
import android.util.AttributeSet;
import android.view.View;

open class DismissibleLayout : SwipeDismissLayout {
    var isDismissible: Boolean = false
        private set

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    init {
        setDismissibleState(false)
    }

    fun setDismissibleState(isDismissible: Boolean) {
        this.isDismissible = isDismissible
        isSwipeable = !isDismissible
    }

    override fun canScroll(v: View?, checkV: Boolean, dx: Float, x: Float, y: Float): Boolean {
        return !isDismissible
    }
}