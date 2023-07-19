package com.example.myplants.feature_main.presentation.list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.myplants.R
import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.model.Task
import com.example.myplants.feature_main.domain.model.TaskWithPlant
import com.example.myplants.ui.theme.NeutralN900
import com.example.myplants.ui.theme.OtherG100
import com.example.myplants.ui.theme.OtherG500

@Composable
fun TaskListItem(
    modifier: Modifier = Modifier,
    item: TaskWithPlant,
    onItemClick: (Plant) -> Unit,
    onStatusIconClick: (Task) -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { onItemClick(item.plant) }
            .then(modifier),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = OtherG500
        ),
        border = BorderStroke(0.5.dp, color = MaterialTheme.colorScheme.onSurface)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
                    .background(
                        color = OtherG100,
                        shape = RoundedCornerShape(
                            topStart = 10.dp,
                            topEnd = 10.dp,
                            bottomEnd = 0.dp,
                            bottomStart = 0.dp
                        )
                    )
            ) {
                val constraints = boxConstraintSet()

                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(
                        id = R.drawable.ic_plant_image_placeholder
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.None
                )

                ConstraintLayout(constraints) {
                    Box(
                        modifier = Modifier
                            .wrapContentWidth()
                            .height(20.dp)
                            .background(
                                color = MaterialTheme.colorScheme.onSurface,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(
                                start = 6.dp,
                                top = 2.dp,
                                end = 6.dp,
                                bottom = 2.dp
                            )
                            .layoutId("waterAmount")
                    ) {
                        Text(
                            text = "${item.plant.waterAmount}ml",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    }

                    Box(
                        modifier = Modifier
                            .wrapContentWidth()
                            .height(20.dp)
                            .background(
                                color = MaterialTheme.colorScheme.onSurface,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(
                                start = 6.dp,
                                top = 2.dp,
                                end = 6.dp,
                                bottom = 2.dp
                            )
                            .layoutId("dueDateText")
                    ) {
                        Text(
                            text = "Today",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .weight(1F)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = item.plant.name,
                        style = MaterialTheme.typography.bodySmall,
                        color = NeutralN900,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    item.plant.description?.let {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = it,
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = {
                        onStatusIconClick(item.task)
                    },
                    modifier = Modifier
                        .size(24.dp),
                    enabled = !item.task.isDone,
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.background,
                        disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    contentPadding = PaddingValues(4.dp),
                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(
                            id = if (item.task.isDone) {
                                R.drawable.ic_check
                            } else {
                                R.drawable.ic_water_drop
                            }
                        ),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }


        }

    }

}

private fun boxConstraintSet(): ConstraintSet {
    return ConstraintSet {
        val dueDateText = createRefFor("dueDateText")
        val waterAmount = createRefFor("waterAmount")

        constrain(waterAmount) {
            top.linkTo(parent.top, margin = 12.dp)
            start.linkTo(parent.start, margin = 12.dp)
        }
        constrain(dueDateText) {
            top.linkTo(waterAmount.bottom, margin = 4.dp)
            start.linkTo(parent.start, margin = 12.dp)
        }

    }
}