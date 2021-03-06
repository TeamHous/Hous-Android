package com.hous.hous_aos.ui.newrules.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.hous.hous_aos.R

@Composable
fun NewRulesTextField(
    ruleName: String,
    changeRuleName: (String) -> Unit,
    focusManager: FocusManager
) {
    val maxChar = 16
    BasicTextField(
        value = ruleName,
        onValueChange = {
            if (it.length < maxChar) changeRuleName(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(id = R.color.white))
            .height(37.dp)
            .padding(vertical = 8.dp, horizontal = 15.dp),
        textStyle = TextStyle(
            color = colorResource(id = R.color.black),
            fontFamily = FontFamily(
                Font(
                    resId = R.font.spoqa_han_sans_neo_medium,
                    style = FontStyle(R.style.B2)
                )
            )
        ),
        maxLines = 1,
        singleLine = true,
        cursorBrush = SolidColor(colorResource(id = R.color.hous_blue)),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
    )
}
