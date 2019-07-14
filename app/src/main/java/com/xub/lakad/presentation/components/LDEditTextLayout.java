package com.xub.lakad.presentation.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.material.textfield.TextInputLayout;
import com.xub.lakad.R;

public class LDEditTextLayout extends TextInputLayout {

    private static int TEXT_SPACE_LEFT;
    private static int TEXT_SPACE_BOTTOM;

    private static final int ERROR_TEXT_SIZE = 12;
    private static final String ERROR_TEXT_COLOR = "#FF4336";

    private AppCompatTextView tvTitle;
    private AppCompatTextView tvError;

    public LDEditTextLayout(Context context) {
        super(context);
    }

    public LDEditTextLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LDEditTextLayout);

        try {
            String title = a.getString(R.styleable.LDEditTextLayout_headerTitle);
            String errorText = a.getString(R.styleable.LDEditTextLayout_errorText);

            tvTitle = new AppCompatTextView(context);
            tvError = new AppCompatTextView(context);

            initTitle(title);
            initErrorText(errorText);

        } finally {
            a.recycle();
        }
    }

    public LDEditTextLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);

        if (getEditText() != null) {
            setHintEnabled(false);
        }
    }

    private void init() {
        TEXT_SPACE_LEFT = (int) getResources().getDimension(R.dimen.grid_padding);
        TEXT_SPACE_BOTTOM = (int) getResources().getDimension(R.dimen.grid_padding);
    }

    private void initTitle(String title) {
        /* set text */
        tvTitle.setText(title);

        /* init layout params */
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        params.leftMargin = TEXT_SPACE_LEFT;

        tvTitle.setPadding(TEXT_SPACE_LEFT, 0, 0, TEXT_SPACE_BOTTOM);

        /* set visibility */
        setTitleVisibility(title);

        /* add to view */
        addView(tvTitle, 0, params);
    }

    private void initErrorText(String errorText) {
        /* set text */
        tvError.setText(errorText);

        /* set layout params */
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        params.leftMargin = TEXT_SPACE_LEFT;

        tvError.setPadding(TEXT_SPACE_LEFT, 0, 0, TEXT_SPACE_BOTTOM);

        /* set text color */
        tvError.setTextColor(Color.parseColor(ERROR_TEXT_COLOR));

        /* set text size */
        tvError.setTextSize(ERROR_TEXT_SIZE);

        /* set visibility */
        setErrorTextVisibility(errorText);

        /* add to view */
        addView(tvError, params);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);

        setTitleVisibility(title);
    }

    public void setTitle(int titleResource) {
        tvTitle.setText(titleResource);

        setTitleVisibility(getContext().getString(titleResource));
    }

    public void setErrorText(String errorText) {
        tvError.setText(errorText);

        setErrorTextVisibility(errorText);
    }

    public String getTitle() {
        return tvTitle.getText().toString();
    }

    public String getErrorText() {
        return tvError.getText().toString();
    }

    private void setTitleVisibility(String title) {
        tvTitle.setVisibility(title != null && !title.isEmpty() ? VISIBLE : GONE);
    }

    private void setErrorTextVisibility(String errorText) {
        tvError.setVisibility(errorText != null && !errorText.isEmpty() ? VISIBLE : GONE);
    }
}
