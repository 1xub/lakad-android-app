package com.xub.lakad.presentation.common.utils

import java.text.DecimalFormat

object NumberUtils {

    @Suppress("MagicNumber")
    fun getOrdinalNumber(n: Int): String {
        return when (n) {
            1 -> "1st"
            2 -> "2nd"
            3 -> "3rd"
            else -> "${n}th"
        }
    }

    fun formatDecimal(d: Double, zeroCount: Int): String {
        val zeros = StringBuilder()

        for (i in 0 until zeroCount) {
            zeros.append("0")
        }

        var pattern = "#,##0"

        if (zeros.isNotEmpty()) {
            pattern += ".$zeros"
        }

        val numberFormat = DecimalFormat(pattern)

        return numberFormat.format(d)
    }
}
