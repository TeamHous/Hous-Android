package com.hous.hous_aos.ui.newrules.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hous.domain.model.CategoryInfo
import com.hous.hous_aos.R

@Composable
fun CategoryItem(
    radius: Dp,
    categoryName: String,
    ruleCategoryList: List<CategoryInfo>,
    setCategory: (String, String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(36.dp)
            .clip(shape = RoundedCornerShape(radius))
            .background(colorResource(id = R.color.white))
            .padding(horizontal = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterStart),
        ) {
            Text(
                text = categoryName,
                fontFamily = FontFamily(
                    Font(
                        resId = R.font.spoqa_han_sans_neo_medium,
                        style = FontStyle(R.style.B2)
                    )
                ),
                color = colorResource(id = R.color.black)
            )
        }
        Box(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterEnd),
        ) {
            CategoryDropDownMenu(
                ruleCategoryList = ruleCategoryList,
                setCategory = setCategory
            )
        }
    }
}
