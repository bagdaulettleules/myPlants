package com.example.myplants.feature_main.presentation.add.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.myplants.ui.components.AccentButton
import com.example.myplants.ui.components.SecondaryButton
import com.example.myplants.ui.theme.MyPlantsTheme
import com.example.myplants.ui.theme.NeutralN900

@Composable
fun SingleSelectDialog(
    title: String? = null,
    options: List<String>,
    defaultSelected: Int? = null,
    onSubmit: (Int) -> Unit,
    onDismissRequest: () -> Unit
) {
    val selectedOption = remember {
        mutableStateOf(defaultSelected)
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
                title?.let {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = NeutralN900
                    )

                    Spacer(modifier = Modifier.height(4.dp))
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    itemsIndexed(options) { index, option ->
                        OptionItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(top = 16.dp, bottom = 16.dp, end = 20.dp),
                            text = option,
                            isSelected = selectedOption.value == index,
                            onSelect = {
                                selectedOption.value = index
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
                            selectedOption.value?.let {
                                onSubmit(it)
                            }
                            onDismissRequest()
                        }
                    )

                }

            }

        }

    }

}

@Composable
fun OptionItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    text: String,
    onSelect: () -> Unit
) {
    val color = animateColorAsState(
        targetValue = if (isSelected) {
            NeutralN900
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        }
    )
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = {
                onSelect()
            },
            modifier = Modifier.size(24.dp)
        )
        Text(
            modifier = Modifier
                .clickable { onSelect() },
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = color.value
        )
    }

}

@Preview
@Composable
fun SingleSelectDialogPreview() {
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
                SingleSelectDialog(
                    title = "Plant Size",
                    options = listOf("Small", "Medium", "Large", "Extra large"),
                    defaultSelected = 1,
                    onSubmit = {},
                    onDismissRequest = {
                        dialogOpen.value = false
                    }
                )
            }

        }
    }
}