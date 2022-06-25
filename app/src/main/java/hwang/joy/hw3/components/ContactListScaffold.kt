package hwang.joy.hw3.components

import hwang.joy.hw3.Screen
import hwang.joy.hw3.data.ContactEntity
import kotlinx.coroutines.CoroutineScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun <T> ContactListScaffold(
    scope: CoroutineScope,
    contacts: ImmutableList<T>,
    currentScreen: Screen,
    onScreenSelect: (Screen) -> Unit,
    selectedIds: ImmutableSet<String>,
    onToggleSelect: (String) -> Unit,
    onClearSelections: () -> Unit,
    // onDeleteSelections: () -> Unit,
    onReset: () -> Unit,
    getKey: (T) -> String,
//    content: @Composable (Modifier) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "My Top App Bar")}
            )
        },
            bottomBar = {
                BottomAppBar(
                    content = { Text(text = "My Bottom App Bar")}
                )
            },
        ) { contentPadding ->
            Text(text="scaffold content")
            ContactListBody(
                modifier = Modifier.padding(contentPadding),
                contacts = contacts,
                getKey = getKey,
            )
        }
}