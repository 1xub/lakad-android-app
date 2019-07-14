package com.xub.lakad.presentation.components;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

import com.xub.lakad.R;

public class LDEditText extends AppCompatEditText {

    public LDEditText(Context context) {
        super(context);
    }

    public LDEditText(Context context, AttributeSet attrs) {
        super(context, attrs, R.attr.ubEditTextStyle);
    }
}
