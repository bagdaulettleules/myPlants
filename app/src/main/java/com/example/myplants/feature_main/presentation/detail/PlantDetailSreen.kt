package com.example.myplants.feature_main.presentation.detail

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import com.example.myplants.feature_main.domain.model.Plant
import com.example.myplants.feature_main.domain.model.Size
import com.example.myplants.feature_main.presentation.detail.components.ShortSummarySection
import com.example.myplants.ui.components.BigButton
import com.example.myplants.ui.components.CircleButton
import com.example.myplants.ui.theme.MyPlantsTheme
import com.example.myplants.ui.theme.NeutralN900

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalGlideComposeApi::class
)
@Composable
fun PlantDetailScreen(
    navController: NavHostController = rememberNavController(),
    plant: Plant,
    onEvent: () -> Unit
) {
    val bottomSheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.Expanded
    )
    val snackbarHostState = remember { SnackbarHostState() }
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState,
        snackbarHostState = snackbarHostState
    )
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = plant.name,
                    style = MaterialTheme.typography.headlineLarge,
                    color = NeutralN900,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                plant.description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                BigButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    text = "Mark as watered",
                    onClick = {
                    }
                )
            }
        },
        sheetShape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp
        ),
        sheetContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,
        sheetContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        sheetShadowElevation = 164.dp,
        sheetDragHandle = null,
        topBar = null
    ) {
        val pagerState = rememberPagerState(
            pageCount = { plant.images.size }
        )
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6F),
            contentAlignment = Alignment.BottomCenter
        ) {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize(),
                state = pagerState,
                key = {
                    plant.images[it]
                }
            ) { index ->
                if (plant.images.isEmpty()) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = painterResource(id = R.drawable.ic_plant_image_placeholder),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                } else {
                    GlideImage(
                        model = plant.images[index],
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    ) {
                        it
                            .placeholder(R.drawable.ic_plant_image_placeholder)
                            .error(R.drawable.ic_plant_image_placeholder)
                            .load(plant.images[index])
                    }
                }
            }
            val constraintSet = screenContentConstraints()

            ConstraintLayout(
                constraintSet = constraintSet,
                modifier = Modifier.fillMaxSize()
            ) {
                CircleButton(
                    iconResource = R.drawable.ic_chevron_left,
                    modifier = Modifier
                        .size(36.dp)
                        .layoutId("go_back_button"),
                    color = MaterialTheme.colorScheme.onPrimary,
                    iconColor = NeutralN900,
                    onClick = {},
                    paddingValues = PaddingValues(8.dp)
                )

                CircleButton(
                    iconResource = R.drawable.ic_pencil,
                    modifier = Modifier
                        .size(36.dp)
                        .layoutId("edit_button"),
                    color = MaterialTheme.colorScheme.onPrimary,
                    iconColor = NeutralN900,
                    onClick = {},
                    paddingValues = PaddingValues(8.dp)
                )

                ShortSummarySection(
                    modifier = Modifier
                        .layoutId("short_summary_box"),
                    size = plant.size.name,
                    waterAmount = plant.waterAmount,
                    frequency = plant.waterDays.size
                )

                LazyRow(
                    modifier = Modifier
                        .layoutId("page_indicator"),
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
                ) {
                    items(plant.images.size) { index ->
                        val animatedWidth =
                            animateDpAsState(
                                targetValue = if (index == pagerState.currentPage) {
                                    20.dp
                                } else {
                                    6.dp
                                }
                            )
                        val animatedColor = animateColorAsState(
                            targetValue = if (index == pagerState.currentPage) {
                                MaterialTheme.colorScheme.primaryContainer
                            } else {
                                MaterialTheme.colorScheme.secondary
                            }
                        )
                        Divider(
                            modifier = Modifier
                                .width(animatedWidth.value)
                                .height(6.dp)
                                .clip(
                                    shape = RoundedCornerShape(size = 100.dp)
                                ),
                            thickness = 6.dp,
                            color = animatedColor.value
                        )
                    }
                }

            }
        }
    }
}

private fun screenContentConstraints() = ConstraintSet {
    val goBackButton = createRefFor("go_back_button")
    val editButton = createRefFor("edit_button")
    val shortSummaryBox = createRefFor("short_summary_box")
    val pageIndicator = createRefFor("page_indicator")

    constrain(goBackButton) {
        top.linkTo(parent.top, margin = 20.dp)
        start.linkTo(parent.start, margin = 20.dp)
    }

    constrain(editButton) {
        top.linkTo(parent.top, margin = 20.dp)
        end.linkTo(parent.end, margin = 20.dp)
    }

    constrain(pageIndicator) {
        start.linkTo(parent.start, 20.dp)
        end.linkTo(parent.end, 20.dp)
        bottom.linkTo(parent.bottom, 36.dp)
    }

    constrain(shortSummaryBox) {
        start.linkTo(parent.start, 20.dp)
        end.linkTo(parent.end, 20.dp)
        bottom.linkTo(pageIndicator.top, 24.dp)
    }
}

@Preview
@Composable
fun PlantDetailScreenPreview() {
    MyPlantsTheme {
        Surface {
            PlantDetailScreen(plant = monsteraPlant) {

            }
        }
    }
}

val monsteraPlant = Plant(
    "Monstera",
    "Native to the Yunnan and Sichuan provinces of southern China, the Chinese money plant was first brought to the UK in 1906 by Scottish botanist George Forrest (yes, we know the exact man who found it). It became a popular houseplant later in the 20th century because it is simple to grow and really easy to propagate, meaning friends could pass cuttings around amongst themselves. That earned it the nickname ‘pass it on plant’.",
    listOf(
        "https://www.gardendesign.com/pictures/images/675x529Max/site_3/hawaiian-pothos-epipremnum-aureum-proven-winners_17324.jpg",
        "https://plantsome.ca/cdn/shop/products/Monsteraadansonii4inpot_500x400@2x.png?v=1626471600",
        "https://hgtvhome.sndimg.com/content/dam/images/grdn/fullset/2014/6/25/0/CI_04-fbfd01d70004.jpg.rend.hgtvcom.616.493.suffix/1452664590074.jpeg"
    ),
    emptyList(),
    12,
    500,
    Size.MEDIUM,
    null,
    1
)