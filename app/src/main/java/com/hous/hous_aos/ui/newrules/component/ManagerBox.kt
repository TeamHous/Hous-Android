package com.hous.hous_aos.ui.newrules.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.hous.domain.model.Homie
import com.hous.domain.model.Manager
import com.hous.domain.model.State
import com.hous.hous_aos.R

@Composable
fun ManagerBox(
    radius: Dp,
    managerIndex: Int,
    manager: Manager,
    homies: List<Homie>,
    homieState: HashMap<String, Boolean>,
    checkBoxState: State,
    choiceManager: (Int, Homie) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .size(36.dp)
            .clip(shape = RoundedCornerShape(radius))
            .background(colorResource(id = R.color.white))
            .padding(horizontal = 12.dp)
    ) {
        val color = when (manager.managerHomie.typeColor) {
            "RED" -> colorResource(id = R.color.hous_red)
            "BLUE" -> colorResource(id = R.color.hous_blue)
            "YELLOW" -> colorResource(id = R.color.hous_yellow)
            "GREEN" -> colorResource(id = R.color.hous_green)
            "PURPLE" -> colorResource(id = R.color.hous_purple)
            "GREY" -> colorResource(id = R.color.g_3)
            else -> null
        }
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (color != null) {
                Box(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .size(16.dp)
                        .background(color)
                )
                Spacer(modifier = Modifier.size(6.dp))
            }
            Text(
                text = manager.managerHomie.userName,
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
                .fillMaxSize()
        ) {
            ManagerDropDownMenu(
                managerIndex = managerIndex,
                homies = homies,
                homieState = homieState,
                checkBoxState = checkBoxState,
                choiceManager = choiceManager
            )
        }
    }
}
