package hwang.joy.hw3.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    Column {
        ContactEditDetails(scope = scope,
            contactWithAddresses = contactWithAddresses,
            onContactChange = onContactChange,
            onContactAdd = onContactAdd
        )
        Row {
            Text(text="Addresses")
            // hitting plus button -> if no contact, add new contact!!
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
                modifier = Modifier.padding(8.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add address"
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




