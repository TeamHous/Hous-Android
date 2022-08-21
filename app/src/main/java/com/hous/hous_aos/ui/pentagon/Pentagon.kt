package com.hous.hous_aos.ui.pentagon

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hous.hous_aos.R
import kotlin.math.cos
import kotlin.math.sin

@Composable
@Preview(showBackground = true)
private fun AniPentagon() {
    Pentagon(
        colorRes = R.color.hous_green_bg,
        changeUserRadius = listOf(12, 12, 12, 12, 12)
    )
}

enum class AniState {
    START, END
}

@Composable
fun Pentagon(
    colorRes: Int,
    changeUserRadius: List<Int>
) {
    val radiusList = changeList(changeUserRadius)
    val colorResource = colorResource(id = colorRes)
    val animationTargetState = remember {
        mutableStateOf(AniState.START)
    }
    val transition = updateTransition(
        targetState = animationTargetState.value,
        label = ""
    )
    val radiusAni = transition.animateFloat(
        transitionSpec = { tween(durationMillis = 3000) },
        label = ""
    ) {
        if (it == AniState.START) 0f else 1f
    }
    Canvas(modifier = Modifier.fillMaxSize()) {
        val size = this.size.center
        val angle = 2.0 * Math.PI / 5.0f
        val radiusPxList = radiusList.onEach { radius -> radius.dp.toPx() }
        val path = Path().apply {
            val currentAngle = -0.5 * Math.PI
            reset()
            moveTo(
                size.x + (radiusPxList[0] * cos(currentAngle)).toFloat().times(radiusAni.value),
                size.y + (radiusPxList[0] * sin(currentAngle)).toFloat().times(radiusAni.value)
            )
            for (i in 1 until 5) {
                lineTo(
                    size.x + (radiusPxList[i] * cos(currentAngle + angle * i)).toFloat()
                        .times(radiusAni.value),
                    size.y + (radiusPxList[i] * sin(currentAngle + angle * i)).toFloat()
                        .times(radiusAni.value)
                )
            }
            close()
        }
        drawIntoCanvas { canvas ->
            canvas.drawOutline(
                outline = Outline.Generic(path),
                paint = Paint().apply {
                    color = colorResource
                    pathEffect = PathEffect.cornerPathEffect(15.0.dp.toPx())
                }
            )
        }
        animationTargetState.value = AniState.END
    }
}

private fun changeList(radiusList: List<Int>): List<Float> {
    return listOf(
        mapper(radiusList[0]),
        mapper(radiusList[4]),
        mapper(radiusList[3]),
        mapper(radiusList[2]),
        mapper(radiusList[1]),
        mapper(radiusList[0])
    )
}

private fun mapper(radius: Int): Float {
    return (radius * 22 + 36).toFloat()
}
