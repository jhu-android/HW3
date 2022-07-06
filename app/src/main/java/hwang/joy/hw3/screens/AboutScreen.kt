package hwang.joy.hw3.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope

@Composable
fun AboutScreen(
    scope: CoroutineScope,
) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "About") },
            )
        },
    ) {
        Column {
            Text(text = "About this app blah blah")
        }
    }


}