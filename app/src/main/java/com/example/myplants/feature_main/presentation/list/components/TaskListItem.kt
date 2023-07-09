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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.myplants.R
import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.model.Size
import com.example.myplants.feature_main.domain.model.Task
import com.example.myplants.feature_main.domain.model.TaskWithPlant
import com.example.myplants.ui.theme.MyPlantsTheme
import com.example.myplants.ui.theme.NeutralN000
import com.example.myplants.ui.theme.NeutralN900
import com.example.myplants.ui.theme.OtherG100
import com.example.myplants.ui.theme.OtherG500
import java.time.DayOfWeek
import java.util.Date

@Composable
fun TaskListItem(
    modifier: Modifier = Modifier,
    task: TaskWithPlant,
    onItemClick: (Plant) -> Unit,
    onStatusIconClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .clickable { onItemClick(task.plant) }
            .then(modifier),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = NeutralN000,
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
                    .weight(3F)
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
                val constraints = decoupledConstraints()

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
                            text = "${task.plant.waterAmount}ml",
                            style = MaterialTheme.typography.labelMedium,
                            color = NeutralN000
                        )
                    }

                    Box(
                        modifier = Modifier
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
                            color = NeutralN000
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = task.plant.name,
                        style = MaterialTheme.typography.bodySmall,
                        color = NeutralN900,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    task.plant.description?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedButton(
                    onClick = onStatusIconClick,
                    modifier = Modifier
                        .size(24.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.background
                    ),
                    contentPadding = PaddingValues(4.dp),
                    border = BorderStroke(0.dp, MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.ic_water_drop),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }


        }

    }

}

private fun decoupledConstraints(): ConstraintSet {
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

@Preview
@Composable
fun TaskCardPreview() {
    MyPlantsTheme {
        TaskListItem(
            task = TaskWithPlant(
                Task(
                    1, DayOfWeek.FRIDAY, Date(), false, null, 2
                ),
                Plant(
                    "Monstera",
                    "Description",
                    null,
                    emptyList(),
                    12,
                    50,
                    Size.MEDIUM,
                    null,
                    1
                )
            ),
            onItemClick = {},
            onStatusIconClick = {}
        )
    }
}