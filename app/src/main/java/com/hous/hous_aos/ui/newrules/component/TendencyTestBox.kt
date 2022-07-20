package com.hous.hous_aos.ui.newrules.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hous.hous_aos.R

@Preview
@Composable
fun Test() {
    TendencyBox {}
}

@Composable
fun TendencyBox(
    goTestActivity: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(color = colorResource(id = R.color.hous_red_bg))
            .padding(vertical = 30.dp, horizontal = 68.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .background(color = colorResource(id = R.color.hous_red))
                .padding(vertical = 14.dp, horizontal = 38.dp)
                .clickable { goTestActivity() }
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "테스트 하러 가기",
                fontStyle = FontStyle(R.style.B1),
                color = colorResource(id = R.color.white),
                textAlign = TextAlign.Center
            )
        }
    }
}
