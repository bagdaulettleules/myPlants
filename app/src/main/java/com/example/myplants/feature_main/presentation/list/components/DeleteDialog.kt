package com.example.myplants.feature_main.presentation.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.myplants.R
import com.example.myplants.ui.components.AccentButton
import com.example.myplants.ui.components.SecondaryButton
import com.example.myplants.ui.theme.MyPlantsTheme
import com.example.myplants.ui.theme.NeutralN900

@Composable
fun DeleteDialog(
    plantName: String = "",
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column {
                Column(
                    modifier = Modifier.padding(
                        top = 12.dp,
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 24.dp
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_x_circle),
                            contentDescription = null,
                            tint = Color.Red
                        )

                        Text(
                            text = "Are you sure?",
                            style = MaterialTheme.typography.bodyLarge,
                            color = NeutralN900
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Do you really want to delete the $plantName This process cannot be undone.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

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
                            onClick = onDismiss
                        )

                        AccentButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .weight(1F, false),
                            text = "Got it",
                            onClick = {
                                onConfirm()
                                onDismiss()
                            }
                        )

                    }
                }
            }

        }

    }

}

@Preview
@Composable
fun DeleteDialogPreview() {
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
                DeleteDialog(
                    plantName = "Monstera",
                    onConfirm = {},
                    onDismiss = {
                        dialogOpen.value = false
                    }
                )
            }

        }
    }
}