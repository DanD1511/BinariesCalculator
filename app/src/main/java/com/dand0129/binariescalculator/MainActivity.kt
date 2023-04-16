package com.dand0129.binariescalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dand0129.binariescalculator.ui.theme.BinariesCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrincipalScreen()
        }
    }
}

@Composable
private fun PrincipalScreen() {
    var shouldShowScreen by rememberSaveable { mutableStateOf<Boolean>(true) }
    var shouldShowScreen2 by rememberSaveable { mutableStateOf<Boolean>(true) }

    Surface {
        if (shouldShowScreen && shouldShowScreen2) {
            WelcomeScreen(onContinueClicked1 = { shouldShowScreen = false },
                onContinueClicked2 = { shouldShowScreen2 = false})
        } else if (!shouldShowScreen && shouldShowScreen2) {
            BinToDec()
        } else if (shouldShowScreen && !shouldShowScreen2) {
            DecToBin()
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
            Text(text = "Binario a decimal")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            onContinueClicked2()
        }) {
            Text(text = "Decimal a binario")
        }
    }
}

@Composable
fun BinToDec() {
    Text(text = "Binario a decimal")
}

@Composable
fun DecToBin() {
    Text(text = "Decimal a binario")
}