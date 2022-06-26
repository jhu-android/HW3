package hwang.joy.hw3.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import hwang.joy.hw3.Screen
import hwang.joy.hw3.data.ContactEntity
import kotlinx.coroutines.CoroutineScope


@Composable
fun ContactListBody(
    scope: CoroutineScope,
    contacts: ImmutableList<ContactEntity>,
    getKey: (ContactEntity) -> String,
    currentScreen: Screen,
    onListScreenSelect: (Screen) -> Unit,
    selectedIds: ImmutableSet<String>,
    onToggleSelect: (String) -> Unit,
    onClearSelections: () -> Unit,
    onDeleteSelections: suspend () -> Unit,
    onContactClick: (ContactEntity) -> Unit,
) =
    LazyColumn(
        modifier = Modifier.padding(20.dp)
    ) { items(
        items = contacts,
        key = getKey,
        ) { contact ->
            val key = getKey(contact)
            Card(
                elevation = 4.dp,
                backgroundColor =
                    if (key in selectedIds)
                        MaterialTheme.colors.primary
                    else
                        MaterialTheme.colors.surface
                ,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .pointerInput(selectedIds) {
                        detectTapGestures(
                            onLongPress = {
                                onToggleSelect(key)
                            },
                            onTap = {
                                if (selectedIds.isNotEmpty()) {
                                    onToggleSelect(key)
                                } else {
                                    onContactClick(contact)
                                }
                            }
                        )
                    }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "a person",
                        modifier = Modifier
                            .size(48.dp)
                            .padding(8.dp)
                            .clickable {
                                onToggleSelect(key)
                            }
                    )
                    Text(text= "${contact.firstName} ${contact.lastName}")
                    Text(text= contact.mobilePhone)
                }
            }
        }
    }