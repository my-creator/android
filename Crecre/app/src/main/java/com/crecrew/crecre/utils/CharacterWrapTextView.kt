package com.crecrew.crecre.utils

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

class CharacterWrapTextView : android.support.v7.widget.AppCompatTextView {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun setText(text: CharSequence, type: TextView.BufferType) {
        super.setText(text.toString().replace(" ", "\u00A0"), type)
    }
}