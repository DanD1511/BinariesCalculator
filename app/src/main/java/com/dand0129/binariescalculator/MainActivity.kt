package com.dand0129.binariescalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    Column() {
        InputNumber(
            colorBox = Color(0xff3c94ff),
            colorField = Color(0xffff8b04),
            viewModel = viewModel,
            title = "Bin To Dec",
            onButtonClicked = { value ->
                viewModel.binToDec(value)
            }
        )
    }
}

@Composable
fun DecToBin(viewModel: ViewModel) {
    Column() {
        InputNumber(
            colorBox = Color(0xffff8b04),
            colorField = Color(0xff3c94ff),
            viewModel = viewModel,
            title = "Dec to Bin",
            onButtonClicked = { value ->
                viewModel.decToBin(value)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputNumber(
    colorBox: Color,
    colorField: Color,
    title: String = "title",
    onButtonClicked: (String) -> Unit,
    viewModel: ViewModel
) {
    val inputNumber = remember { mutableStateOf("") }
    val textToShow by viewModel.textToShow.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff010101)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            text = title,
            fontSize = 42.sp,
            color = Color(0xFFf5f3ff)
        )
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.25f)
                .background(
                    color = colorBox,
                    shape = RoundedCornerShape(10)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            TextField(
                modifier = Modifier
                    .padding(20.dp)
                    .background(
                        color = colorField
                    ),
                value = inputNumber.value,
                onValueChange = {
                    inputNumber.value = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle.Default.copy(fontSize = 20.sp, color = Color.White),
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth(0.5f),
                colors = ButtonDefaults.buttonColors(colorField),
                onClick = {
                    onButtonClicked(inputNumber.value)
                }
            ) {
                Text(
                    text = "Calculate",
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }

        Text(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .fillMaxSize(0.3f)
                .background(
                    Color(0xff2b2b2b),
                    shape = RoundedCornerShape(10)
                ),
            text = textToShow!!,
            textAlign = TextAlign.Center,
        )
    }
}