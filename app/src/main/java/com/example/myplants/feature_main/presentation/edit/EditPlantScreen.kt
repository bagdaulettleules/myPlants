package com.example.myplants.feature_main.presentation.edit

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
import com.example.myplants.feature_main.presentation.edit.components.HintTextField
import com.example.myplants.feature_main.presentation.edit.components.SelectDialog
import com.example.myplants.feature_main.presentation.edit.components.SelectOptionField
import com.example.myplants.feature_main.presentation.edit.components.SingleSelectDialog
import com.example.myplants.feature_main.presentation.edit.states.ImageViewState
import com.example.myplants.feature_main.presentation.edit.states.MultipleSelectFieldState
import com.example.myplants.feature_main.presentation.edit.states.SingleSelectFieldState
import com.example.myplants.feature_main.presentation.edit.states.TextFieldState
import com.example.myplants.feature_main.presentation.util.UiEvent
import com.example.myplants.ui.components.AccentButton
import com.example.myplants.ui.components.CircleButton
import com.example.myplants.ui.theme.MyPlantsTheme
import com.example.myplants.ui.theme.NeutralN900
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun EditPlantScreen(
    navController: NavHostController = rememberNavController(),
    nameState: TextFieldState,
    descriptionState: TextFieldState,
    sizeState: SingleSelectFieldState,
    waterAmountState: SingleSelectFieldState,
    waterDaysState: MultipleSelectFieldState,
    imageState: ImageViewState,
    onEvent: (EditPlantEvent) -> Unit,
    eventFlow: MutableSharedFlow<UiEvent> = MutableSharedFlow()
) {
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> onEvent(EditPlantEvent.ImagePicked(uri)) }
    )

    LaunchedEffect(key1 = true) {
        eventFlow.collectLatest { uiEvent ->
            when (uiEvent) {
                is UiEvent.ErrorOccurred -> {
                    snackbarHostState.showSnackbar(uiEvent.message)
                }

                UiEvent.SuccessfullyCompleted -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = MaterialTheme.colorScheme.onPrimary)
                    .padding(start = 20.dp, end = 20.dp, top = 8.dp, bottom = 8.dp)
            ) {
                AccentButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    text = "Save changes",
                    enabled = true,
                    onClick = {
                        onEvent(EditPlantEvent.PlantSaved)
                    }
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            GlideImage(
                model = imageState.uri,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            ) {
                it
                    .placeholder(R.drawable.ic_plant_image_placeholder)
                    .error(R.drawable.ic_plant_image_placeholder)
                    .load(imageState.uri)
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
                    onClick = {
                        navController.navigateUp()
                    },
                    paddingValues = PaddingValues(8.dp)
                )

                AccentButton(
                    modifier = Modifier
                        .layoutId("change_image_button"),
                    text = imageState.hint,
                    icon = R.drawable.ic_upload,
                    onClick = {
                        imagePickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(446.dp)
                        .layoutId("plant_detail_container"),
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(20.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
                    ) {
                        HintTextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = nameState.text,
                            hint = nameState.hint,
                            label = "Plant name*",
                            isHintVisible = nameState.isHintVisible,
                            onValueChange = {
                                onEvent(EditPlantEvent.NameChanged(it))
                            },
                            onFocusChange = {
                                onEvent(EditPlantEvent.NameFocusChanged(it))
                            }
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            SelectOptionField(
                                modifier = Modifier.weight(1f, false),
                                text = waterDaysState.text,
                                label = "Dates*",
                                onClick = {
                                    onEvent(EditPlantEvent.DatesFocusChanged(true))
                                }
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            SelectOptionField(
                                modifier = Modifier.weight(1f, false),
                                text = "00:00",
                                label = "Time*",
                                onClick = {
                                    onEvent(EditPlantEvent.TimeFocusChanged(true))
                                }
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            SelectOptionField(
                                modifier = Modifier.weight(1f, false),
                                text = waterAmountState.text,
                                label = "The amount of water*",
                                onClick = {
                                    onEvent(EditPlantEvent.WaterAmountFocusChanged(true))
                                }
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            SelectOptionField(
                                modifier = Modifier.weight(1f, false),
                                label = "Plant size*",
                                text = sizeState.text,
                                onClick = {
                                    onEvent(EditPlantEvent.SizeFocusChanged(true))
                                }
                            )
                        }

                        HintTextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = descriptionState.text,
                            hint = descriptionState.hint,
                            label = "Description",
                            isHintVisible = descriptionState.isHintVisible,
                            onValueChange = {
                                onEvent(EditPlantEvent.DescriptionChanged(it))
                            },
                            onFocusChange = {
                                onEvent(EditPlantEvent.DescriptionFocusChanged(it))
                            }
                        )

                    }

                }
            }

        }

        if (sizeState.isDialogShown) {
            SingleSelectDialog(
                title = "Plant Size",
                options = sizeState.options,
                defaultSelected = sizeState.selected,
                onSubmit = {
                    onEvent(EditPlantEvent.SizeSelected(it))
                },
                onDismissRequest = {
                    onEvent(EditPlantEvent.SizeFocusChanged(false))
                }
            )
        }

        if (waterAmountState.isDialogShown) {
            SingleSelectDialog(
                title = "The amount of water",
                options = waterAmountState.options,
                defaultSelected = waterAmountState.selected,
                onSubmit = {
                    onEvent(EditPlantEvent.WaterAmountSelected(it))
                },
                onDismissRequest = {
                    onEvent(EditPlantEvent.WaterAmountFocusChanged(false))
                }
            )
        }

        if (waterDaysState.isDialogShown) {
            SelectDialog(
                title = "Dates",
                options = waterDaysState.options,
                onSubmit = {
                    onEvent(EditPlantEvent.DatesSelected(it))
                },
                onDismissRequest = {
                    onEvent(EditPlantEvent.DatesFocusChanged(false))
                }
            )
        }

    }

}

private fun screenContentConstraints() = ConstraintSet {
    val goBackBtn = createRefFor("go_back_button")
    val plantDetailContainer = createRefFor("plant_detail_container")
    val changeImageButton = createRefFor("change_image_button")

    constrain(goBackBtn) {
        top.linkTo(parent.top, margin = 20.dp)
        start.linkTo(parent.start, margin = 20.dp)
    }

    constrain(plantDetailContainer) {
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        bottom.linkTo(parent.bottom)
    }

    constrain(changeImageButton) {
        bottom.linkTo(plantDetailContainer.top, margin = 16.dp)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }
}

@Preview
@Composable
fun EditPlantScreenPreview() {
    MyPlantsTheme {
        EditPlantScreen(
            nameState = TextFieldState(
                hint = "Enter plant name"
            ),
            descriptionState = TextFieldState(
                hint = "Enter short description for this plant"
            ),
            sizeState = SingleSelectFieldState(
                isDialogShown = false,
                options = listOf("Small", "Medium", "Large", "Extra large"),
                selected = 1
            ),
            waterAmountState = SingleSelectFieldState(
                isDialogShown = false,
                options = listOf("250ml", "500ml", "750ml", "1000ml"),
                selected = 1
            ),
            waterDaysState = MultipleSelectFieldState(
                isDialogShown = true,
                options = listOf(
                    SelectItem("Everyday", false),
                    SelectItem("Monday", false),
                    SelectItem("Tuesday", false),
                    SelectItem("Wednesday", true)
                )
            ),
            imageState = ImageViewState(
                hint = "Add image"
            ),
            onEvent = {}
        )
    }
}