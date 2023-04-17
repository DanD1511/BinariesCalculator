package com.dand0129.binariescalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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
fun MainScreen(viewModel: ViewModel) {
    var shouldShowScreen by rememberSaveable { mutableStateOf(true) }
    var shouldShowScreen2 by rememberSaveable { mutableStateOf(true) }

    BackHandler(onBack = {
        if (!shouldShowScreen && !shouldShowScreen2) {
            shouldShowScreen = true
            shouldShowScreen2 = true
            viewModel.clearText()
        } else if (!shouldShowScreen) {
            shouldShowScreen = true
            viewModel.clearText()
        } else if (!shouldShowScreen2) {
            shouldShowScreen2 = true
            viewModel.clearText()
        }
    })

    Surface {
        if (shouldShowScreen && shouldShowScreen2) {
            WelcomeScreen(onContinueClicked1 = { shouldShowScreen = false },
                onContinueClicked2 = { shouldShowScreen2 = false })
        } else if (!shouldShowScreen) {
            BinToDec(viewModel)
        } else if (!shouldShowScreen2) {
            DecToBin(viewModel)
        }
    }
}


@Composable
fun WelcomeScreen(
    onContinueClicked1: () -> Unit, onContinueClicked2: () -> Unit
) {
    val largeRadialGradient = object : ShaderBrush() {
        override fun createShader(size: Size): Shader {
            val biggerDimension = maxOf(size.height, size.width)
            return RadialGradientShader(
                colors = listOf(Color(0xFF1c1c1c), Color.Black),
                center = size.center,
                radius = biggerDimension / 3f,
                colorStops = listOf(0f, 0.95f)
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                largeRadialGradient
            )
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 90.dp, start = 50.dp, end = 50.dp, bottom = 50.dp)
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xFF2c2c2c), shape = RoundedCornerShape(15.dp)
                    )
                    .height(300.dp)
                    .width(300.dp)
                    .border(
                        width = 1.dp, color = Color.White, shape = RoundedCornerShape(15.dp)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .background(
                            brush = largeRadialGradient, shape = RoundedCornerShape(15.dp)
                        )
                        .height(250.dp)
                        .width(250.dp)
                        .border(
                            width = 1.dp, color = Color.White, shape = RoundedCornerShape(15.dp)
                        )
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Binary converter",
                        color = Color.White,
                        fontSize = 35.sp,
                        fontFamily = FontFamily(Font(R.font.tron)),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp, start = 50.dp, end = 50.dp)
        ) {
            Box(
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Button(
                    onClick = {
                        onContinueClicked1()
                    }, modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .background(
                            shape = RoundedCornerShape(10.dp), color = Color(0xFF2279cb)
                        )
                        .border(
                            width = 1.dp,
                            color = Color(0xFF58caf3),
                            shape = RoundedCornerShape(10.dp)
                        ), colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2279cb), contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Bin to Dec",
                        fontSize = 25.sp,
                        fontFamily = FontFamily(Font(R.font.tron)),
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            Box(
                modifier = Modifier.padding(bottom = 20.dp)
            ) {
                Button(
                    onClick = {
                        onContinueClicked2()
                    }, modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .background(
                            shape = RoundedCornerShape(10.dp), color = Color(0xFFf98b08)
                        )
                        .border(
                            width = 1.dp,
                            color = Color(0xFFffff30),
                            shape = RoundedCornerShape(10.dp)
                        ), colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFf98b08), contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Dec to Bin",
                        fontSize = 25.sp,
                        fontFamily = FontFamily(Font(R.font.tron)),
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        fontWeight = FontWeight.W100
                    )
                }
            }
        }
    }
}

@Composable
fun BinToDec(viewModel: ViewModel) {
    InputNumber(colorBox = Color(0xFF2279cb),
        colorField = Color(0xFFf98b08),
        viewModel = viewModel,
        title = "Bin To Dec",
        onButtonClicked = { value ->
            viewModel.binToDec(value)
        })
}


@Composable
fun DecToBin(viewModel: ViewModel) {
    InputNumber(colorBox = Color(0xFFf98b08),
        colorField = Color(0xFF2279cb),
        viewModel = viewModel,
        title = "Dec to Bin",
        onButtonClicked = { value ->
            viewModel.decToBin(value)
        })
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff010101))
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xFF2c2c2c), shape = RoundedCornerShape(15.dp)
                    )
                    .fillMaxHeight(0.2f)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp, color = Color.White, shape = RoundedCornerShape(15.dp)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(20.dp)
                        .background(
                            color = Color.Black, shape = RoundedCornerShape(15.dp)
                        )
                        .fillMaxHeight(0.8f)
                        .fillMaxWidth()
                        .border(
                            width = 1.dp, color = Color.White, shape = RoundedCornerShape(15.dp)
                        )
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = title,
                        color = Color.White,
                        fontSize = 35.sp,
                        fontFamily = FontFamily(Font(R.font.tron)),
                        textAlign = TextAlign.Center,
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight(0.35f)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp, color = Color.White, shape = RoundedCornerShape(10)
                    )
                    .background(
                        color = colorBox, shape = RoundedCornerShape(10)
                    ),
            ) {
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = colorField
                    ),
                    modifier = Modifier
                        .padding(20.dp)
                        .border(
                            width = 1.dp, color = Color.White, shape = RoundedCornerShape(15)
                        )
                        .clip(RoundedCornerShape(15)),
                    value = inputNumber.value,
                    onValueChange = {
                        inputNumber.value = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = TextStyle.Default.copy(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.asul_bold)),
                        color = Color.White
                    )
                )
                Button(modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(10.dp)
                    .background(
                        shape = RoundedCornerShape(10), color = colorField
                    ), colors = ButtonDefaults.buttonColors(colorField), onClick = {
                    onButtonClicked(inputNumber.value)
                }) {
                    Text(
                        text = "Calculate",
                        fontSize = 25.sp,
                        fontFamily = FontFamily(Font(R.font.tron)),
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        fontWeight = FontWeight.W100
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .background(
                        color = Color(0xff2b2b2b), shape = RoundedCornerShape(10)
                    ), contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = textToShow!!,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.asul_bold))
                )
            }
        }
    }
}
