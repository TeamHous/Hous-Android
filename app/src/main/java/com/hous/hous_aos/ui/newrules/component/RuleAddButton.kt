package com.hous.hous_aos.ui.newrules.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.hous.hous_aos.R

@Composable
fun NewRulesAddRuleButton(
    isAddButton: Boolean,
    addNewRule: () -> Unit,
    finish: () -> Unit
) {
    val color =
        if (isAddButton) colorResource(id = R.color.hous_blue) else colorResource(id = R.color.g_3)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(48.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color)
            .padding(vertical = 12.dp)
            .clickable(isAddButton) {
                addNewRule()
                finish()
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center),
        ) {
            Text(
                text = "추가하기",
                fontStyle = FontStyle(R.style.B1),
                color = colorResource(id = R.color.white)
            )
        }
    }
}
