package com.hous.hous_aos.ui.editrules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hous.hous_aos.ui.editrules.component.RuleDeleteButton
import com.hous.hous_aos.ui.editrules.component.RuleEditButton

@Composable
fun EditRulesScreen() {
    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        LazyColumn {
            item {
                Row {
                    RuleDeleteButton()
                    Spacer(modifier = Modifier.size(16.dp))
                    RuleEditButton()
                }
            }
        }
    }
}
