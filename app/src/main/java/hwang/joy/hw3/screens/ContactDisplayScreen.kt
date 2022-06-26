package hwang.joy.hw3.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import hwang.joy.hw3.Screen
import hwang.joy.hw3.data.ContactWithAddresses
import kotlinx.coroutines.CoroutineScope

@Composable
fun ContactDisplayScreen(
    scope: CoroutineScope,
    currentScreen: Screen,
    onListScreenSelect:(Screen) -> Unit,
    contact: ContactWithAddresses?,
) {


}