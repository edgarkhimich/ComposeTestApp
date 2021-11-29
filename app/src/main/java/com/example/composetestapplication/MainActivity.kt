package com.example.composetestapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                val previousBoxColor = remember {
                    MutableLiveData<Color>()
                }

                val color = remember {
                    mutableStateOf(Color.Yellow)
                }

                ColorBox(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    previousBoxColor = previousBoxColor.value,
                    colorBoxOnClickListener = {
                        previousBoxColor.value = color.value
                        color.value = it
                    }
                )
                Box(
                    modifier = Modifier
                        .background(color.value)
                        .weight(1f)
                        .fillMaxSize()
                )
            }
        }
    }
}


@Composable
fun ColorBox(
    modifier: Modifier = Modifier,
    previousBoxColor: Color?,
    colorBoxOnClickListener: (Color) -> Unit
) {
    Box(
        modifier = modifier
            .background(previousBoxColor ?: Color.Black)
            .clickable {
                colorBoxOnClickListener(
                    Color(
                        Random.nextFloat(),
                        Random.nextFloat(),
                        Random.nextFloat(),
                        1f
                    )
                )
            }
    )

}
