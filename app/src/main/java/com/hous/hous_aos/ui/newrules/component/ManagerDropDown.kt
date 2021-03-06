package com.hous.hous_aos.ui.newrules.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.hous.hous_aos.R
import com.hous.hous_aos.data.entity.Homie
import com.hous.hous_aos.ui.newrules.isAddDay

@Composable
fun ManagerDropDownMenu(
    managerIndex: Int,
    homies: List<Homie>,
    homieState: HashMap<String, Boolean>,
    checkBoxState: State,
    choiceManager: (Int, Homie) -> Unit
) {
    if (checkBoxState != State.SELECT && isAddDay(homies, homieState)) {
        var isExpanded by remember { mutableStateOf(false) }

        Image(
            modifier = Modifier
                .fillMaxSize()
                .clickable { isExpanded = true },
            painter = painterResource(id = R.drawable.ic_open),
            contentDescription = "",
            alignment = Alignment.CenterEnd,
            contentScale = ContentScale.Inside
        )

        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth(),
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            homies.forEach { homie ->
                if (homieState[homie.userName]!!) {
                    DropdownMenuItem(
                        onClick = {
                            choiceManager(managerIndex, homie)
                            isExpanded = false
                        }
                    ) {
                        val color = when (homie.typeColor) {
                            "RED" -> colorResource(id = R.color.hous_red)
                            "BLUE" -> colorResource(id = R.color.hous_blue)
                            "YELLOW" -> colorResource(id = R.color.hous_yellow)
                            "GREEN" -> colorResource(id = R.color.hous_green)
                            "PURPLE" -> colorResource(id = R.color.hous_purple)
                            else -> colorResource(id = R.color.g_3)
                        }
                        Row(
                            modifier = Modifier.wrapContentSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(shape = CircleShape)
                                    .size(16.dp)
                                    .background(color)
                            )
                            Spacer(modifier = Modifier.size(6.dp))
                            Text(
                                text = homie.userName,
                                fontFamily = FontFamily(
                                    Font(
                                        resId = R.font.spoqa_han_sans_neo_medium,
                                        style = FontStyle(R.style.B2)
                                    )
                                ),
                                color = colorResource(id = R.color.black)
                            )
                        }
                    }
                }
            }
        }
    }
}
