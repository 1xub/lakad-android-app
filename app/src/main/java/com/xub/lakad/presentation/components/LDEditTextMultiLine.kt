package com.xub.lakad.presentation.components

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.xub.lakad.R

class LDEditTextMultiLine : AppCompatEditText {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs, R.attr.ubEditTextMultiLineStyle)
}