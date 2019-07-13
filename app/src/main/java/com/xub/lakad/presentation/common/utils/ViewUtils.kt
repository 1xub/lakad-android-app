package com.xub.lakad.presentation.common.utils

import android.content.Context
import android.graphics.Paint
import android.util.DisplayMetrics

object Utils {

    fun getDisplayMetrics(context: Context): DisplayMetrics {
        val resources = context.getResources()
        return resources.getDisplayMetrics()
    }

    fun convertDpToPixel(dp: Float, context: Context): Float {
        return dp * (getDisplayMetrics(context).densityDpi / 160f)
    }

    fun convertDpToPixelSize(dp: Float, context: Context): Int {
        val pixels = convertDpToPixel(dp, context)
        val res = (pixels + 0.5f).toInt()
        if (res != 0) {
            return res
        } else if (pixels == 0f) {
            return 0
        } else if (pixels > 0) {
            return 1
        }
        return -1
    }

    fun getTextWidth(paint: Paint, text: String): Float {
        return paint.measureText(text)
    }

}