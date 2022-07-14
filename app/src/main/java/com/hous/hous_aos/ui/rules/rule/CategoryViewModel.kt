package com.hous.hous_aos.ui.rules.rule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hous.hous_aos.R
import com.hous.hous_aos.data.entity.rules.CategoryOfRuleResponse

class CategoryViewModel : ViewModel() {

    private var _categoryOfRuleList =
        MutableLiveData<MutableList<CategoryOfRuleResponse>>()
    val categoryOfRuleList get() = _categoryOfRuleList

    init {
        fetchToCategoryOfRuleList()
    }

    fun onChangeIsSelected(position: Int) {
        val tmpCategoryOfRuleList = requireNotNull(_categoryOfRuleList.value).map { data ->
            data.copy().apply { isSelected = false }
        }
        tmpCategoryOfRuleList[position].isSelected = true
        _categoryOfRuleList.value = tmpCategoryOfRuleList.toMutableList()
    }

    private fun fetchToCategoryOfRuleList() {
        /**  dummy data입니다만..??
         * */
        _categoryOfRuleList.value = mutableListOf(
            CategoryOfRuleResponse(
                name = "라면을",
                icon = R.drawable.ic_rules_heart_s,
                backGround = R.drawable.ic_rules_category_red_bg_m
            ),
            CategoryOfRuleResponse(
                name = "너무",
                icon = R.drawable.ic_rules_beer_s,
                backGround = R.drawable.ic_rules_category_yellow_bg_m
            ),
            CategoryOfRuleResponse(
                name = "많이",
                icon = R.drawable.ic_rules_broom_s,
                backGround = R.drawable.ic_rules_category_blue_bg_m
            ),
            CategoryOfRuleResponse(
                name = "먹었더니",
                icon = R.drawable.ic_rules_heart_s,
                backGround = R.drawable.ic_rules_category_red_bg_m
            ),
            CategoryOfRuleResponse(
                name = "배불러",
                icon = R.drawable.ic_rules_heart_s,
                backGround = R.drawable.ic_rules_category_red_bg_m
            ),
            CategoryOfRuleResponse(
                name = "",
                icon = R.drawable.ic_rules_heart_s,
                backGround = R.drawable.ic_rules_todo_plus
            )
        )
    }

    companion object {
        const val TAG = "viewmodel"
    }
}
