package hwang.joy.hw3.components

import androidx.compose.foundation.clickable
import hwang.joy.hw3.Screen
import hwang.joy.hw3.data.ContactEntity
import kotlinx.coroutines.CoroutineScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ContactListScaffold(
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

) {
    Scaffold(
        topBar = {
            if (selectedIds.size == 0) {
                TopAppBar(
                    title = { Text(text = "Contacts")},
                    actions = {
                        IconButton(
                            onClick = {
                                scope.launch(Dispatchers.IO) {
                                    onReset()
                                }
                            }
                        ) {
                            Icon(
                                tint = Color.Yellow,
                                imageVector = Icons.Default.Autorenew,
                                contentDescription = "refresh",
                            )

                        }
                        // TODO button for adding new contact
                    }
                )
            } else {
                TopAppBar(
                    title = { Text(text = "${selectedIds.size} selected")},
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back",
                            modifier = Modifier.padding(8.dp).clickable {
                                onClearSelections()
                            }
                        )
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                scope.launch(Dispatchers.IO) {
                                    onDeleteSelections()
                                }
                            },
                            modifier = Modifier.padding(8.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "delete",
                            )
                        }
                    }
                )
            }

        },
        bottomBar = {
            BottomAppBar(
                content = { Text(text = "Bottom App Bar")}
            )
        },
        content = {
            ContactListBody(
                scope = scope,
                contacts = contacts,
                getKey = { contactEntity -> contactEntity.id  },
                currentScreen = currentScreen,
                onListScreenSelect = onListScreenSelect,
                selectedIds = selectedIds,
                onToggleSelect = onToggleSelect,
                onClearSelections = onClearSelections,
                onContactClick = select,
                onDeleteSelections = onDeleteSelections,
            )
        }
    )
}