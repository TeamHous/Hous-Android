package com.hous.hous_aos.ui.newrules.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.hous.hous_aos.R
import com.hous.hous_aos.data.model.response.NewRulesResponse

@Composable
fun CategoryDropDownMenu(
    ruleCategoryList: List<NewRulesResponse.Category>,
    setCategory: (String, String) -> Unit
) {
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
        ruleCategoryList.forEach { category ->
            DropdownMenuItem(
                onClick = {
                    setCategory(category._id, category.categoryName)
                    isExpanded = false
                }
            ) {
                Text(category.categoryName)
            }
        }
    }
}
