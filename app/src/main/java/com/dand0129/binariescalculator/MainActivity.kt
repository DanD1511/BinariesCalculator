package com.dand0129.binariescalculator

import android.os.Bundle
import android.renderscript.ScriptGroup.Input
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
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
                        color = Color(0xFF2c2c2c),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .height(300.dp)
                    .width(300.dp)
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(15.dp)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .background(
                            brush = largeRadialGradient,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .height(250.dp)
                        .width(250.dp)
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(15.dp)
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
                modifier = Modifier
                    .padding(top = 20.dp)
            ) {
                Button(
                    onClick = {
                        onContinueClicked1()
                    },
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .background(
                            shape = RoundedCornerShape(10.dp), color = Color(0xFF2279cb)
                        )
                        .border(
                            width = 1.dp,
                            color = Color(0xFF58caf3),
                            shape = RoundedCornerShape(10.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2279cb),
                        contentColor = Color.White
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
                modifier = Modifier
                    .padding(bottom = 20.dp)
            ) {
                Button(
                    onClick = {
                        onContinueClicked1()
                    },
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .background(
                            shape = RoundedCornerShape(10.dp),
                            color = Color(0xFFf98b08)
                        )
                        .border(
                            width = 1.dp,
                            color = Color(0xFFffff30),
                            shape = RoundedCornerShape(10.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFf98b08),
                        contentColor = Color.White
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