package com.hous.hous_aos.ui.pentagon

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Pentagon(
    colorRes: Int,
    changeUserRadius: List<Int>
) {
    val radiusList = changeList(changeUserRadius)
    val colorResource = colorResource(id = colorRes)
    Canvas(modifier = Modifier.fillMaxSize()) {
        val size = this.size.center
        val angle = 2.0 * Math.PI / 5.0f
        val radiusPxList = radiusList.onEach { radius -> radius.dp.toPx() }
        val path = Path().apply {
            val currentAngle = -0.5 * Math.PI
            reset()
            moveTo(
                size.x + (radiusPxList[0] * cos(currentAngle)).toFloat(),
                size.y + (radiusPxList[0] * sin(currentAngle)).toFloat()
            )
            for (i in 1 until 5) {
                lineTo(
                    size.x + (radiusPxList[i] * cos(currentAngle + angle * i)).toFloat(),
                    size.y + (radiusPxList[i] * sin(currentAngle + angle * i)).toFloat()
                )
            }
            close()
        }
        drawIntoCanvas { canvas ->
            canvas.drawOutline(
                outline = Outline.Generic(path),
                paint = Paint().apply {
                    color = colorResource
                    pathEffect = PathEffect.cornerPathEffect(30.0.dp.toPx())
                }
            )
        }
    }
}

private fun changeList(radiusList: List<Int>): List<Float> {
    return listOf(
        mapper(radiusList[0]),
        mapper(radiusList[4]),
        mapper(radiusList[3]),
        mapper(radiusList[2]),
        mapper(radiusList[1]),
        mapper(radiusList[0]),
    )
}

private fun mapper(radius: Int): Float {
    return when (radius) {
        0 -> 50.0f
        1 -> 80.0f
        2 -> 110.0f
        3 -> 140.0f
        4 -> 170.0f
        5 -> 200.0f
        6 -> 230.0f
        7 -> 260.0f
        else -> 300.0f
    }
}