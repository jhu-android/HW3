package hwang.joy.hw3.screens

import androidx.compose.foundation.clickable
import hwang.joy.hw3.Screen
import hwang.joy.hw3.data.ContactEntity
import kotlinx.coroutines.CoroutineScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import hwang.joy.hw3.R
import hwang.joy.hw3.components.ContactListBody
import hwang.joy.hw3.components.ImmutableList
import hwang.joy.hw3.components.ImmutableSet

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
    onEdit: () -> Unit,
    onClickAbout: () -> Unit,
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
                                    onClickAbout()
                                }
                            }
                        ) {
                            Icon(
                                tint = Color.LightGray,
                                imageVector = Icons.Default.Info,
                                contentDescription = stringResource(id = R.string.icon_description_about),
                            )
                        }

                        IconButton(
                            onClick = {
                                scope.launch(Dispatchers.IO) {
                                    onEdit()
                                }
                            }
                        ) {
                            Icon(
                                tint = Color.Green,
                                imageVector = Icons.Default.Add,
                                contentDescription = stringResource(id = R.string.icon_description_tap_to_add)
                            )
                        }

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
                                contentDescription = stringResource(id = R.string.icon_description_reset_db),
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
                            contentDescription = stringResource(id = R.string.icon_description_clear_select),
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
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
        }
    ) {
        ContactListBody(
            scope = scope,
            contacts = contacts,
            getKey = { contactEntity -> contactEntity.id },
            selectedIds = selectedIds,
            onToggleSelect = onToggleSelect,
            onClearSelections = onClearSelections,
            onContactClick = select,
            onDeleteSelections = onDeleteSelections,
        )
    }
}