package hwang.joy.hw3.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hwang.joy.hw3.Screen
import hwang.joy.hw3.components.*
import hwang.joy.hw3.data.ContactEntity
import kotlinx.coroutines.CoroutineScope

@Composable
fun ContactListScreen(
    scope: CoroutineScope,
    contacts: ImmutableList<ContactEntity>,
    currentScreen: Screen,
    onListScreenSelect: (Screen) -> Unit,
    selectedIds: ImmutableSet<String>,
    onToggleSelect: (String) -> Unit,
    onClearSelections: () -> Unit,
    onDeleteSelections: suspend () -> Unit,
    onReset: () -> Unit,
    select:(ContactEntity) -> Unit,
) =
    ContactListScaffold(
        scope = scope,
        contacts = contacts,
        currentScreen = currentScreen,
        onListScreenSelect = onListScreenSelect,
        selectedIds = selectedIds,
        onToggleSelect = onToggleSelect,
        onClearSelections = onClearSelections,
        onDeleteSelections = onDeleteSelections,
        onReset = onReset,
        select = select,
    )
