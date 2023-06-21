package com.example.myplants.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myplants.R
import com.example.myplants.ui.theme.MyPlantsTheme

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier,
            style = MaterialTheme.typography.headlineLarge
        )
        Icon(painter = painterResource(id = R.drawable.ic_edit), contentDescription = "")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyPlantsTheme {
        Greeting("Android")
    }
}