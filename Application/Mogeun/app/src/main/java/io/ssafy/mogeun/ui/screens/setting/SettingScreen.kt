package io.ssafy.mogeun.ui.screens.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SettingScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "test")
            }
            Box (
                modifier = Modifier
                    .padding(4.dp)

            ) {
                Box(
                    modifier = Modifier
                        .width(300.dp)
                        .height(120.dp)
                        .background(color = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "Primary", color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    }
}
