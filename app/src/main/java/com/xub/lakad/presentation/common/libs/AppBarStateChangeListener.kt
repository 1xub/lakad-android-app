package com.xub.lakad.presentation.common.libs

import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

abstract class AppBarStateChangeListener : AppBarLayout.OnOffsetChangedListener {
    var currentState = State.IDLE
        private set
    var currentOffset = 0f
        private set

    enum class State {
        EXPANDED, COLLAPSED, IDLE
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, i: Int) {
        if (i == 0) {
            if (currentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED)
            }
            currentState = State.EXPANDED
        } else if (abs(i) >= appBarLayout.totalScrollRange) {
            if (currentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED)
            }
            currentState = State.COLLAPSED
        } else {
            if (currentState != State.IDLE) {
                onStateChanged(appBarLayout, State.IDLE)
            }
            currentState = State.IDLE
        }
        val offset = abs(i / appBarLayout.totalScrollRange.toFloat())
        if (offset != currentOffset) {
            currentOffset = offset
            onOffsetChanged(currentState, offset)
        }
    }

    abstract fun onStateChanged(appBarLayout: AppBarLayout, state: State)

    abstract fun onOffsetChanged(state: State, offset: Float)
}