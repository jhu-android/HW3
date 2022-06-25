package hwang.joy.hw3.screens

import androidx.compose.runtime.Composable
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
    // onDeleteSelections: () -> Unit,
    onReset: () -> Unit,
) =
    ContactListScaffold(
        scope = scope,
        contacts = contacts,
        getKey = { contactEntity -> contactEntity.id  },
        currentScreen = currentScreen,
        onListScreenSelect = onListScreenSelect,
        selectedIds = selectedIds,
        onToggleSelect = onToggleSelect,
        onClearSelections = onClearSelections,
        onReset = onReset,
    )
