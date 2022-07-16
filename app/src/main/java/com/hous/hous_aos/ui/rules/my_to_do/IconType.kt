package com.hous.hous_aos.ui.rules.my_to_do

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.hous.hous_aos.R

enum class IconType(@ColorRes val colorRes: Int, @DrawableRes val drawableRes: Int) {
    CLEAN(R.color.hous_blue_bg_2, R.drawable.ic_rules_broom_s),
    TRASH(R.color.hous_blue_bg_2, R.drawable.ic_rules_trash_s),
    LIGHT(R.color.hous_red_bg_2, R.drawable.ic_rules_bulb_s),
    HEART(R.color.hous_red_bg_2, R.drawable.ic_rules_heart_s),
    BEER(R.color.hous_yellow_bg_2, R.drawable.ic_rules_beer_s),
    CAKE(R.color.hous_yellow_bg_2, R.drawable.ic_rules_pancake_s),
    LAUNDRY(R.color.hous_purple_bg_2, R.drawable.ic_rules_laundry_s),
    COFFEE(R.color.hous_purple_bg_2, R.drawable.ic_rules_coffee_s)
}
