package hwang.joy.hw3.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    getKey: (AddressEntity) -> String,
){
    Log.d("jhw%%", contactWithAddresses?.addresses?.size.toString())
        LazyColumn(modifier = Modifier) {
            item {
                ContactEditDetails(scope = scope,
                    contactWithAddresses = contactWithAddresses,
                    onContactChange = onContactChange,
                    onContactAdd = onContactAdd
                )
            }
            item {
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
//                        modifier = Modifier.padding(8.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "add address"
                        )
                    }

                }
            }
            if (contactWithAddresses?.addresses != null) {
                items(
                    items = contactWithAddresses.addresses,
                    key = getKey,
                ) { address ->
                    val key = getKey(address)
                    Card(
                        elevation = 4.dp,
                        backgroundColor = MaterialTheme.colors.surface,
                        modifier = Modifier
//                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable {
                                scope.launch {
                                    onAddressEdit(address)
                                }
                            }
                    ) {
                        Row(
//                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                text = address.street,
                                modifier = Modifier.weight(1f)
                            )
                            IconButton(
                                onClick = {
                                    scope.launch(Dispatchers.IO) {
                                        onAddressDelete(key)
                                    }
                                },
//                                modifier = Modifier.padding(8.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "delete",
                                )
                            }
                        }
                    }
            }
        }
//            item {
//                Text(text="hello im at the bottom of the lazycolumn")
//            }
    }


}




