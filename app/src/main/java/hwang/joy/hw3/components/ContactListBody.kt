// Attribution: Scott Stanfield, Android Summer 2022
// Source code: https://gitlab.com/605-686/android-summer-2022/-/tree/main/Movies3

package hwang.joy.hw3.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import hwang.joy.hw3.R
import hwang.joy.hw3.data.ContactEntity


@Composable
fun ContactListBody(
    contacts: ImmutableList<ContactEntity>,
    getKey: (ContactEntity) -> String,
    selectedIds: ImmutableSet<String>,
    onToggleSelect: (String) -> Unit,
    onContactClick: (ContactEntity) -> Unit,
) {
    val sortedContacts = contacts.sortedWith(compareByOver({it.lastName.uppercase()}, {it.firstName.uppercase()}))
    LazyColumn(
        modifier = Modifier.padding(20.dp)
    ) {
        items(
            items = sortedContacts,
            key = getKey,
        ) { contact ->
            val key = getKey(contact)
            Card(
                elevation = 2.dp,
                backgroundColor =
                    if (key in selectedIds)
                        MaterialTheme.colors.secondary
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
                        imageVector = Icons.Sharp.Person,
                        contentDescription = stringResource(id = R.string.icon_description_tap_to_select),
                        modifier = Modifier
                            .size(48.dp)
                            .padding(8.dp)
                            .clickable {
                                onToggleSelect(key)
                            }
                    )
                    Column {
                        Text(
                            text= "${contact.firstName} ${contact.lastName}",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(20.dp, 8.dp, 4.dp, 0.dp)
                        )
                        Text(
                            text= contact.mobilePhone,
                            modifier = Modifier.padding(20.dp, 0.dp, 4.dp, 8.dp)
                        )
                    }

                }
            }
        }
    }
}
