package com.hous.hous_aos.ui.pentagon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hous.hous_aos.R
import com.hous.hous_aos.util.getColorSet
import kotlin.math.cos
import kotlin.math.sin

@Preview(showBackground = true)
@Composable
fun PreviewPentagon() {
    Box(
        modifier = Modifier.wrapContentHeight()
    ) {
        PentagonBox(
            "동그란 동글이",
            "PURPLE",
            listOf(3, 3, 3, 3, 3)
        )
    }
}

@Composable
fun PentagonBox(
    typeName: String,
    typeColor: String,
    typeScore: List<Int>
) {
    val boxColor = typeColor.getColorSet(typeColor)
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(colorResource(id = boxColor.colorBg))
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(32.dp))
            Text(
                text = typeName,
                fontFamily = FontFamily(Font(resId = R.font.spoqa_han_sans_neo_bold)),
                color = colorResource(id = boxColor.colorPrimary),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                lineHeight = 8.sp,
                letterSpacing = (-0.02).sp
            )
            Spacer(modifier = Modifier.size(16.dp))
            Box(modifier = Modifier.height(302.dp)) {
                Pentagon(
                    colorRes = boxColor.colorBg2,
                    changeUserRadius = listOf(12, 12, 12, 12, 12)
                )
                Pentagon(
                    colorRes = boxColor.colorPrimary,
                    changeUserRadius = typeScore
                )
                PentagonText()
            }
        }
    }
}

@Composable
private fun PentagonText() {
    val textList = stringArrayResource(id = R.array.pentagon_text)

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val currentAngle = -0.5 * Math.PI
        val angle = 2.0 * Math.PI / 5.0f
        val radiusList = listOf(340.0f, 350.0f, 350.0f, 350.0f, 350.0f)
        with(LocalDensity.current) {
            val radiusPxList = radiusList.onEach { radius -> radius.dp.toPx() }
            val center = Offset(
                this@BoxWithConstraints.maxWidth.toPx() / 2f,
                this@BoxWithConstraints.maxHeight.toPx() / 2f
            )
            for (i in 0 until 5) {
                BoxWithConstraints(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = textList[i],
                        modifier = Modifier
                            .offset(
                                (center.x + (radiusPxList[i] * cos(currentAngle + angle * i)).toFloat() - 35).toDp(),
                                (center.y + (radiusPxList[i] * sin(currentAngle + angle * i)).toFloat() - 20).toDp()
                            ),
                        fontStyle = FontStyle(R.style.Description),
                        color = colorResource(id = R.color.g_5)
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .offset((center.x).dp, (center.y + 400.0f.dp.toPx()).dp)
                    .size(16.dp),
            )
        }
    }
}
