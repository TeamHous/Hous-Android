package com.hous.hous_aos.ui.newrules.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hous.hous_aos.R
import com.hous.hous_aos.data.model.response.NewRulesResponse
import com.hous.hous_aos.ui.newrules.addDay
import com.hous.hous_aos.ui.newrules.isAddDay

@Composable
fun NewRulesAddMangerButton(
    test: MutableState<List<Pair<MutableState<NewRulesResponse.Homie>, List<Pair<String, MutableState<State>>>>>>,
    homies: List<NewRulesResponse.Homie>,
    homieState: HashMap<String, Boolean>
) {
    if (
        test.value[test.value.size - 1].first.value.name != "담당자 없음" &&
        isAddDay(homies, homieState)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(32.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(colorResource(id = R.color.white))
                .padding(vertical = 4.dp)
                .clickable {
                    if (isAddDay(homies, homieState)) {
                        val ttt =
                            mutableListOf<Pair<MutableState<NewRulesResponse.Homie>, List<Pair<String, MutableState<State>>>>>()
                        test.value.forEach { ttt.add(it) }
                        ttt.add(addDay(homies, homieState))
                        test.value = ttt
                    }
                }
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_plus_blue),
                    contentDescription = ""
                )
            }
        }
    }
}
