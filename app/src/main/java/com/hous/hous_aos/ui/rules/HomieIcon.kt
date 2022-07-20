package com.hous.hous_aos.ui.rules

import androidx.annotation.DrawableRes
import com.hous.hous_aos.R

enum class HomieIconType(@DrawableRes val drawableRes: Int) {
    RED(R.drawable.sel_rules_dialog_red),
    BLUE(R.drawable.sel_rules_dialog_blue),
    GREEN(R.drawable.sel_rules_dialog_green),
    YELLOW(R.drawable.sel_rules_dialog_yellow),
    GRAY(R.drawable.sel_rules_dialog_gray),
    PURPLE(R.drawable.sel_rules_dialog_purple)
}
