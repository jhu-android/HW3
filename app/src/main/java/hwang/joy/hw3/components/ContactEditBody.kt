// Attribution: Scott Stanfield, Android Summer 2022
// Source code: https://gitlab.com/605-686/android-summer-2022/-/tree/main/Movies3

package hwang.joy.hw3.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hwang.joy.hw3.R
import hwang.joy.hw3.data.AddressEntity
import hwang.joy.hw3.data.ContactEntity
import hwang.joy.hw3.data.ContactWithAddresses
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ContactEditBody(
    scope: CoroutineScope,
    contactWithAddresses: ContactWithAddresses?,
    onContactChange: suspend (ContactEntity) -> Unit,
    onContactAdd: suspend (ContactEntity) -> Unit,
    onAddressDelete: suspend (String) -> Unit,
    onAddAddressClick: suspend (String) -> Unit,
    onAddAddressClickNewContact: suspend (ContactEntity) -> Unit,
    onAddressEdit: suspend (AddressEntity) -> Unit,
){
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        ContactEditDetails(scope = scope,
            contactWithAddresses = contactWithAddresses,
            onContactChange = onContactChange,
            onContactAdd = onContactAdd
        )
        Card(
            elevation = 2.dp,
            backgroundColor = Color(0xFFf2f2f5),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Column {
                Row {
                    Text(
                        text= stringResource(id = R.string.label_addresses),
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .padding(8.dp, 10.dp, 8.dp, 8.dp)
                            .weight(1f)
                    )
                    IconButton(
                        onClick = {
                            scope.launch(Dispatchers.IO) {
                                if (contactWithAddresses != null) {
                                    onAddAddressClick(contactWithAddresses.contact.id)
                                } else {
                                    onAddAddressClickNewContact(createNewContact())
                                }
                            }
                        },
                        modifier = Modifier.padding(0.dp, 0.dp, 8.dp, 0.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Sharp.Add,
                            tint = Color(0xFF046524),
                            contentDescription = stringResource(id = R.string.icon_description_tap_to_add)
                        )
                    }
                }
                ContactEditAddresses(
                    scope = scope,
                    addresses = contactWithAddresses?.addresses,
                    getKey = { addressEntity -> addressEntity.id  },
                    onAddressDelete = onAddressDelete,
                    onAddressEdit = onAddressEdit,
                )
            }

        }

    }
}




