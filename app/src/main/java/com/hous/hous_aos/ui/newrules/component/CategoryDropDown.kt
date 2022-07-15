package com.hous.hous_aos.ui.newrules.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.hous.hous_aos.R
import com.hous.hous_aos.ui.newrules.NewRulesUiState
import com.hous.hous_aos.ui.newrules.State

@Composable
fun CategoryDropDownMenu(
    uiState: MutableState<NewRulesUiState>,
    checkBoxState: MutableState<State>,
) {
    if (checkBoxState.value != State.SELECT) {
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
            modifier = Modifier.fillMaxWidth(),
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            uiState.value.ruleCategory.forEach {
                DropdownMenuItem(
                    onClick = {
                        uiState.value = uiState.value.copy(categoryId = it._id)
                        uiState.value = uiState.value.copy(categoryName = it.categoryName)
                        isExpanded = false
                    }
                ) {
                    Text(it.categoryName)
                }
            }
        }
    }
}