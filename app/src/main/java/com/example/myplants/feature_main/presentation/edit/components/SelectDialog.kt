package com.example.myplants.feature_main.presentation.edit.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.myplants.R
import com.example.myplants.feature_main.presentation.edit.SelectItem
import com.example.myplants.ui.components.AccentButton
import com.example.myplants.ui.components.SecondaryButton
import com.example.myplants.ui.theme.MyPlantsTheme
import com.example.myplants.ui.theme.NeutralN900

@Composable
fun SelectDialog(
    title: String,
    options: List<SelectItem>,
    onSubmit: (List<String>) -> Unit,
    onDismissRequest: () -> Unit
) {
    var items by remember {
        mutableStateOf(options)
    }

    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(
                    top = 12.dp,
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 24.dp
                )
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = NeutralN900
                )

                Spacer(modifier = Modifier.height(4.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    items(items.size) { i ->
                        SelectOption(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(top = 16.dp, bottom = 16.dp, end = 20.dp),
                            text = items[i].title,
                            isChecked = items[i].isSelected,
                            onCheckedChange = {
                                items = items.mapIndexed { j, item ->
                                    if (i == j) {
                                        item.copy(isSelected = it)
                                    } else {
                                        item
                                    }
                                }
                            }
                        )

                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
                ) {
                    SecondaryButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1F, false),
                        text = "Cancel",
                        onClick = onDismissRequest
                    )

                    AccentButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .weight(1F, false),
                        text = "Got it",
                        onClick = {
                            onSubmit(
                                items.filter { it.isSelected }.map { it.title }
                            )
                            onDismissRequest()
                        }
                    )

                }
            }

        }

    }

}

@Composable
fun SelectOption(
    modifier: Modifier = Modifier,
    isChecked: Boolean = false,
    text: String,
    onCheckedChange: (Boolean) -> Unit
) {
    val color = animateColorAsState(
        targetValue = if (isChecked) {
            NeutralN900
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        }
    )
    Row(
        modifier = Modifier
            .clickable { onCheckedChange(!isChecked) }
            .then(modifier),
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isChecked) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(4.dp)
                    ),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        } else {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.onBackground,
                        shape = RoundedCornerShape(4.dp)
                    )
            )
        }

        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = color.value
        )
    }

}

@Preview
@Composable
fun SelectDialogPreview() {
    MyPlantsTheme {
        Surface {
            val dialogOpen = remember {
                mutableStateOf(true)
            }
            Button(
                onClick = { dialogOpen.value = true }
            ) {
                Text(text = "SHOW DIALOG")
            }

            if (dialogOpen.value) {
                SelectDialog(
                    title = "Dates",
                    options = listOf(
                        SelectItem("Everyday", false),
                        SelectItem("Monday", false),
                        SelectItem("Tuesday", false),
                        SelectItem("Wednesday", true)
                    ),
                    onSubmit = {},
                    onDismissRequest = {
                        dialogOpen.value = false
                    }
                )
            }

        }
    }
}