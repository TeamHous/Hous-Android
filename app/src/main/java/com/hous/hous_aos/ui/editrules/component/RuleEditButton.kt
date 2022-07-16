package com.hous.hous_aos.ui.editrules.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hous.hous_aos.R

@Composable
fun RuleEditButton() {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = colorResource(id = R.color.hous_blue))
            .width(152.dp)
            .height(48.dp)
            .clickable { /* 삭제 함수 */ }
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "저장하기",
            fontStyle = FontStyle(R.style.B1),
            color = colorResource(id = R.color.white)
        )
    }
}

@Preview
@Composable
fun PreviewEditButton() {
    RuleEditButton()
}
