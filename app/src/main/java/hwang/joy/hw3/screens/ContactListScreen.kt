// Attribution: Scott Stanfield, Android Summer 2022
// Source code: https://gitlab.com/605-686/android-summer-2022/-/tree/main/Movies3

package hwang.joy.hw3.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hwang.joy.hw3.R
import hwang.joy.hw3.components.ContactListBody
import hwang.joy.hw3.components.ImmutableList
import hwang.joy.hw3.components.ImmutableSet
import hwang.joy.hw3.data.ContactEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ContactListScreen(
    scope: CoroutineScope,
    contacts: ImmutableList<ContactEntity>,
    selectedIds: ImmutableSet<String>,
    onToggleSelect: (String) -> Unit,
    onClearSelections: () -> Unit,
    onDeleteSelections: suspend () -> Unit,
    onReset: () -> Unit,
    onAddContact: suspend () -> Unit,
    onClickAbout: () -> Unit,
    select:(ContactEntity) -> Unit,
    ) {
    Scaffold(
        topBar = {
            if (selectedIds.size == 0) {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.screen_title_contacts))},
                    actions = {
                        IconButton(
                            onClick = {
                                scope.launch(Dispatchers.IO) {
                                    onAddContact()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Sharp.Add,
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
                                imageVector = Icons.Sharp.Autorenew,
                                contentDescription = stringResource(id = R.string.icon_description_reset_db),
                            )

                        }
                        IconButton(
                            onClick = {
                                scope.launch(Dispatchers.IO) {
                                    onClickAbout()
                                }
                            }
                        ) {
                            Icon(

                                imageVector = Icons.Sharp.Info,
                                contentDescription = stringResource(id = R.string.icon_description_about),
                            )
                        }
                    }
                )
            } else {
                TopAppBar(
                    title = { Text(text = "${selectedIds.size} selected")},
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Sharp.ArrowBack,
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
                                imageVector = Icons.Sharp.Delete,
                                contentDescription = stringResource(id = R.string.icon_description_delete),
                            )
                        }
                    }
                )
            }

        },
    ) {
        if (contacts.isEmpty()) {
            Card(
                elevation = 4.dp,
                backgroundColor = MaterialTheme.colors.surface,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.message_no_contacts),
                    modifier = Modifier.padding(4.dp),
                )
            }
        } else {
            ContactListBody(
                contacts = contacts,
                getKey = { contactEntity -> contactEntity.id },
                selectedIds = selectedIds,
                onToggleSelect = onToggleSelect,
                onContactClick = select,
            )
        }
    }
}