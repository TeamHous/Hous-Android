package com.hous.hous_aos.ui.newrules.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.hous.domain.model.State
import com.hous.hous_aos.R

@Composable
fun DeleteButton(
    index: Int,
    setCheckBoxState: (String, State) -> Unit,
    deleteManager: (Int) -> Unit
) {
    setCheckBoxState("DeleteButton Out", State.BLOCK)
    Image(
        painter = painterResource(id = R.drawable.ic_delete),
        contentDescription = "",
        modifier = Modifier.clickable { deleteManager(index) }
    )
}
