package com.hous.hous_aos.ui.newrules.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import com.hous.hous_aos.R

@Composable
fun NewRulesToolbar(
    notificationState: Boolean,
    checkBoxState: State,
    toggleState: (Boolean) -> Unit,
    finish: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "",
            modifier = Modifier.clickable { finish() }
        )
        Text(
            text = stringResource(id = R.string.new_rules_title),
            fontStyle = FontStyle(R.style.B1)
        )
        if (!notificationState || checkBoxState == State.SELECT) {
            Image(
                painter = painterResource(id = R.drawable.ic_alarmoff),
                contentDescription = "",
                modifier = Modifier.clickable {
                    if (checkBoxState != State.SELECT) toggleState(true)
                }
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_alarmon),
                contentDescription = "",
                modifier = Modifier.clickable { toggleState(false) }
            )
        }
    }
}
