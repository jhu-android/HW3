package hwang.joy.hw3.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hwang.joy.hw3.R
import hwang.joy.hw3.Screen
import hwang.joy.hw3.data.ContactWithAddresses
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ContactDisplayScaffold(
    scope: CoroutineScope,
    currentScreen: Screen,
    onListScreenSelect:(Screen) -> Unit,
    contact: ContactWithAddresses?,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "${contact?.contact?.firstName} ${contact?.contact?.lastName}") }
            )

        },
        bottomBar = {
            BottomAppBar(
                content = { Text(text = "Bottom App Bar") }
            )
        }
    ) {
        ContactDisplayBody(
            scope = scope,
            contact = contact,
        )
    }

}