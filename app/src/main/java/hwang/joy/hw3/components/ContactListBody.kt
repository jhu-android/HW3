package hwang.joy.hw3.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hwang.joy.hw3.R
import hwang.joy.hw3.Screen
import hwang.joy.hw3.data.ContactEntity
import kotlinx.coroutines.CoroutineScope


@Composable
fun ContactListBody(
    contacts: ImmutableList<ContactEntity>,
    getKey: (ContactEntity) -> String,
    selectedIds: ImmutableSet<String>,
    onToggleSelect: (String) -> Unit,
    onContactClick: (ContactEntity) -> Unit,
) {
    val sortedContacts = contacts.sortedBy { it.lastName.uppercase() }

    LazyColumn(
        modifier = Modifier.padding(20.dp)
    ) {
        items(
            items = sortedContacts,
            key = getKey,
        ) { contact ->
            val key = getKey(contact)
            Card(
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
                        contentDescription = stringResource(id = R.string.icon_description_tap_to_select),
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
}
