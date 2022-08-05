package com.hous.hous_aos.ui.newrules.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.hous.data.entity.State
import com.hous.hous_aos.R
import com.hous.hous_aos.ui.newrules.DayData

@Composable
fun NewRulesDay(
    dayData: DayData,
    currentIndex: Int,
    selectDay: (Int, DayData) -> Unit
) {
    val color = when (dayData.dayState) {
        State.UNSELECT -> colorResource(id = R.color.white)
        State.SELECT -> colorResource(id = R.color.hous_blue_bg_2)
        State.BLOCK -> colorResource(id = R.color.g_2)
    }
    val textColor = when (dayData.dayState) {
        State.BLOCK -> colorResource(id = R.color.g_4)
        else -> colorResource(id = R.color.hous_blue)
    }

    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(shape = CircleShape)
            .background(color)
            .clickable(dayData.dayState != State.BLOCK) {
                Log.d("sdlhfjaskdf", "index: $currentIndex dayData $dayData")
                selectDay(currentIndex, dayData)
            }
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center),
            text = dayData.day,
            color = textColor,
            fontFamily = FontFamily(
                Font(
                    resId = R.font.spoqa_han_sans_neo_medium,
                    style = FontStyle(R.style.B3)
                )
            ),
        )
    }
}
