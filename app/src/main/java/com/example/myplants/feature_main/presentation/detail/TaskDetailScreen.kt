package com.example.myplants.feature_main.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.myplants.R
import com.example.myplants.feature_main.presentation.detail.components.ShortSummarySection
import com.example.myplants.feature_main.presentation.util.Screen
import com.example.myplants.ui.components.AccentButton
import com.example.myplants.ui.components.CircleButton
import com.example.myplants.ui.theme.MyPlantsTheme
import com.example.myplants.ui.theme.NeutralN900

@OptIn(
    ExperimentalGlideComposeApi::class
)
@Composable
fun TaskDetailScreen(
    navController: NavHostController = rememberNavController(),
    name: String,
    description: String,
    imageUrl: String,
    size: String,
    frequency: String,
    amount: String,
    isDone: Boolean = false,
    onMarkWatered: () -> Unit,
    onEdit: () -> Long?
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            GlideImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            ) {
                it
                    .placeholder(R.drawable.ic_plant_image_placeholder)
                    .error(R.drawable.ic_plant_image_placeholder)
                    .load(imageUrl)
            }

            val constraintSet = screenContentConstraints()
            ConstraintLayout(
                constraintSet = constraintSet,
                modifier = Modifier.fillMaxSize()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .layoutId("detail_section"),
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentColor = MaterialTheme.colorScheme.surface
                    ),
                    border = null,
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 164.dp
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = name,
                            style = MaterialTheme.typography.headlineLarge,
                            color = NeutralN900,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp),
                            text = description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        AccentButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            text = "Mark as watered",
                            enabled = !isDone,
                            onClick = onMarkWatered
                        )
                    }
                }

                CircleButton(
                    iconResource = R.drawable.ic_chevron_left,
                    modifier = Modifier
                        .size(36.dp)
                        .layoutId("go_back_button"),
                    color = MaterialTheme.colorScheme.onPrimary,
                    iconColor = NeutralN900,
                    onClick = {
                        navController.navigateUp()
                    },
                    paddingValues = PaddingValues(8.dp)
                )

                CircleButton(
                    iconResource = R.drawable.ic_pencil,
                    modifier = Modifier
                        .size(36.dp)
                        .layoutId("edit_button"),
                    color = MaterialTheme.colorScheme.onPrimary,
                    iconColor = NeutralN900,
                    onClick = {
                        navController.navigate(
                            Screen.EditPlant.route + "?plantId=${onEdit()}"
                        )
                    },
                    paddingValues = PaddingValues(8.dp)
                )

                ShortSummarySection(
                    modifier = Modifier
                        .layoutId("short_summary_box"),
                    size = size,
                    waterAmount = amount,
                    frequency = frequency
                )

            }
        }

    }
}

private fun screenContentConstraints() = ConstraintSet {
    val goBackButton = createRefFor("go_back_button")
    val editButton = createRefFor("edit_button")
    val shortSummaryBox = createRefFor("short_summary_box")
    val detailSection = createRefFor("detail_section")

    constrain(detailSection) {
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        bottom.linkTo(parent.bottom)
    }

    constrain(goBackButton) {
        top.linkTo(parent.top, margin = 20.dp)
        start.linkTo(parent.start, margin = 20.dp)
    }

    constrain(editButton) {
        top.linkTo(parent.top, margin = 20.dp)
        end.linkTo(parent.end, margin = 20.dp)
    }

    constrain(shortSummaryBox) {
        start.linkTo(parent.start, 20.dp)
        end.linkTo(parent.end, 20.dp)
        bottom.linkTo(detailSection.top, 16.dp)
    }
}

@Preview
@Composable
fun TaskDetailScreenPreview() {
    MyPlantsTheme {
        Surface {
        }
    }
}