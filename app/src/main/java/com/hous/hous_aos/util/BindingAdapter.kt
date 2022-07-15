package com.hous.hous_aos.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.Nullable
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("app:visibility")
fun View.setVisibility(isVisible: Boolean) {
    this.isVisible = isVisible
}

/**
 * 나중에 사용할 곳 좀 많아서 추가*/
@BindingAdapter("app:imageRes")
fun ImageView.setDrawable(@Nullable @DrawableRes drawableRes: Int) {
    if (drawableRes != -1) this.setImageResource(drawableRes)
}

@BindingAdapter("app:colorRes")
fun CardView.setColor(@ColorRes colorRes: Int) {
    if (colorRes != -1) {
        this.backgroundTintList = context.getColorStateList(colorRes)
    }
}

@BindingAdapter("app:isSelected")
fun TextView.setSelected(isSelected: Boolean) {
    this.isSelected = isSelected
}

@BindingAdapter("app:isSelected")
fun ImageView.setSelected(isSelected: Boolean) {
    this.isSelected = isSelected
}
