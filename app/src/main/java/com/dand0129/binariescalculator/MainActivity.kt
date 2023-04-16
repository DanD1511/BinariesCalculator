package com.dand0129.binariescalculator

import android.os.Bundle
import android.renderscript.ScriptGroup.Input
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    val viewModel: ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(viewModel)
        }
    }
}

@Composable
private fun MainScreen(viewModel: ViewModel) {
    var shouldShowScreen by rememberSaveable { mutableStateOf<Boolean>(true) }
    var shouldShowScreen2 by rememberSaveable { mutableStateOf<Boolean>(true) }

    Surface {
        if (shouldShowScreen && shouldShowScreen2) {
            WelcomeScreen(
                onContinueClicked1 = { shouldShowScreen = false },
                onContinueClicked2 = { shouldShowScreen2 = false }
            )
        } else if (!shouldShowScreen) {
            BinToDec(viewModel)
        } else if (!shouldShowScreen2) {
            DecToBin(viewModel)
        } else return@Surface
    }
}

@Composable
fun WelcomeScreen(
    onContinueClicked1: () -> Unit,
    onContinueClicked2: () -> Unit
) {
    Column {
        Button(onClick = {
            onContinueClicked1()
        }) {
            Text(text = "Bin to Dec")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            onContinueClicked2()
        }) {
            Text(text = "Dec to Bin")
        }
    }
}

@Composable
fun BinToDec(viewModel: ViewModel) {
    val textToShow by viewModel.textToShow.observeAsState()

    Column() {
        InputNumber(
            title = "Bin To Dec",
            onButtonClicked = { value ->
                viewModel.binToDec(value)
            }
        )
        Text(
            text = textToShow!!
        )
    }
}

@Composable
fun DecToBin(viewModel: ViewModel) {
    val textToShow by viewModel.textToShow.observeAsState()

    Column() {
        InputNumber(
            title = "Dec to Bin",
            onButtonClicked = { value ->
                viewModel.decToBin(value)
            }
        )
        Text(
            text = textToShow!!
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputNumber(
    title: String = "title",
    onButtonClicked: (String) -> Unit
) {
    val inputNumber = remember { mutableStateOf("") }

    Column() {
        Text(text = title)
        TextField(
            value = inputNumber.value,
            onValueChange = {
                inputNumber.value = it
            }
        )
        Button(
            onClick = {
                onButtonClicked(inputNumber.value)
            }
        ) {

        }
    }
}