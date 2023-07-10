package com.example.myplants.feature_main.presentation.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myplants.R
import com.example.myplants.ui.components.BigButton
import com.example.myplants.ui.theme.NeutralN900

@Composable
fun EmptyListMessage(
    modifier: Modifier = Modifier,
    onAddButtonClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_empty_list),
            contentDescription = "Empty list"
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Sorry.",
            style = MaterialTheme.typography.bodyLarge,
            color = NeutralN900
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "There are no plants in the list, please add your first plant.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        BigButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Add Your First Plant",
            onClick = onAddButtonClick
        )
    }
}